package com.antgul.antgul_android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static ValidationResult validateEmail(String email) {
        String regexEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (matcherEmail.matches()) {
            return new ValidationResult("", true);
        }

        String message;
        if (email.equals("")) {
            message = ("이메일을 입력해주세요");
        } else if (!matcherEmail.matches()) {
            message = ("이메일 형식에 어긋납니다");
        } else {
            message = ("이메일 형식에 어긋납니다");
        }
        return new ValidationResult(message, false);
    }

    public static ValidationResult validatePassword(String password, String passwordConfirm) {
        String regexPw = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$";                    // 숫자/영문/특수문자를 최소 1개를 포함하고 공백은 허용되지 않음 8~16글자
        Pattern patternPw = Pattern.compile(regexPw);
        Matcher matcherPw = patternPw.matcher(password);


        if (password.equals(passwordConfirm) && matcherPw.matches()) {
            return new ValidationResult("", true);
        }

        String message;
        if (password.length() <= 0) {
            message = "비밀번호를 입력해주세요.";
        } else if (!matcherPw.matches()) {
            message = "비밀번호 형식에 어긋납니다.";
        } else if (passwordConfirm.equals("")) {
            message = "비밀번호 확인을 입력해주세요.";
        } else if (!password.equals(passwordConfirm)) {
            message = "비밀번호가 일치하지 않습니다.";
        } else {
            message = "비밀번호 형식에 어긋납니다.";
        }

        return new ValidationResult(message, false);
    }

    public static ValidationResult validateNickName(String nickname) {
        String regexNick = "^[가-힣a-zA-Z0-9]{2,12}$";                                               // 한글/영문/숫자 포함 2~12
        Pattern patternNick = Pattern.compile(regexNick);
        Matcher matcherNick = patternNick.matcher(nickname);

        if (matcherNick.matches()) {
            return new ValidationResult("", true);
        }

        String message;
        if (nickname.equals("")) {
            message = ("닉네임을 입력해주세요");
        } else if (!matcherNick.matches()) {
            message = ("닉네임 형식에 어긋납니다");
        } else {
            message = "뭔진 모르겠지만, 닉네임이 이상합니다.";
        }

        return new ValidationResult(message, false);
    }


}
