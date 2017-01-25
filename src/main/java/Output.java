import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Output {
    private static Output instance;
    private static final Object lock = new Object();

    private ConsoleView consoleView;

    private Output() {
    }

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

    public ConsoleView getConsoleView() {
        if(consoleView == null) {
            throw new RuntimeException("Console view was not properly initialized or disposed before using.");
        }
        return consoleView;
    }

    public void dispose() {
        if(consoleView != null)
            consoleView.dispose();
    }
}
