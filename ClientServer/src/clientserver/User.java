/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import clientserver.exceptions.ClientException;
import clientserver.exceptions.ExitCommandException;
import clientserver.exceptions.ProtocolErrorException;
import clientserver.exceptions.UnknownCommandException;
import java.util.Scanner;

public class User {
    private static Scanner _in = new Scanner(System.in);
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
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
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
                System.out.print("> ");
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
                    showText("Unknown Command:" + e.toString());
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
        System.out.print(text);
    }

    public static String getText() {
        return _in.nextLine();
    }

}
