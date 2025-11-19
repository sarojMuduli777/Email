package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(!oldPassword.equals(this.getPassword())) {
            return;
        }
        if(newPassword.length()<8){
            return;
        }
        char [] chars=newPassword.toCharArray();
        boolean isUpperCase=false;
        boolean isLowerCase=false;
        boolean isDigit=false;
        boolean isSpecial=false;
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                isDigit = true;
            } else if (ch >= 'a' && ch <= 'z') {
                isLowerCase = true;
            } else if (ch >= 'A' && ch <= 'Z') {
                isUpperCase = true;
            } else {
                isSpecial = true;
            }
        }
        if(isDigit && isSpecial && isLowerCase && isUpperCase){
            this.password=newPassword;
        }
    }
}