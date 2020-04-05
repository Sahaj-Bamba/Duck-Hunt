/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.MessageType;
import DuckHunt.Constant.Request;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class Message implements Serializable{
    
    private String from;
    private String to;
    private String content;
    private MessageType type;

    public Message(String from, String to, String content, MessageType type) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.type = type;
    }
    
    public Message(String content) {
        this.content = content;
    }
    
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public MessageType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.valueOf(Request.MESSAGE);
    }
    
}
