package com.kiwitype.blog.k8s.services.movies.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Hayden Bakkum
 */
public class MakeHealthyTask extends Task {

    private AtomicBoolean healthy;

    public MakeHealthyTask(AtomicBoolean healthy) {
        super("make-healthy");

        this.healthy = healthy;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) {
        healthy.set(true);
    }
    
}
