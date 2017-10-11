/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.tasks;

import workingserver.processing.report.Report;


public class WordCountTask extends Task {

    public WordCountTask() {
        super("WCT", 'R');
    }

    @Override
    public Report run(String file) {
        Integer wordCount = file.split(" |\n").length;
        
        return createReportOk(wordCount.toString());
    }
    
}
