import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class HelloAction extends AnAction {
    public HelloAction() {
        super("Hello");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);

        Output.getInstance().getConsoleView().print("sample output", ConsoleViewContentType.NORMAL_OUTPUT);
    }
}