package com.example.scrumtool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scrumtool.dao.User;
import org.apache.ibatis.annotations.Mapper;

//用户base mapper
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
