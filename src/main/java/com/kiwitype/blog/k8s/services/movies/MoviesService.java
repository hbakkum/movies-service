package com.kiwitype.blog.k8s.services.movies;

import com.kiwitype.blog.k8s.services.movies.healthchecks.MoviesServiceHealthCheck;
import com.kiwitype.blog.k8s.services.movies.resources.MoviesResource;
import com.kiwitype.blog.k8s.services.movies.tasks.MakeHealthyTask;
import com.kiwitype.blog.k8s.services.movies.tasks.MakeUnhealthyTask;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Hayden Bakkum
 */
public class MoviesService extends Application<MoviesServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MoviesService().run(args);
    }

    @Override
    public void run(MoviesServiceConfiguration configuration, Environment environment) {
        // flag used to control healthcheck
        AtomicBoolean healthy = new AtomicBoolean(true);

        environment.jersey().register(new MoviesResource());

        environment.healthChecks().register("movies-service-healthcheck", new MoviesServiceHealthCheck(healthy));

        environment.admin().addTask(new MakeHealthyTask(healthy));
        environment.admin().addTask(new MakeUnhealthyTask(healthy));
    }

}
