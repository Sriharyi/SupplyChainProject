package com.example.supplychain.exception;

public class EntityNotNullException  extends RuntimeException{
    public EntityNotNullException(){
        
    }
    public EntityNotNullException(String message){
        super(message);
    }
}
