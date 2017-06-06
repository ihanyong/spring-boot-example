package com.ihanyong.springboot.exampler.more.controller;/**
 * Created by hanyong on 2017/6/5.
 */

import com.ihanyong.springboot.exampler.more.entity.TestEntity;
import com.ihanyong.springboot.exampler.more.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestRepository repository;

    @RequestMapping(value = "test/entity", method = RequestMethod.PUT)
    public TestEntity save(@RequestBody TestEntity dto) {
        TestEntity entity = repository.save(dto);
        return entity;
    }

    @Cacheable(cacheNames = "test_cache", key = "#p0")
    @RequestMapping(value = "test/entity/{id}" , method = RequestMethod.GET)
    public TestEntity get(@PathVariable Long id) {
        LOGGER.info("load {} from db.", id);
        return repository.findOne(id);
    }

    @CacheEvict(cacheNames = "test_cache", key = "#p0")
    @RequestMapping(value = "test/entity/{id}", method = RequestMethod.POST)
    public TestEntity update(@PathVariable Long id, @RequestBody TestEntity dto) {

        TestEntity entity = repository.findOne(id);
        entity.setName(dto.getName());

        return repository.save(entity);
    }

}
