package workingserver.tasks;

import workingserver.processing.report.Report;

/**
 * Task that performs the convertion of text to Lower Case
 */
public class ConvertTextToLowerCaseTask extends Task {

    /**
     *
     */
    public ConvertTextToLowerCaseTask() {
        super("LOW", 'F');
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public Report run(String file) {
        return createReportOk(file.toLowerCase());
    }
    
}
