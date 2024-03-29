package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.todo_event;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.Result;
import com.example.demo.entity.User;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/login")
    @ResponseBody
    public Result<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password));
        if(res == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success();
    }
    @RequestMapping("/register")
    @ResponseBody
    public Result<?> register(@RequestParam("username") String username, @RequestParam("password") String password){
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,username));
        if(res != null){
            return Result.error("用户名或密码错误");
        }
        if(password==null){
            password="123456";
        }
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        userMapper.insert(user);
        return Result.success();
    }

    @GetMapping("/test")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickname,search);
        }
        Page<User> UserPage=userMapper.findPage(new Page<>(pageNum,pageSize));
        return Result.success(UserPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }
    @PostMapping
    public Result<?> save(@RequestBody User user){
        userMapper.insert(user);
        return Result.success();
    }
    @DeleteMapping("/{id} ")
    public Result<?> delete(@PathVariable long id){
        userMapper.deleteByID(id);
        return Result.success();

    }
}
