/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

public class User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientTCP client = new ClientTCP();
        ProtocolClientCS protocol = new ProtocolClientCS(); //THROWS ProtocolException -> CommandExcept, ExitExcept
    }

}
