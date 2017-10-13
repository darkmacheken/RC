package workingserver.tasks;

import workingserver.processing.report.Report;

/**
 * Task that performs the search for the longuest word
 */
public class FindLongestWordTask extends Task {

    /**
     *
     */
    public FindLongestWordTask() {
        super("FLW", 'R');
    }

    /**
     *
     * @param file
     * @return
     */
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
