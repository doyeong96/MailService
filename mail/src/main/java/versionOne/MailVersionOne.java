package versionOne;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailVersionOne {

    final String PORT = "465";
    final String SMTP = "smtp.naver.com";

    public Session settingSession(Properties properties, String id, String password){

        // Session session = null;

        try {

            properties.put("mail.smtp.host", SMTP); // smtp 서버
            properties.put("mail.smtp.socketFactory.port", PORT); // smtp port
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 네이버메일은 ssl
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", PORT);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(id, password);
                }
            });

            return session;

        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("세션생성 실패");

            return null;
        }

    }

    public void sendMail(Session session, String title, String content, String sender, String receiver, String cc){

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender)); // 발신자 설정
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); // 메세지를 전달할 단일 사용자
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc)); // 메일 참조
            message.setSubject(title); // 제목 설정
            message.setContent(content, "text/html; charset=utf-8"); // 내용, 컨텐츠 타입
            // message.setText(); // setText 로는 text만 전달 가능함


            Transport.send(message); // 메일 전송

            System.out.println(receiver + "에게 메일 전송");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(receiver + "에게 메일 전송 실패");
        }

    }
}
