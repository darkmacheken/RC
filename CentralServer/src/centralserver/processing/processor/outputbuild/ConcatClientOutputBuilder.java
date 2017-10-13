package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;

/**
 *
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
