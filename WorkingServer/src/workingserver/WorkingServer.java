/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
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

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                String unregMessage = "UNR " + InetAddress.getLocalHost().getHostAddress() + " " + wsPort + "\n";
                int counter = 0;
                while(counter < 3){
                    try{
                        connectionUDP.send(unregMessage);
                        String received = connectionUDP.receive();
                        if(received.equals("UAK OK\n"))
                            break;
                        counter++;
                    }
                    catch(SocketTimeoutException e){
                        counter++;
                    }
                }
            }
        });

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
        }
        catch (ConnectionException ex) {
            System.out.println(ex.getErrorDescription());
            return;
        }

        String registerMessage = "REG ";
        for (Task task : tasks) {
            registerMessage = registerMessage + task.getPTC() + " ";
        }
        registerMessage = registerMessage + InetAddress.getLocalHost().getHostAddress() + " " + wSPort + "\n";

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
                counter++;
            }
            catch (ConnectionException ex) {
                System.out.println(ex.getErrorDescription());
            }
        }

        while (true) {
            try {
                ServerTCP server = new ServerTCP(wSPort);
                ConnectionTCP connectionTCP = server.acceptSocket();
                ServerTCPConnectionThread connectionThread = new ServerTCPConnectionThread(connectionTCP, allTasks);
                connectionThread.start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
        }

    }

}
