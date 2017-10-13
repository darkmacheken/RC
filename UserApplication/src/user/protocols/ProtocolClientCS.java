/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.protocols;

import user.Constants;
import user.exceptions.ConnectionException;
import user.exceptions.ExitCommandException;
import user.exceptions.ProtocolErrorException;
import user.exceptions.UnknownCommandException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Class Protocol Client-Cs
 */
public class ProtocolClientCS{
    private Map<String,String> _descriptionPTC;
    private String _fileName = null;
    private String _ptc=null;

    /**
     * Constructor
     */
    public ProtocolClientCS() {
        _descriptionPTC = new HashMap<String,String>();

        _descriptionPTC.put("WCT", "word count");
        _descriptionPTC.put("FLW", "find longest word");
        _descriptionPTC.put("UPP", "convert text to upper case");
        _descriptionPTC.put("LOW", "convert text to lower case");
    }

    /**
     *
     * @param sentence
     * @return
     * @throws UnknownCommandException
     * @throws ExitCommandException
     * @throws ConnectionException
     */
    public String sendProtocol(String sentence) throws UnknownCommandException,
                                                       ExitCommandException,
                                                       ConnectionException{
        String[] splitedSentence = sentence.split(" ");

        if(splitedSentence.length==0)
            throw new UnknownCommandException("");

        if (splitedSentence[0].equals("exit"))
            throw new ExitCommandException();
        else if (splitedSentence[0].equals("list"))
            return "LST\n";
        else if (splitedSentence[0].equals("request"))
            return this.requestSendProtocol(splitedSentence);
        else
            throw new UnknownCommandException(splitedSentence[0]);
    }

    /**
     *
     * @param sentence
     * @throws ConnectionException
     */
    public void receiveProtocol(String sentence) throws ConnectionException {
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, 0 > sentence.length()-1 ? 0 : sentence.length()-1);

        String[] splitedSentence = sentence.split(" ", 2);

        if(splitedSentence.length == 0)
            throw new ConnectionException(Constants.REQ_NULL);

        if(splitedSentence.length == 1)
            throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
       
        String[] arguments;
        if (splitedSentence[0].equals("FPT")) {
                arguments = splitedSentence[1].split(" ");
                switch (arguments[0]) {
                    case "EOF":
                        throw new ConnectionException(Constants.REQ_EOF);
                    case "ERR":
                        throw new ConnectionException(Constants.REQ_ERR);
                    default:
                        int numberPTC = Integer.parseInt(arguments[0]);
                        if(arguments.length != numberPTC+1)
                            throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
                        for(int i = 1, count=1; i<=numberPTC; i++){
                            String ptc = arguments[i];
                            String description = _descriptionPTC.get(ptc);
                            Set<String> ptcShowed = new HashSet<String>();

                            if(!ptcShowed.contains(ptc)){
                                System.out.println(count + "- " + ptc + " - " + description);
                                count++;
                                ptcShowed.add(ptc);
                            }
                        }
                        break;
                }
        }
        else if (splitedSentence[0].equals("REP")) {
                arguments = splitedSentence[1].split(" ",3);
                if(arguments.length == 0)
                     throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);

                switch (arguments[0]){
                    case "EOF":
                        throw new ConnectionException(Constants.REQ_EOF);
                    case "ERR":       
                        throw new ConnectionException(Constants.REQ_ERR);
                    case "R": //report of performed task
                        try{
                            int size = Integer.parseInt(arguments[1]);
                            String data = arguments[2].substring(0, size);

                            if(_ptc.equals("WCT")){
                                int numberWords = Integer.parseInt(data);
                                System.out.println("Number of words: " + numberWords);
                            }
                            else if(_ptc.equals("FLW")){
                                System.out.println("Longest words: " + data);
                            }
                            else
                                throw new ConnectionException(Constants.REQ_ERR);
                        }
                        catch(NumberFormatException e){
                            throw new ConnectionException(Constants.REQ_ERR);
                        }
                        break;

                    case "F": //processed text file
                        int size2 = Integer.parseInt(arguments[1]);
                        String data2 = arguments[2].substring(0, size2);

                        String[] fileName = _fileName.split("(\\.)(?!.*\\.)");

                        String finalNameFile = fileName[0] + "_" + _ptc + "." + fileName[1];
                        try {
                        PrintWriter out = new PrintWriter(
                                                    new BufferedWriter(
                                                            new FileWriter(finalNameFile)));
                        out.print(data2);
                        System.out.println("received file " + finalNameFile + "\n\t" + size2 + " Bytes received.");
                        out.close();
                        }
                        catch (IOException e) {
                            throw new ConnectionException("");
                        }                        
                        break;
                    default:
                        throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
                }
        }
        else if (splitedSentence[0].equals("ERR")) {
                throw new ConnectionException(Constants.REQ_ERR);
        }
        else {
                throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
        }
        _fileName=null;
        _ptc=null;
    }

    private String requestSendProtocol(String[] splitedSentence) throws ConnectionException{
        if(splitedSentence.length < 3)
            throw new ConnectionException(Constants.ARG_LESS);
        else if (splitedSentence.length>3)
            throw new ConnectionException(Constants.ARG_HIGH);

        if(splitedSentence[1].length()!=3)
            throw new ConnectionException(Constants.ARG_PTCERR);

        _ptc = splitedSentence[1];
        _fileName = splitedSentence[2];
        String protocolSend = "REQ " + _ptc + " ";

        //Trying to open file
        String fileText = new String();
        try{
            Scanner file = new Scanner(new File(_fileName));
            fileText = file.useDelimiter("\\A").next();
            file.close();
        }
        catch (FileNotFoundException ex) {
            throw new ConnectionException(Constants.FILE_NFOUND);
        }

        int size = fileText.length();

        protocolSend= protocolSend + size + " " + fileText + "\n";

        System.out.println("\t" + size + " Bytes to transmit.");

        return protocolSend;
    }
}
