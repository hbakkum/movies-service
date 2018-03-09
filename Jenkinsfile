node("gcloud") {
    stage("clean") {
        deleteDir()
    }

    stage("checkout") {
        git url: "git@bitbucket.org:hbakkum/movies-service.git"
    }

    // Build the dropwizard docker image and upload this to container engine. The env variable 
    // 'DOCKER_GOOGLE_CREDENTIALS' points to the location of our gcloud service account key and 
    // is used by the spotify dockerfile plugin to authenticate to container registry
    stage("build") {
        withEnv(["GCLOUD_PROJECT=${GCLOUD_PROJECT}", "DOCKER_GOOGLE_CREDENTIALS=/var/jenkins/gcloud-accounts/${GCLOUD_PROJECT}-jenkins-slave-account.json"]) {
            sh "mvn clean deploy"
        }
    }

    stage("deploy") {
        // Configures kubectl with kubernetes cluster credentials and endpoint information
        sh "gcloud auth activate-service-account jenkins-slave@${GCLOUD_PROJECT}.iam.gserviceaccount.com --key-file /var/jenkins/gcloud-accounts/${GCLOUD_PROJECT}-jenkins-slave-account.json"
        sh "gcloud --project ${GCLOUD_PROJECT} container clusters get-credentials ${K8S_CLUSTER} --zone us-central1-a"
        
        // Apply the templated kubernetes config file containing the deployment and service, then wait for the rollout to complete
        sh "kubectl apply -f target/config/k8s-config.yaml"
        sh "kubectl rollout status deployment/movies-service"
    }
}
