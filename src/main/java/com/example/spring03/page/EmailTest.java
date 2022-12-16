//package com.example.spring03.page;
//
//import javax.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//
//import com.example.spring03.service.EmailSenderService;
//
//public class EmailTest {
//    
//    @Autowired
//    private EmailSenderService senderService;
//    
//    public static void main(String[] args) {
//    
//        }
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail() throws MessagingException {
//        senderService.sendEmail(
//                "sangjunkim23@gmail.com", 
//                "This is subject", 
//                "this is body of email");
//    }
//
//}
