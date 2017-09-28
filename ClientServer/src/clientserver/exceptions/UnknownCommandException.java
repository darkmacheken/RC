/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver.exceptions;

/**
 * Unknown command client server
 */
public class UnknownCommandException extends Exception {

    private static final long serialVersionUID = 270920172255L;
    private String _unknownCommand;

    public UnknownCommandException(String unknownCommand){
        _unknownCommand=unknownCommand;
    }

    @Override
    public String toString(){
        return _unknownCommand;
    }
}
