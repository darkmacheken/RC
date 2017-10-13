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
public class WordCountTask extends Task {

    /**
     *
     */
    public WordCountTask() {
        super("WCT", 'R');
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public Report run(String file) {
        String[] words = file.split(" |\n|\r");
        Integer wordCount = 0;
        
        for (String word : words) {
            if (!word.isEmpty())
                wordCount++;
        }
        
        return createReportOk(wordCount.toString());
    }
    
}
