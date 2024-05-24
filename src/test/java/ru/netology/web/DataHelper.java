package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;

public class DataHelper {
    private DataHelper(){
    }

    @Value
    public static class AuthInfo{
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo(){return new AuthInfo("vasya", "qwerty123");}


    @Value
    public static class VerificationCode{
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo){
        return new VerificationCode("12345");
    }

    @Value
    public static class idCard{
        private String idFirstCard;
        private String idSecondCard;
    }
    public static idCard getIdCard(){
        return new idCard("92df3f1c-a033-48e6-8390-206f6b1f56c0", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class numberFirstCard{
        private String numberFirstCard;
    }

    public static numberFirstCard numberFirstCard(){
        return new numberFirstCard("5559 0000 0000 0001");
    }

    @Value
    public static class numberSecondCard{
        private String numberSecondCard;
    }
    public static numberSecondCard numberSecondCard(){
        return new numberSecondCard("5559 0000 0000 0002");
    }
}
