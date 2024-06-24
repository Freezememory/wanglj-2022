package com.wanglj.wanglj2022.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Wanglj
 * @date 2021/7/21 16:11
 * 测试controller
 */
@Controller
@RequestMapping("/forward")
@Slf4j
public class ForwardController {
    /**
     * 统一认证中心授权token接口
     * @param paramMaps code  授权码  state   页面状态
     */
    @GetMapping("/codeLogin")
    public String codeLogin(@RequestParam Map<String, Object> paramMaps, HttpServletRequest request) {
        log.info("入参: {}", paramMaps.toString());
        if (ObjectUtil.isNull(paramMaps.get("code"))) {
            log.error("授权码不可为空, 登陆异常");
            return "授权码不可为空，登陆异常";
        }
        if (ObjectUtil.isNull(paramMaps.get("state"))) {
            log.error("页面状态码不可为空,登陆异常");
            return "页面状态码不可为空,登陆异常";
        }
        try {
            String token = IdUtil.fastUUID();
            String state = "101000004071";
            return "redirect:https://xxx.xxx.cn/login_tyrz.html?code=" + token + "&state=" + state;
        } catch (Exception e) {
            log.error("统一认证登录异常: ", e);
        }
        return "登录异常请联系管理员";
    }


}
