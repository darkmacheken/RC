/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import java.io.IOException;
import clientserver.exceptions.ClientException;
import clientserver.exceptions.ExitCommandException;
import clientserver.exceptions.UnknownCommandException;
import clientserver.exceptions.ProtocolErrorException;

public class User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cSName = null;
        Integer cSPort = null;
        ClientTCP client;
        ProtocolClientCS protocol;

        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-n")) {
                    i++;
                    cSName = args[i];
                }
                else if (args[i].equals("-p")) {
                    i++;
                    cSPort = Integer.parseInt(args[i]);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            showText("Erro de parâmetros.");
        }
        catch (NumberFormatException e) {
            showText("Erro de parâmetros.");
        }


        try {
            client = new ClientTCP(cSName, cSPort);
        }
        catch (ClientException e) {
            showText(e.getErrorDescription());
            return;
        }

        protocol = new ProtocolClientCS(); //THROWS ProtocolException -> UnknownCommand, ExitCommand

            while (true) {
                String command = getText();
                try {
                    String commandP = protocol.sendProtocol(command);
                    client.send(commandP);
                    String responseP = client.receive();
                    protocol.receiveProtocol(responseP);
                }
                catch (ProtocolErrorException e) {
                    showText(e.getErrorDescription());
                }
                catch (UnknownCommandException e) {
                    showText(e.toString());
                }
                catch (ExitCommandException e) {
                    try {
                        client.close();
                        return;
                    }
                    catch (ClientException ce) {
                        showText(ce.getErrorDescription());
                        return;
                    }
                }
                catch (ClientException e) {
                    showText(e.getErrorDescription());
                }
            }
    }

    public static void showText(String text) {
        System.out.println(text);
    }

    public static String getText() {
        return System.console().readLine();
    }

}
