import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import javafx.util.Pair;
import javafx.event.EventHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Class used to print output to PitPlugin console.
 * note: Need to be dispatched when closing project.
 */
public class Output {
    private static final Object lock = new Object();
    private static Output instance;
    private ConsoleView consoleView;

    private Output() {

    }

    /**
     * Gets an instance of Output class.
     */
    public static Output getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Output();
                }
            }
        }
        return instance;
    }

    public JPanel createJPanel(Project project) {

        consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        JComponent consolePanel = consoleView.getComponent();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(consolePanel, BorderLayout.CENTER);
        return panel;

    }

    private ConsoleView getConsoleView() {

        if (consoleView == null) {
            throw new RuntimeException("Console view was not properly initialized or disposed before using.");
        }
        return consoleView;

    }

    /**
     * Prints text followed by line feed to PitPlugin console.
     */
    public void println(String text) {
        ConsoleView cv = getConsoleView();
        cv.print(text, ConsoleViewContentType.NORMAL_OUTPUT);
        cv.print("\n", ConsoleViewContentType.NORMAL_OUTPUT);
    }

    /**
     * Prints error followed by line feed to PitPlugin console.
     */
    public void error(String error) {
        ConsoleView cv = getConsoleView();
        cv.print(error, ConsoleViewContentType.ERROR_OUTPUT);
        cv.print("\n", ConsoleViewContentType.ERROR_OUTPUT);
    }

    /**
     * Prints hyperlink to PitPlugin console.
     */
    public void hyperlink(String hyperlink, HyperlinkInfo info) {
        ConsoleView cv = getConsoleView();
        cv.printHyperlink(hyperlink, info);
    }

    /*
     * Prints hyperlinks with reference to code
     */

    public void hyperlink(MyHyperlink hyperlink){

        ConsoleView cv = getConsoleView();
        //Display MyHiperlink to console
        cv.printHyperlink(hyperlink.getText(),hyperlink);

        // Set hyperlink handle
        hyperlink.setOnAction(new EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(javafx.event.ActionEvent event) {

                if ( event.getEventType().equals(Event.MOUSE_ENTER) && !hyperlink.getText().equals("") ){

                    Pair<String,Integer> hyperlinkParameters = PitestHandleOutputEvent.paraseHyperlink(hyperlink);

                    String filePath = hyperlinkParameters.getKey();

                    Integer rowNumber = hyperlinkParameters.getValue();

                    PitestHandleOutputEvent.goToCodeLine(filePath,rowNumber);

                }
            }

        });//End of setOnAction

    }//End of hyperlink

    /**
     * Disposed the console resources, need to be called when closing a project.
     */
    public void dispose() {
        if (consoleView != null)
            consoleView.dispose();

    }
}
