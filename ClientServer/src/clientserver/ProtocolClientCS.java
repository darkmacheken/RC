/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import clientserver.exceptions.ConnectionException;
import clientserver.exceptions.ExitCommandException;
import clientserver.exceptions.ProtocolErrorException;
import clientserver.exceptions.UnknownCommandException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.max;
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

        switch (splitedSentence[0]){
            case "exit":
                throw new ExitCommandException();
            case "list":
                return "LST\n";
            case "request":
                return this.requestSendProtocol(splitedSentence);
            default:
                throw new UnknownCommandException(splitedSentence[0]);
        }
    }

    /**
     *
     * @param sentence
     * @throws ConnectionException
     */
    public void receiveProtocol(String sentence) throws ConnectionException {
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, max(0,sentence.length()-1));
        
        String[] splitedSentence = sentence.split(" ");

        if(splitedSentence.length == 0)
            throw new ConnectionException(Constants.REQ_NULL);

        if(splitedSentence.length == 1)
            throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
       
            
        switch (splitedSentence[0]){
            case "FPT":
                switch (splitedSentence[1]) {
                    case "EOF":
                        throw new ConnectionException(Constants.REQ_EOF);
                    case "ERR":
                        throw new ConnectionException(Constants.REQ_ERR);
                    default:
                        int numberPTC = Integer.parseInt(splitedSentence[1]);
                        if(splitedSentence.length != numberPTC+2)
                            throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
                        for(int i = 0, count=1; i<numberPTC; i++){
                            String ptc = splitedSentence[i+2];
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
                break;

            case "REP":
                if(splitedSentence.length < 2)
                     throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);

                switch (splitedSentence[1]){
                    case "EOF":
                        throw new ConnectionException(Constants.REQ_EOF);
                    case "ERR":       
                        throw new ConnectionException(Constants.REQ_ERR);
                    case "R": //report of performed task
                        int size = Integer.parseInt(splitedSentence[2]);
                        String data = splitedSentence[2].substring(0, size);

                        System.out.println(data);
                        break;

                    case "F": //processed text file
                        int size2 = Integer.parseInt(splitedSentence[2]);
                        String data2 = splitedSentence[3].substring(0, size2);

                        String[] fileName = _fileName.split("\\.");

                        String finalNameFile = fileName[0] + "_" + _ptc + "." + fileName[1];
                        try {
                        PrintWriter out = new PrintWriter(
                                                    new BufferedWriter(
                                                            new FileWriter(finalNameFile)));
                        out.print(data2);
                        System.out.println("received file " + finalNameFile + "\n\t" + size2 + " Bytes");
                        out.close();
                        }
                        catch (IOException e) {
                            throw new ConnectionException("");
                        }                        
                        break;
                    default:
                        throw new ProtocolErrorException(Constants.PT_NFOLLOW,sentence);
                }
                break;
            case "ERR":
                throw new ConnectionException(Constants.REQ_ERR);
                
            default:
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
