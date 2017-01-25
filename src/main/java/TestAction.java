import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class TestAction extends AnAction {
    public TestAction() {
        super("Test");
    }

    public void actionPerformed(AnActionEvent event) {
        Output.getInstance().println("sample output");
        Output.getInstance().error("sample error");
    }
}