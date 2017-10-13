/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.tasks;

import workingserver.processing.report.Report;

/**
 *
 *  
 */
public class ConvertTextToUpperCaseTask extends Task {

    /**
     *
     */
    public ConvertTextToUpperCaseTask() {
        super("UPP", 'F');
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public Report run(String file) {
        return createReportOk(file.toUpperCase());
    }
    
}
