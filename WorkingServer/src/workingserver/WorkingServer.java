/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
 *
 * @author Pedro Daniel
 */
public class WorkingServer {

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
            System.out.println("Erro de parâmetros.\n");
            return;
        }

        ConnectionUDP connectionUDP;
        try {
            connectionUDP = new ConnectionUDP(cSName, cSPort);
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
            return;
        }

        Runtime.getRuntime().addShutdownHook(new ShutdownThread(connectionUDP, wSPort));

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
            catch(SocketTimeoutException e){
                if(counter < 3)
                    counter++;
                else
                    return;
            }
            catch(ConnectionException e) {
                 if(counter < 3){
                     counter++;
                     System.err.println(e.getErrorDescription());
                 }
                else
                    return;     
            }
        }
        
        System.out.println("Server registered succesfully in CS: " + connectionUDP.getNameToSend() + " " + connectionUDP.getPortToSend());

        ServerTCP server;
        try {
            server = new ServerTCP(wSPort);
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
        }

    }

}
