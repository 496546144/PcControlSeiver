package com.hbung.likun;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public class ClientInfo {
    private String name;
    private String ip;
    private StringBuilder allMessage = new StringBuilder();
    private String newMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return name + "  ip:" + ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public StringBuilder getAllMessage() {
        return allMessage;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public void addMessage(String message) {
        newMessage = message;
        allMessage.append(newMessage);
        allMessage.append("\n");
    }

}
