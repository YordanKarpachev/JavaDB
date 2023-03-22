package com.example.exercisespringdataautomappingobjects.entities.users;

import com.example.exercisespringdataautomappingobjects.exeption.RegisterException;

public class RegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;
    String fullName;

    public RegisterDTO(String[] args) {
        this.email = args[1];
        this.password = args[2];
        this.confirmPassword = args[3];
        this.fullName = args[4];

        this.validation();

    }

    private void validation() {

        int indexOfDot = email.lastIndexOf(".");
        int indexOfMail = email.indexOf("@");
        if (indexOfMail < 0 || indexOfDot < indexOfMail) {
            throw new RegisterException("Invalid Email");
        }

        if (!password.equals(confirmPassword) || password.length() < 5) {
            throw new RegisterException("Invalid Password");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}


