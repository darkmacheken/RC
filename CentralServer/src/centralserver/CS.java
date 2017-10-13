package centralserver;

import centralserver.connection.ServerTCP;
import centralserver.connection.ServerUDP;
import centralserver.exceptions.ConnectionException;
import centralserver.threads.ServerTCPThread;
import centralserver.threads.ServerUDPThread;

/**
 * Central server main thread
 */
public class CS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer cSPort = null;
        
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-p")) {
                    i++;
                    cSPort = Integer.parseInt(args[i]);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Erro de parâmetros.\n");
            return;
        }
        
        try {
            WSList list = new WSList();
            //create serverTCP and run thread
            ServerTCP server = new ServerTCP(cSPort);
            System.out.println("Server TCP created.");
            ServerTCPThread threadTCP = new ServerTCPThread(list, server);
            threadTCP.start();
            //create serverUDP and run thread
            ServerUDP serverUDP = new ServerUDP(cSPort);
            System.out.println("Server UDP created.");
            ServerUDPThread threadUDP = new ServerUDPThread(list, serverUDP);
            threadUDP.start();
            
            threadTCP.join();
        }
        catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
        }
        catch (InterruptedException e) {
            ;
        }
    }
    
}
