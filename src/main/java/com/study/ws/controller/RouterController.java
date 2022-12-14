package com.study.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.ws.Servie.ChatSessionService;
import com.study.ws.constant.CommonConstant;
import com.study.ws.entity.User;
import com.study.ws.exception.GlobalException;
import com.study.ws.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 路由接口控制器
 */
@Slf4j
@Controller
public class RouterController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ChatSessionService chatSessionService;

    /**
     * 登陆页面
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "login";
    }

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        Set<String> keys = redisTemplate.keys(CommonConstant.USER_PREFIX + CommonConstant.REDIS_MATCH_PREFIX);
//        if(keys!=null&&keys.size()>0){
//            for (String key : keys) {
//                User u = chatSessionService.findById(key);
//                if(u.getName().equals(user.getName())){
//                    throw new GlobalException("用户名已存在");
//                }
//            }
//        }
//        redisTemplate.boundValueOps(CommonConstant.USER_PREFIX + user.getId()).set(JSONObject.toJSONString(user));
//        return new R();

        if (keys != null && keys.size() > 0) {
            keys.forEach(key -> {
                User entity = chatSessionService.findById(key);
                if (entity != null) {
                    if ((entity.getName()).equals(user.getName())) {
                        throw new GlobalException("用户名已存在");
                    }
                }
            });
        }
        redisTemplate.boundValueOps(CommonConstant.USER_PREFIX + user.getId()).set(JSONObject.toJSONString(user));
        return new R();
    }

    /**
     * 首页入口
     *
     * @return
     */
    @GetMapping("/{id}/chat")
    public String index(@PathVariable("id") String id) {
        User user = chatSessionService.findById(id);
        if (user == null) {
            return "redirect:/";
        }
        return "index";
    }
}
