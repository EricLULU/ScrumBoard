package com.example.scrumtool.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("scrum_tool_tbl")
public class ProjectInfo {
    @TableId
    private Integer id;
    
    // the name of project
    @TableField("projectName")
    private String projectName;
    
    //number of group member
    private int number;
}
