import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class TestAction extends AnAction {
    public TestAction() {
        super("Pit Plugin");
    }

    public void actionPerformed(AnActionEvent event) {
        Output.getInstance().println("Pit plugin");
        Output.getInstance().error("Pit plugin error");
    }
}