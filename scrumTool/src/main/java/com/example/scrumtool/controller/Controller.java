package com.example.scrumtool.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.scrumtool.dao.ProjectInfo;
import com.example.scrumtool.mapper.ProjectBaseMapper;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    
    @Autowired
    ProjectBaseMapper projectBaseMapper;
    /**
     * 更新小组信息
     */
    @PostMapping("update/{name}/{number}")
    public String updateGroupInfo(@PathVariable("name") String name,
                                    @PathVariable("number") int number) {
        logger.info("begin to update project info");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectName(name);
        projectInfo.setNumber(number);
        
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
    
        List<ProjectInfo> projectInfos = projectBaseMapper.selectList(queryWrapper);
        if (projectInfos == null  || projectInfos.size() == 0) {
            projectBaseMapper.insert(projectInfo);
        } else {
            String projectName = projectInfos.get(0).getProjectName();
            UpdateWrapper<ProjectInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("projectName", projectName);
            projectBaseMapper.update(projectInfo, updateWrapper);
        }
        logger.info("complete update project info");
        return HttpStatus.OK.toString();
    }
}
