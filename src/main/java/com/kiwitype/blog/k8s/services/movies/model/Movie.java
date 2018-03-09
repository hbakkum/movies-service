package com.kiwitype.blog.k8s.services.movies.model;

/**
 * @author Hayden Bakkum
 */
public class Movie {

    private String id;

    private String name;

    public Movie(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
