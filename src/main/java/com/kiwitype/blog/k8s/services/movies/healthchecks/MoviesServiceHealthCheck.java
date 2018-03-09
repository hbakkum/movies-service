package com.kiwitype.blog.k8s.services.movies.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Hayden Bakkum
 */
public class MoviesServiceHealthCheck extends HealthCheck {

    private AtomicBoolean healthy;

    public MoviesServiceHealthCheck(AtomicBoolean healthy) {
        this.healthy = healthy;
    }

    @Override
    protected Result check() {
        return healthy.get() ? Result.healthy() : Result.unhealthy("Service is unhealthy");
    }

}
