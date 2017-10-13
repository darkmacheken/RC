package centralserver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *  
 */
public class WSList {
    private ConcurrentHashMap<String, ConcurrentHashMap<ConnectAddress, String>> _struct;

    
    /**
     * Create class
     */
    public WSList() {
        _struct = new ConcurrentHashMap<String, ConcurrentHashMap<ConnectAddress, String>>();
    }

    /**
     *
     * @return
     */
    public synchronized List<String> getPTCs() {
        LinkedList<String> pTCs = new LinkedList<String>();
               
        for(String ptc: _struct.keySet()){
            if(!_struct.get(ptc).isEmpty())
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
        ConcurrentHashMap<ConnectAddress, String> ips = _struct.get(pTC);
        List<ConnectAddress> addresses = new LinkedList<ConnectAddress>();
                
        if(ips == null)
            return null;
        else{
            for(ConnectAddress ip: ips.keySet())
                addresses.add(ip);
            
            ConnectAddress[] res = new ConnectAddress[addresses.size()];
            addresses.toArray(res);
            return res;
        }
    }
    
    /**
     *
     * @param pTCs
     * @param connectAddress
     */
    public synchronized void addIP(String[] pTCs, ConnectAddress connectAddress){
        for (String ptc : pTCs) {
            _struct.putIfAbsent(ptc, new ConcurrentHashMap<ConnectAddress, String>());
            ConcurrentHashMap<ConnectAddress, String> map = _struct.get(ptc);
            map.putIfAbsent(connectAddress, "");
        }
    }
    
    /**
     *
     * @param connectAddress
     * @return
     */
    public synchronized List<String> removeIP(ConnectAddress connectAddress){
        List<String> ptcList = new LinkedList<String>();
        for(String ptc: _struct.keySet()){
            ConcurrentHashMap<ConnectAddress, String> map = _struct.get(ptc);
            if(map.containsKey(connectAddress))
                map.remove(connectAddress, "");
        }
        return ptcList;
    }
    
}
