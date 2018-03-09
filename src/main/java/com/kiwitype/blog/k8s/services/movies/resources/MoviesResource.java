package com.kiwitype.blog.k8s.services.movies.resources;

import com.google.common.collect.Lists;
import com.kiwitype.blog.k8s.services.movies.model.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Hayden Bakkum
 */
@Path("movies")
@Produces(MediaType.APPLICATION_JSON)
public class MoviesResource {

    @GET
    public List<Movie> getAllMovies() {
        return Lists.newArrayList(
            new Movie("1", "The Matrix"),
            new Movie("2", "The Terminator")
        );
    }

}
