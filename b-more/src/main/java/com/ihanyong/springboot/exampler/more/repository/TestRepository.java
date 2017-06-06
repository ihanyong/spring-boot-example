package com.ihanyong.springboot.exampler.more.repository;/**
 * Created by hanyong on 2017/6/5.
 */

import com.ihanyong.springboot.exampler.more.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
