package com.dream.service;

import com.dream.common.Page;
import com.dream.po.Category;
import com.dream.po.Movie;
import com.dream.po.Query;

import java.util.List;

public interface MovieService {

    // 查询客户列表
    public Page<Movie> findMovieList(Query query);

    public Movie getMovieById(Integer id);

    public void deleteMovie(Integer id);

    public List<Category> selectCategory();

    public void updateMovie(Movie movie, String[] categoryIds);

    public void addMovie(Movie movie, String[] categoryIds);
}
