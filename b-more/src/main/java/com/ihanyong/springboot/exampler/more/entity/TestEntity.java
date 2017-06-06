package com.ihanyong.springboot.exampler.more.entity;/**
 * Created by hanyong on 2017/6/5.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
@Entity
@Table(name = "t_test")
public class TestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "test_id_gen")
    @TableGenerator(name = "test_id_gen", table = "sys_sqe", pkColumnName = "name", valueColumnName = "next_val",
            pkColumnValue = "t_test_id", initialValue = 1, allocationSize = 50)
    private Long id;

    private String name;

    @Version
    private Long versionNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Long versionNum) {
        this.versionNum = versionNum;
    }
}
