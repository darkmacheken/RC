package workingserver.tasks;

import workingserver.processing.report.Report;

/**
 * Task that performs count of words in a text
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
