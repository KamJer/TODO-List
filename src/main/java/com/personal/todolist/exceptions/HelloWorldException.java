package com.personal.todolist.exceptions;

public class HelloWorldException extends Exception{

    public HelloWorldException() {
        super("more than one element in the list");
    }
}
