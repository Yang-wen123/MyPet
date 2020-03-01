package com.company.Model.Inter;

import com.company.Bean.Receiver;
import com.company.Bean.Sender;

public interface Message {
    /***
     *
     * @param sender 发送方
     * @param receiver 接收方
     * @param message 消息
     */
    void sendMessage(Sender sender, Receiver receiver,String message);

    /***
     *
     * @param receiver 接收方
     * @param sender 发送方
     * @param message 消息
     */
    void receiveMessage(Receiver receiver,Sender sender,String message);
}
