/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.tasks;

import workingserver.processing.report.Report;
import workingserver.processing.report.ReportError;

/**
 *
 *  
 */
public class TaskRunner {
    private final Task[] _tasks;
    private final String _pTC;
    private final String _file;
    
    /**
     *
     * @param tasks
     * @param pTC
     * @param file
     */
    public TaskRunner(Task[] tasks, String pTC, String file) {
        _tasks = tasks;
        _pTC = pTC;
        _file = file;
    }
    
    /**
     *
     * @return
     */
    public Report run() {
        for (Task task : _tasks) {
            if (task.isPTC(_pTC)) {
                return task.run(_file);
            }
        }
        
        return new ReportError("REP EOF");
    }
}
