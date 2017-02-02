import com.intellij.execution.configurations.ConfigurationTypeBase;

//nowy typ uruchomieniowy
public class PitestConfigurationType extends ConfigurationTypeBase {
    protected PitestConfigurationType() {
        super("PitestRandomId",
                "Pitest mutation",
                "Use mutation testing on given class using pitest",
                null);

        addFactory(new PitestConfigurationFactory(this));
    }
}
