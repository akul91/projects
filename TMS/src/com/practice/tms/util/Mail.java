package com.practice.tms.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail
{
 public static void mail(){
      String to = "";//change accordingly
      String from = "";//change accordingly
      String host = "";//or IP address

     //Get the session object
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);

     //compose the message
      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
         message.setSubject("Request for Training");
         message.setText("Hello,\n\n Your employee has posted a new training. Please log in to your account to approve or reject the request.\n" +
         		"\nThanks and Regards,\n" +
         		"Training Administrator");

         // Send message
         Transport.send(message);
         //System.out.println("message sent successfully....");

      }catch (MessagingException mex) {mex.printStackTrace();}
   }
}