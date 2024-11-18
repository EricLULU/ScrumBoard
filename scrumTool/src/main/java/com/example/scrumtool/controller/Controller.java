package com.example.scrumtool.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.scrumtool.dao.ProjectInfo;
import com.example.scrumtool.dao.User;
import com.example.scrumtool.mapper.ProjectBaseMapper;
import com.example.scrumtool.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    
    @Autowired
    ProjectBaseMapper projectBaseMapper;
    
    @Autowired
    UserMapper userMapper;
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
    
    //注册接口
    @PostMapping("register")
    public String register(@RequestBody User user) {
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            logger.error("failed to insert the user info");
        }
        return HttpStatus.OK.toString();
    }
    
    //登入接口
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", user.getName());
        queryMap.put("password", user.getPassword());
       
        List<User> userList = userMapper.selectByMap(queryMap);
        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
