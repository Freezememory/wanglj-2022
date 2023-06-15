package com.wanglj.wanglj2022.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.wanglj.wanglj2022.websocket.domain.SocketContent;
import com.wanglj.wanglj2022.websocket.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wanglj
 * @date 2023/6/15 10:18
 * @desc WebSocketController
 */
@RestController
public class WebSocketController {

    @Autowired
    private WebSocket webSocket;

    @PostMapping("/socket/send")
    public void setWebSocket(@RequestBody SocketContent content) {
        String userId = content.getUserId();
        String[] userIds = content.getUserIds();

        //创建业务消息信息
        JSONObject obj = new JSONObject();
        obj.put("cmd", content.getCmd());//业务类型
        obj.put("msgId", content.getMsgId());//消息id
        obj.put("msgTxt", content.getMsgTxt());//消息内容
        //全体发送
        webSocket.sendAllMessage(obj.toJSONString());
        //单个用户发送 (userId为用户id)
        webSocket.sendOneMessage(userId, obj.toJSONString());
        //多个用户发送 (userIds为多个用户id，逗号‘,’分隔)
        webSocket.sendMoreMessage(userIds, obj.toJSONString());
    }


}
