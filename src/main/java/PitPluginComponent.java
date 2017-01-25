import com.intellij.openapi.components.ProjectComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ekamkli on 2017-01-25.
 */
public class PitPluginComponent implements ProjectComponent {
    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {
        Output.getInstance().dispose();
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "PitPluginComponent";
    }
}
