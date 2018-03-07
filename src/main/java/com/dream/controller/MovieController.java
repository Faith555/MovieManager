package com.dream.controller;


import com.dream.common.Page;
import com.dream.po.Category;
import com.dream.po.Movie;
import com.dream.po.Query;
import com.dream.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie")
    public String showMovie() {
        return "redirect:/movie/list.action";
    }

    // 客户列表
    @RequestMapping(value = "/movie/list")
    public String list(Query query, Model model) {

        Page<Movie> movies = movieService.findMovieList(query);
        model.addAttribute("page", movies);
        List<Category> categorylist = movieService.selectCategory();
        model.addAttribute("categoryList", categorylist);
        //客户来源
//        List<BaseDict> fromType = systemService.findBaseDictListByType(FROM_TYPE);
        //客户所属行业
//        List<BaseDict> industryType = systemService.findBaseDictListByType(INDUSTRY_TYPE);
        //客户级别
//        List<BaseDict> levelType = systemService.findBaseDictListByType(LEVEL_TYPE);
//        model.addAttribute("fromType", fromType);
//        model.addAttribute("industryType", industryType);
//        model.addAttribute("levelType", levelType);
        //参数回显
        model.addAttribute("movieName", query.getMovieName());
        model.addAttribute("categoryId", query.getCategoryId());
//        model.addAttribute("custIndustry", custIndustry);
//        model.addAttribute("custLevel", custLevel);
        return "movieManage";
    }

    @RequestMapping("/movie/delete")
    @ResponseBody
    public String customerDelete(Integer id) {
        movieService.deleteMovie(id);
        return "OK";
    }

    @RequestMapping("/movie/edit")
    @ResponseBody
    public Movie getMovieById(Integer id) {
        Movie movie = movieService.getMovieById(id);
        return movie;
    }

    @RequestMapping("/movie/update")
    @ResponseBody
    public String updateMovie(Movie movie, HttpServletRequest request) {
        String[] categoryIds = request.getParameterValues("categoryId");
        movieService.updateMovie(movie, categoryIds);
        return "OK";
    }


    @RequestMapping("/movie/add")
    @ResponseBody
    public String addMovie(Movie movie, HttpServletRequest request) {
        String[] categoryIds = request.getParameterValues("categoryId");
        movieService.addMovie(movie, categoryIds);
        return "OK";
    }
}
