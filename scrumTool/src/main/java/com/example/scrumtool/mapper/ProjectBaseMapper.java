package com.example.scrumtool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scrumtool.dao.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProjectBaseMapper extends BaseMapper<ProjectInfo> {
}
