node("gcloud") {
    // delete old workspace
    stage("clean") {
        deleteDir()
    }

    stage("checkout") {
        git url: "git@bitbucket.org:hbakkum/movies-service.git"
    }

    // build the dropwizard docker image and upload this to container engine
    stage("build") {
        withEnv(["GCLOUD_PROJECT=${GCLOUD_PROJECT}", "DOCKER_GOOGLE_CREDENTIALS=/var/jenkins/gcloud-accounts/${GCLOUD_PROJECT}-jenkins-slave-account.json"]) {
            sh "mvn clean deploy"
        }
    }

    stage("deploy") {
        sh "gcloud auth activate-service-account jenkins-slave@${GCLOUD_PROJECT}.iam.gserviceaccount.com --key-file /var/jenkins/gcloud-accounts/${GCLOUD_PROJECT}-jenkins-slave-account.json"
        sh "gcloud --project ${GCLOUD_PROJECT} container clusters get-credentials ${K8S_CLUSTER} --zone us-central1-a"
        sh "kubectl apply -f target/config/k8s-config.yaml"
        sh "kubectl rollout status deployment/movies-service"
    }

}