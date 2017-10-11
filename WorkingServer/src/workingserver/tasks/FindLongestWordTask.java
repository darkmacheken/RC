/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.tasks;

import workingserver.processing.report.Report;


public class FindLongestWordTask extends Task {

    public FindLongestWordTask() {
        super("FLW", 'R');
    }

    @Override
    public Report run(String file) {
        String[] words = file.split(" |\n");
        String longestWord = "";
        
        for(String word : words)
            if (word.length() > longestWord.length())
                longestWord = word;
        
        return createReportOk(longestWord);
    }
    
}
