package com.example.client.model.discussion;

import com.example.client.model.utilisateur.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.example.client.service.connection.EConnection;
import org.springframework.stereotype.Component;

@Component
public class Messages {
    String idMessages;
    Discussion discussion;
    Utilisateur sender;
    String messages;
    Date dateEnvoie;

    public Messages(String idMessages,Discussion discussion, Utilisateur sender, String messages, Date dateEnvoie) {
        this.setIdMessages(idMessages);
        this.setDiscussion(discussion);
        this.setSender(sender);
        this.setMessages(messages);
        this.setDateEnvoie(dateEnvoie);
    }
    public Messages(String idMessages) {
        this.setIdMessages(idMessages);
    }
    public Messages() {
    }
    
    public String getIdMessages() {
        return idMessages;
    }
    public void setIdMessages(String idMessages) {
        this.idMessages = idMessages;
    }

    public Discussion getDiscussion() {
        return discussion;
    }
    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public Utilisateur getSender() {
        return sender;
    }
    public void setSender(Utilisateur sender) {
        this.sender = sender;
    }

    public String getMessages() {
        return messages;
    }
    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }
    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    public boolean isDeletable() {
        return false;
    }
    public boolean isUpdatable() {
        return false;
    }
    public boolean isInsertable() {
        boolean response = this != null  && this.getDiscussion() != null && this.getDiscussion().getIdDiscussion() != null && !this.getDiscussion().getIdDiscussion().isEmpty() && this.getSender() != null && this.getSender().getIdUtilisateur() != null && !this.getSender().getIdUtilisateur().isEmpty() && this.getMessages() != null && !this.getMessages().isEmpty() && this.getDateEnvoie() != null ;
        return response;
    }  
}