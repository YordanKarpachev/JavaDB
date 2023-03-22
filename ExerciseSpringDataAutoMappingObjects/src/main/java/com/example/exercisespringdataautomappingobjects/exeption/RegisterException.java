package com.example.exercisespringdataautomappingobjects.exeption;

public class RegisterException extends RuntimeException{

    public RegisterException(String reason){
        super(reason);
    }
}
