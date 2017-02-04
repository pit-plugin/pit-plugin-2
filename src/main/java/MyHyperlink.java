import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.openapi.project.Project;
import javafx.scene.control.Hyperlink;

/*
    Created by Karol on 02.02.2017.
    We need special Hypertext to print in ConsoleView and also to have
    hyperlink that should be able to handle event

 */


public class MyHyperlink extends Hyperlink implements HyperlinkInfo {

    @Override
    public void navigate(Project project) {

    }

    @Override
    public boolean includeInOccurenceNavigation() {
        return false;
    }
}
