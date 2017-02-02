import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.JavaParametersUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PitestRunProfilState extends JavaCommandLineState {
    PitestRunConfiguration importantConfiguration;
    Module actualModule;

    protected PitestRunProfilState(Module modul, @NotNull ExecutionEnvironment environment, PitestRunConfiguration rc) {
        super(environment);
        importantConfiguration = rc;
        actualModule = modul;
    }

    @Override
    protected JavaParameters createJavaParameters() throws ExecutionException {
        JavaParameters myNewParams = new JavaParameters();

        //Ustawiamy parametry maszyny wirtualnej
        //TODO: dodać classPath danego modułu do argumentów

        myNewParams.setJdk(ProjectRootManager.getInstance(getEnvironment().getProject()).getProjectSdk());

        StringBuilder pitestClassWithArguments = new StringBuilder();
        pitestClassWithArguments.append("org.pitest.mutationtest.commandline.MutationCoverageReport");

        myNewParams.getClassPath().add(PathUtil.getJarPathForClass(getClass()));
        myNewParams.getClassPath().add(getEnvironment().getProject().getBaseDir().getPath());

        JavaParametersUtil.configureModule(actualModule, myNewParams, JavaParameters.JDK_AND_CLASSES_AND_TESTS, null);
        //JavaParametersUtil.configureConfiguration(myNewParams, importantConfiguration);

//        Module[] list = ModuleManager.getInstance(getEnvironment().getProject()).getModules();
//
//        for (Module m: list) {
//            Library[] dependecies = ModuleRootManager.getInstance(m).getModifiableModel().getModuleLibraryTable().getLibraries();
//            for (Library lib : dependecies)
//                myNewParams.getClassPath().add(lib.getName());
//        }

        myNewParams.setMainClass(pitestClassWithArguments.toString());
        ParametersList par = myNewParams.getProgramParametersList();

        par.add("--reportDir");
        par.add(".");
        par.add("--sourceDirs");
        par.add("./src");
        par.add("--targetClasses");
        par.add(importantConfiguration.getClassToMutate());
        par.add("--targetTests");
        par.add(importantConfiguration.getTestForClass());


        return myNewParams;
    }
}
