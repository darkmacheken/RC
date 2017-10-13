package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;

/**
 *
 *  
 *
 */
public class AddClientOutputBuilder extends ClientOutputBuilder {

    /**
     *
     * @param fileName
     * @param reports
     */
    public AddClientOutputBuilder(String fileName, ReportOk[] reports) {
        super(fileName, reports, 'R');
    }

    /**
     *
     */
    @Override
    public void build() {
        Integer value = 0;
        try {
            for (ReportOk report : getReports())
                value += Integer.parseInt(report.getFile());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        setFile(value.toString());
    }
    
}
