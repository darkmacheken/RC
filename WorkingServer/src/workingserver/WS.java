package workingserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import workingserver.connection.ConnectionTCP;
import workingserver.connection.ConnectionUDP;
import workingserver.connection.ServerTCP;
import workingserver.exceptions.ConnectionException;
import workingserver.tasks.ConvertTextToLowerCaseTask;
import workingserver.tasks.ConvertTextToUpperCaseTask;
import workingserver.tasks.FindLongestWordTask;
import workingserver.tasks.Task;
import workingserver.tasks.WordCountTask;
import workingserver.threads.ServerTCPConnectionThread;
import workingserver.threads.ShutdownThread;

/**
 * Main Thread WS, here the connection via UDP is made here.
 * Accepts connections and lauch ServerTCPConnectionThread
 */
public class WS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer wSPort = null;
        String cSName = null;
        Integer cSPort = null;
        ArrayList<Task> tasks = new ArrayList<Task>();

        Task[] allTasks = new Task[]{
            new WordCountTask(),
            new FindLongestWordTask(),
            new ConvertTextToUpperCaseTask(),
            new ConvertTextToLowerCaseTask()
        };

        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-p")) {
                    i++;
                    wSPort = Integer.parseInt(args[i]);
                }
                else if (args[i].equals("-n")) {
                    i++;
                    cSName = args[i];
                }
                else if (args[i].equals("-e")) {
                    i++;
                    cSPort = Integer.parseInt(args[i]);
                }
                else {
                    for (Task task : allTasks) {
                        if (task.isPTC(args[i])) {
                            tasks.add(task);
                            break;
                        }
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.print("Parameter error.\n");
            return;
        }
        
        if (tasks.isEmpty()) {
            System.out.print(Constants.ARG_LESS);
            return;
        }

        ConnectionUDP connectionUDP;
        try {
            connectionUDP = new ConnectionUDP(cSName, cSPort);
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
            return;
        }     

        String registerMessage = "REG ";
        for (Task task : tasks) {
            registerMessage = registerMessage + task.getPTC() + " ";
        }
        try {
            registerMessage = registerMessage + InetAddress.getLocalHost().getHostAddress() + " " + wSPort + "\n";
        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host.\n");
        }

        int counter = 0;
        while(counter < 3){
            try{
                connectionUDP.send(registerMessage);
                String received = connectionUDP.receive();
                if(received.equals("RAK OK\n"))
                    break;
                counter++;
            }
            catch(ConnectionException e) {
                counter++;
                 if(counter < 3){                    
                     System.err.println(e.getErrorDescription());
                 }
                 else{
                    System.err.println(e.getErrorDescription());
                    System.out.println("Couldn't connect to CS: " + connectionUDP.getNameToSend() + " " + connectionUDP.getPortToSend());
                    connectionUDP.close();
                    System.exit(0);
                 }                
            }
        }

        System.out.println("Server registered succesfully in CS: " + connectionUDP.getNameToSend() + " " + connectionUDP.getPortToSend());
        
        //Thread to run when program is shutdown
        Runtime.getRuntime().addShutdownHook(new ShutdownThread(connectionUDP, wSPort));
        
        ServerTCP server;
        try {
            server = new ServerTCP(wSPort);
            System.out.println("Server TCP created.");
        }
        catch (ConnectionException ex) {
            System.out.println(ex.getErrorDescription());
            return;
        }
        while (true) {
            try {
                ConnectionTCP connectionTCP = server.acceptSocket();
                ServerTCPConnectionThread connectionThread = new ServerTCPConnectionThread(connectionTCP, allTasks);
                System.out.println("Connected to client: " + connectionTCP.getName() + ":" + connectionTCP.getPort());
                connectionThread.start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
            catch (IOException ex) {
                Logger.getLogger(WS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
