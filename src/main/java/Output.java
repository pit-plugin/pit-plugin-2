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
    private static ToolWindow toolWindow;
    private static Map<String, Content> contents;
    private static ContentFactory contentFactory;
    private static JPanel panel;

    private Output() {
        contents = new TreeMap<>();
        contentFactory = ContentFactory.SERVICE.getInstance();
        panel = new JPanel();
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

    private static ToolWindow getToolWindow(Project project) {
        if (toolWindow == null || Arrays.stream(ProjectManager.getInstance().getOpenProjects()).filter(n->n.equals(project)).count() == 0) {
            toolWindow = ToolWindowManager.getInstance(project).registerToolWindow("PIT log", true, ToolWindowAnchor.BOTTOM);
            toolWindow.setTitle("PIT log");
        }
        return toolWindow;
    }

    private static JPanel getPanel(Project project) {
        if (toolWindow == null || Arrays.stream(ProjectManager.getInstance().getOpenProjects()).filter(n->n.equals(project)).count() == 0) {
            ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
            JComponent consolePanel = consoleView.getComponent();

            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(consolePanel, BorderLayout.CENTER);
        }
        return panel;
    }

    private static Content initializeProjectContent(Project project) {
        Content content = contentFactory.createContent(getPanel(project), project.getName(), false);
        contents.put(project.toString(), content);
        getToolWindow(project).getContentManager().addContent(content);
        return content;
    }

    public static ConsoleView getProjectConsole(Project project) {
        String projId = project.toString();
        Content projContent = contents.get(projId);

        if (projContent == null) {
            projContent = initializeProjectContent(project);
        }

        JPanel panel = (JPanel)projContent.getComponent();
        ConsoleView console = (ConsoleView) panel.getComponents()[0];
        return console;
    }
}
