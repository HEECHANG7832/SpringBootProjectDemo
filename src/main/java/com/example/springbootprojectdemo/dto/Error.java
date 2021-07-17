package com.example.springbootprojectdemo.dto;

public class Error {

    private String field;
    private String message;
    private String inValidValue;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInValidValue() {
        return inValidValue;
    }

    public void setInValidValue(String inValidValue) {
        this.inValidValue = inValidValue;
    }

}
