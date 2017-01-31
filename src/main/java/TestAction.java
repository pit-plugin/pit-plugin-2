import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class TestAction extends AnAction {
    public TestAction() {
        super("Test");
    }

    public void actionPerformed(AnActionEvent event) {
        Output.getInstance().println("sample output");
        Output.getInstance().error("sample error");
    }
}