package chapter08.puzzle73.client;

import chapter08.puzzle73.library.Api;

public class Client {

    /*
    As written, the program compiles without error.
    If we uncomment the private declaration of the local class String in library.Api,
    the method Api.newString no longer has the return type java.lang.String,
    so the initialization of the variable Client.s fails to compile:
     */
    String s = Api.newString();

    /*
    As written, this program compiles without error.
    If we uncomment the private declaration in library.Api, the client fails to compile:
     */
    int answer = Api.ANSWER;
}
