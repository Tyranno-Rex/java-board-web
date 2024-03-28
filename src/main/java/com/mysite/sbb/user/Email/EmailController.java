package com.mysite.sbb.user.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@PropertySource("classpath:application.properties")
public class EmailController {
    private static String type = "text/html; charset=utf-8";
    private static String emailAdd = "jsilvercastle@gmail.com";
    private static String password = "rjuo zhca wxkp jbpn";

    public static void sendEmail(String username, String email, String newPassword) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAdd, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAdd,"발신자이름"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("새로운 비밀번호 발급");
            message.setContent("안녕하세요, " + username + "님. 새로운 비밀번호는 " + newPassword + "입니다.", type);
            Transport.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
