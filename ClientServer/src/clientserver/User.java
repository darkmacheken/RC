/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import clientserver.exceptions.ExitCommandException;
import clientserver.exceptions.UnknownCommandException;

public class User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cSName = null; // TODO
        String cSPort = null; // TODO 58000+GN

        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-n")) {
                    i++;
                    cSName = args[i];
                }
                else if (args[i].equals("-p")) {
                    i++;
                    cSPort = args[i];
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            showText("Erro de parâmetros.");
        }

        ClientTCP client = new ClientTCP();
        ProtocolClientCS protocol = new ProtocolClientCS(); //THROWS ProtocolException -> UnknownCommand, ExitCommand

        try {
            while (true) {
                String command = getText();
                try {
                    String commandP = protocol.sendProtocol(command);
                    client.send(commandP);
                    String responseP = client.receive();
                    protocol.receiveProtocol(responseP);
                }
                catch (UnknownCommandException e) {
                    showText("oi user es cancr");
                }
            }
        }
        catch (ExitCommandException e) {
            client.close();
        }
    }

    public static void showText(String text) {
        System.out.println(text);
    }

    public static String getText() {
        return System.console().readLine();
    }

}
