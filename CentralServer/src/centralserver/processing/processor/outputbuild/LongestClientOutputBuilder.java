/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;


public class LongestClientOutputBuilder extends ClientOutputBuilder {

    public LongestClientOutputBuilder(String fileName, ReportOk[] reports) {
        super(fileName, reports, 'R');
    }

    @Override
    public void build() {
        String longestWord = "";
        
        for (ReportOk report : getReports())
            if (report.getFile().length() > longestWord.length())
                longestWord = report.getFile();

        setFile(longestWord);
    }
    
}
