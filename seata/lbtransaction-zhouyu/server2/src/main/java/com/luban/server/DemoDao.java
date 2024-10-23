package com.luban.server;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DemoDao {

    @Insert("insert into t_test(name,create_time) values(#{name},NOW())")
    public void insert(@Param("name") String name);
}
