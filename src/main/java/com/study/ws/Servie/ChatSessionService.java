package com.study.ws.Servie;

import com.study.ws.entity.Message;
import com.study.ws.entity.User;

import java.util.List;

public interface ChatSessionService {

    /**
     * 根据ID从Redis中查询数据
     */
    User findById(String id);

    /**
     * 推送消息存储到redis缓存中
     * @param fromId
     * @param toId
     * @param message
     */
    void pushMessage(String fromId,String toId,String message);

    /**
     * 获取在线用户列表
     * @return
     */
    List<User> onlineList();

    /**
     * 获取公共消息内容
     * @return
     */
    List<Message> commonList();

    /**
     * 获取该用户与指定窗口的推送消息
     * @param fromId
     * @param toId
     * @return
     */
    List<Message> selfList(String fromId,String toId);

    /**
     * 删除指定id在redis中的存储的数据
     * @param id
     */
    void delete(String id);

}
