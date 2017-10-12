/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

import centralserver.processing.report.ReportOk;


public class AddClientOutputBuilder extends ClientOutputBuilder {

    public AddClientOutputBuilder(String fileName, ReportOk[] reports) {
        super(fileName, reports, 'R');
    }

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
