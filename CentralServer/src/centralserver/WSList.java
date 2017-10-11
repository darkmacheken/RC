/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author duartegalvao
 */
public class WSList {
    ConcurrentHashMap<String, LinkedBlockingDeque<ConnectAddress>> _struct;

    
    /**
     * Create class
     */
    public WSList() {
        _struct = new ConcurrentHashMap<String, LinkedBlockingDeque<ConnectAddress>>();
    }

    /**
     *
     * @return
     */
    public synchronized List<String> getPTCs() {
        ArrayList<String> pTCs = new ArrayList<String>();
               
        for(String ptc: _struct.keySet()){
            pTCs.add(ptc);
        }
        return pTCs;
    }
    
    /**
     *
     * @param pTC
     * @return
     */
    public synchronized ConnectAddress[] getIPs(String pTC){  
        LinkedBlockingDeque<ConnectAddress> ips = _struct.get(pTC);
        
        if(ips == null)
            return null;
        else
            return (ConnectAddress[]) ips.toArray();
    }
    
    /**
     *
     * @param pTC
     * @param ip
     * @return
     */
    public synchronized void addIP(String[] pTCs, ConnectAddress connectAddress){
        for (String ptc : pTCs) {
            if (!_struct.contains(ptc)) {
                _struct.put(ptc, new LinkedBlockingDeque<ConnectAddress>());
            }
            LinkedBlockingDeque<ConnectAddress> deque = _struct.get(ptc);
            if(!deque.contains(connectAddress))
                deque.add(connectAddress);
        }
    }
    
    /**
     *
     * @param ip
     * @return
     */
    public synchronized List<String> removeIP(ConnectAddress connectAddress){
        List<String> ptcList = new ArrayList<String>();
        for(String ptc: _struct.keySet()){
            boolean result = _struct.get(ptc).remove(connectAddress);
            if(result)
                ptcList.add(ptc);
        }
        return ptcList;
    }
    
}
