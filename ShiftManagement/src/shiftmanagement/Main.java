package shiftmanagement;

import shiftmanagement.Business.ShiftManagement;
import shiftmanagement.Parser.Parser;
import shiftmanagement.presentation.FrameInicial;


/**
 *
 * @author Tiago
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ShiftManagement system = new ShiftManagement();
        FrameInicial frameInicio = new FrameInicial(system);
        frameInicio.setVisible(true);
        
       /* Parser p = new Parser();
        p.parseShifts();*/
    }
    
}
