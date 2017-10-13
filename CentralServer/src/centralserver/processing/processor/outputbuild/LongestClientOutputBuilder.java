package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;

/**
 *
 *  
 *
 */
public class LongestClientOutputBuilder extends ClientOutputBuilder {

    /**
     *
     * @param fileName
     * @param reports
     */
    public LongestClientOutputBuilder(String fileName, ReportOk[] reports) {
        super(fileName, reports, 'R');
    }

    /**
     *
     */
    @Override
    public void build() {
        String longestWord = "";
        
        for (ReportOk report : getReports())
            if (report.getFile().length() > longestWord.length())
                longestWord = report.getFile();

        setFile(longestWord);
    }
    
}
