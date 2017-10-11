/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.report;

/**
 *
 * @author Asus
 */
public class ReportError extends Report {
    //Error
    private String _error;

    /**
     *
     * @param _nameAdress
     * @param _iP
     * @param _port
     */
    public ReportError(String error) {
        _error = error;
    }

    /**
     *
     * @return
     */
    public String getError() {
        return _error;
    }
    
    
}
