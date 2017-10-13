package workingserver.tasks;

import workingserver.processing.report.Report;

/**
 * Task that performs the convertion of text to Upper Case
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
