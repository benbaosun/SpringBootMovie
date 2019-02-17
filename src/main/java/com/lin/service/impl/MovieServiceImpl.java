package com.lin.service.impl;

import com.github.pagehelper.PageHelper;
import com.lin.mapper.MovieMapper;
import com.lin.model.Movie;
import com.lin.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 电影服务
 * @author lkmc2
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> getHotMovie(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        // 使用分页插件
        PageHelper.startPage(page, pageSize);

        // 创建查询条件（热度为1，根据发布日期降序排列）
        Example example = new Example(Movie.class);
        example.setOrderByClause("publish_date desc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hot", 1);

        return movieMapper.selectByExample(example);
    }

}