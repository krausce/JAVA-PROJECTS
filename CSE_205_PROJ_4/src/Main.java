/********************************************************************************************************
 * Class: Main.java																						*
 * DESCRIPTION																							*
 * Executes and synchronizes efforts across the program.												*
 * 																										*
 * COURSE AND PROJECT INFO																				*
 * CSE205 Object Oriented Programming and Data Structures, Spring B Online 2019.						*
 * Project Number: p03																					*
 * 																										*
 * @AUTHOR Christopher E. Kraus, cekraus1, cekraus1@asu.edu.											*
 * ******************************************************************************************************/
import javax.swing.JFrame;

/**
 * The Main class containing the main() and run() methods.
 */
public class Main {

    // A reference to the View object.
    private View mView;

    /**
     * This is where execution starts. Instantiate a Main object and then call run().
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * exit() is called when the Exit button in the View is clicked. Call System.exit(0).
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Accessor method for mView.
     */
    private View getView() {
        return mView;
    }

    /**
     * run() is the main routine.
     */
    private void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setView(new View(this));
    }

    /**
     * Mutator method for mView.
     */
    private void setView(View pView) {
        mView = pView;
    }

}
