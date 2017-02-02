import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class TestAction extends AnAction {

public TestAction() {
        super("Pit Plugin");
    }

    public void actionPerformed(AnActionEvent event)  {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        PitRunner pitRunner = new PitRunner();
        try{
            pitRunner.runMutation();
        }
        catch (IOException | InterruptedException e){
            Output.getInstance().error("Pit plugin error");
        }
        finally {
            Output.getInstance().println("Pit plugin");
        }

        Messages.showMessageDialog(project, "Pit plugin", "Pitest launched", Messages.getInformationIcon());
    }
}