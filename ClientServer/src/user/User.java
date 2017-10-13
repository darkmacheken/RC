/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import user.protocols.ParseProtocolClientCS;
import user.protocols.ProtocolClientCS;
import user.exceptions.ConnectionException;
import user.exceptions.ExitCommandException;
import user.exceptions.ProtocolErrorException;
import user.exceptions.UnknownCommandException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class User {
    private static Scanner _in = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cSName = null;
        Integer cSPort = null;
        ConnectionTCP client = null;
        ProtocolClientCS protocol;
        ParseProtocolClientCS parser;


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
            showText("Parameter error.\n");
            return;
        }

        protocol = new ProtocolClientCS();

            while (true) {
                System.out.print("> ");
                String command = getText();
                try {
                    //create protocol message
                    String commandP = protocol.sendProtocol(command);     
                    //create connection tcp
                    client = new ConnectionTCP(cSName, cSPort);
                    //create connection parser
                    parser = new ParseProtocolClientCS(client);
                    //send command to server
                    client.send(commandP);
                    //receive message and apply protocol
                    String responseP = parser.parse();
                    protocol.receiveProtocol(responseP);
                    //close connection tcp
                    client.close();
                }
                catch (ProtocolErrorException e) {
                    showText(e.getErrorDescription());
                }
                catch (UnknownCommandException e) {
                    showText("Unknown Command:" + e.toString() + "\n");
                }
                catch (ExitCommandException e) {
                    try {
                        if(client != null)
                            client.close();
                        return;
                    }
                    catch (ConnectionException ce) {
                        showText(ce.getErrorDescription());                        
                        return;
                    }
                }
                catch (ConnectionException e) {
                    showText(e.getErrorDescription());
                }
            }
    }

    /**
     *
     * @param text
     */
    public static void showText(String text) {
        System.out.print(text);
    }

    /**
     *
     * @return
     */
    public static String getText() {
        return _in.nextLine();
    }

}
