import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;


public class PitestRunConfiguration extends ModuleBasedConfiguration {

    private String classToMutate;

    public void setTestForClass(String testForClass) {
        this.testForClass = testForClass;
    }

    public String getTestForClass() {

        return testForClass;
    }

    private String testForClass;

    public Module getSelectedModule() {
        return SelectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        SelectedModule = selectedModule;
    }

    private Module SelectedModule;

    public String getClassToMutate() {
        return classToMutate;
    }

    public void setClassToMutate(String specialClass) {
        classToMutate = specialClass;
    }

    public PitestRunConfiguration(RunConfigurationModule configurationModule, ConfigurationFactory factory) {
        super(configurationModule, factory);
        classToMutate = "";
        testForClass = "";
        SelectedModule = null;
    }

    @Override
    public Collection<Module> getValidModules() {
        Module[] goodModules = ModuleManager.getInstance(getProject()).getModules();
        return Arrays.asList(goodModules);
    }


    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {

        return new PitestRunProfilState(SelectedModule, executionEnvironment, this);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new PitestForm();
    }
}
