package clientserver;

import clientserver.ProtocolException;
import clientserver.UnknownCommand;
import clientserver.ExitCommand;

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
        catch (ArrayOutOfBoundsException e) {
            showText("Erro de parâmetros.");
        }

        ClientTCP client = new ClientTCP();
        ProtocolClientCS protocol = new ProtocolClientCS(); //THROWS ProtocolException -> UnknownCommand, ExitCommand

        try {
            while (true) {
                String command = getText();
                try {
                    String commandP = protocol.sendProtocol(command);
                }
                catch (UnknownCommand e) {
                    showText("oi user es cancr");
                }
                client.send(commandP);
                String responseP = client.receive();
                String response = protocol.receiveProtocol(responseP);
                showText(response);
            }
        }
        catch (ExitCommand e) {
            client.close();
        }
        catch (ProtocolException e) {
            // erro total e completamente F A T A L
        }
    }

    public static void showText(String text) {
        System.out.println(text);
    }

    public static String getText() {
        return System.console().readLine();
    }

}
