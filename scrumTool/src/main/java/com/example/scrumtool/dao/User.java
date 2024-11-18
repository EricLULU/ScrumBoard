package com.example.scrumtool.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("scrum_user_tbl")
public class User {
    
    @TableId
    private Integer id;
    
    private String name;
    private String role;
    private String email;
    private String password;
}
