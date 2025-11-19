package com.driver;

import java.text.MessageFormat;
import java.util.*;

public class Gmail extends Email {

    //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    LinkedHashMap<Integer, Message> trash=new LinkedHashMap<>();
    int trashCounter=0;
    private int inboxCapacity;
    LinkedHashMap<Integer, Message> inbox;
    private int idCounter;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.inbox=new LinkedHashMap<>();
        this.idCounter=0;
    }


    public void receiveMail(Date date, String sender, String message){
        Message message1=new Message(date, sender, message);
        if(this.inbox.size()==this.inboxCapacity){
            Integer oldMail=this.inbox.keySet().iterator().next();
            trash.put(++trashCounter,this.inbox.get(oldMail));
            this.inbox.remove(oldMail);
        }
        this.inbox.put(++this.idCounter,message1);
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(this.inbox.isEmpty()){
            return;
        }
        Integer integer1=null;
        for (Integer integer: this.inbox.keySet()){
            Message message1=this.inbox.get(integer);
            if(message1.message.equals(message)){
                integer1=integer;
                //trash.put(++trashCounter,message1);
                break;
            }
        }
        if(integer1!=null){
            trash.put(++trashCounter,this.inbox.get(integer1));
            this.inbox.remove(integer1);
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(this.inbox.isEmpty()){
            return null;
        }
        Integer a=null;
        for (Integer integer: this.inbox.keySet()){
            a=integer;
        }
        if(a==null)return null;
        Message latest= this.inbox.get(a);
        return latest.message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(this.inbox.isEmpty()){
            return null;
        }
        Message oldest= this.inbox.values().iterator().next();
        return oldest.message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        // find number of mails in the inbox which are received between given dates (inclusive)
        int count = 0;
        if(this.inbox.isEmpty()){
            return 0;
        }

        for (Message message : this.inbox.values()) {
            Date date = message.date;
            if((date.equals(start) || date.after(start)) &&
                    (date.equals(end) || date.before(end))){
                count++;
            }
        }
        return count;
    }


    public int getInboxSize(){
        // Return number of mails in inbox
        return this.inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
//        if(this.trash.isEmpty()){
//            return;
//        }
//        for (Integer integer: this.trash.keySet()){
//            this.trash.remove(integer);
//        }
        this.trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
    class Message{
        private Date date;
        private String sender;
        private String message;

        public Message(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}