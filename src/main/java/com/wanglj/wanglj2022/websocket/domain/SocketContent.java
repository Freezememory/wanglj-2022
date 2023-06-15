package com.wanglj.wanglj2022.websocket.domain;

import lombok.Data;

/**
 * @author Wanglj
 * @date 2023/6/15 10:27
 * @desc SocketContent
 */
@Data
public class SocketContent {

    private String userId = "1";

    private String[] userIds = new String[]{"1,2,3"};

    //业务类型
    private String cmd = "topic";

    //消息id
    private String msgId = "110";
    //消息内容
    private String msgTxt = "hello world";


}
