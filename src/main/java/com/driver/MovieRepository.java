package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> movieDirectorMap;

    public MovieRepository(){
        movieMap=new HashMap<>();
        directorMap=new HashMap<>();
        movieDirectorMap=new HashMap<>();
    }
    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        directorMap.put(director.getName(),director);
    }

    public void createMovieDirectorPair(String movie,String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currentMovies= new ArrayList<>();
            if(movieDirectorMap.containsKey(director)) currentMovies=movieDirectorMap.get(director);
            currentMovies.add(movie);
            movieDirectorMap.put(director,currentMovies);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieDirectorMap.get(director);
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        List<String> movies=new ArrayList<>();
        if(movieDirectorMap.containsKey(director)){
            movies=movieDirectorMap.get(director);
            for(String movie:movies){
                movieMap.remove(movie);
            }
        }
        movieDirectorMap.remove(director);

        if(directorMap.containsKey(director)) directorMap.remove(director);
    }
    public void deleteDirector(){
        List<String> movies=new ArrayList<>();
        for(String director:movieDirectorMap.keySet()){
            movies=movieDirectorMap.get(director);
           for(String movie: movies){
               if(movieMap.containsKey(movie)) movieMap.remove(movie);
           }
        }
        movieDirectorMap.clear();
        directorMap.clear();
    }
}
