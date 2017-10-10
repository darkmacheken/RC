/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

/**
 *
 * @author duartegalvao
 */
public class WSList {
    private String[] _pTCs;

    /**
     *
     * @return
     */
    public synchronized String[] getPTCs() {
        return _pTCs;
    }
    
    /**
     *
     * @param pTC
     * @return
     */
    public synchronized ConnectAddress[] getIPs(String pTC){
        
    }
    
    /**
     *
     * @param pTC
     * @param ip
     * @return
     */
    public synchronized boolean addIP(String[] pTC, String ip){
        
    }
    
    /**
     *
     * @param ip
     * @return
     */
    public synchronized boolean removeIP(String ip){
        
    }
    
}
