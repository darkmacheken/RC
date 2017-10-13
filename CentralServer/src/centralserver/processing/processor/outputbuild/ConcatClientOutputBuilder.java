/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;

/**
 *
 *  
 */
public class ConcatClientOutputBuilder extends ClientOutputBuilder {

    /**
     *
     * @param fileName
     * @param reports
     */
    public ConcatClientOutputBuilder(String fileName, ReportOk[] reports) {
        super(fileName, reports, 'F');
    }

    /**
     *
     */
    @Override
    public void build() {
        String outFile = "";
        for (ReportOk report : getReports())
            outFile += report.getFile();
        
        setFile(outFile);
    }
    
}
