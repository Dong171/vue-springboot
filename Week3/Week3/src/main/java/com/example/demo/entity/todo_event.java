package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="todo_event")
public class todo_event {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String title;
    private String zhuangtai;
    private String explanation;
    private Integer user_id;
}
