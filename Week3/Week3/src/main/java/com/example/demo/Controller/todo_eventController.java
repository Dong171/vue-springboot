package com.example.demo.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.todo_event;
import com.example.demo.mapper.todo_eventMapper;
import com.example.demo.vo.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo_event")
public class todo_eventController {

    @Resource
    todo_eventMapper todo_eventMapper;


    @GetMapping("/test")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<todo_event> wrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(todo_event::getId,search);
        }
        Page<todo_event> todo_eventPage=todo_eventMapper.findPage(new Page<>(pageNum,pageSize));
        return Result.success(todo_eventPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody todo_event event){
        todo_eventMapper.updateById(event);
        return Result.success();
    }
    @PostMapping
    public Result<?> save(@RequestBody todo_event event){
        todo_eventMapper.insert(event);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        todo_eventMapper.deleteByID(id);
        return Result.success();}

}
