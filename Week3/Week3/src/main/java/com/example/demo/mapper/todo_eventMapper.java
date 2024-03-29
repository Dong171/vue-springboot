package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.todo_event;


public interface todo_eventMapper extends BaseMapper<todo_event> {
    Page<todo_event> findPage(Page<todo_event>page);
    todo_event geteventByID(Long id);
    void deleteByID(Long id);

}
