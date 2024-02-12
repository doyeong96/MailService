package versionOne;

import javax.mail.Session;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("메일링 테스트 입니다");

        String title = "메일링 Test 입니다.";
        String content = "테스트 메일 입니다.";
        String senderId = ""; // 보내는 사람
        String senderPassword = ""; // 2차인증을 설정한 경우 어플리케이션 비밀번호를 입력
        String receiver = ""; // 받는 사람
        String cc = ""; // 참조

        MailVersionOne mailVersionOne = new MailVersionOne();

        Session session = mailVersionOne.settingSession(new Properties(), senderId, senderPassword);

        mailVersionOne.sendMail(session, title, content, senderId, receiver, cc);
    }
}
