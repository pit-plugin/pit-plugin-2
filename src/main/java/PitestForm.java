import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class PitestForm extends SettingsEditor<PitestRunConfiguration> {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;


    @Override
    protected void resetEditorFrom(@NotNull PitestRunConfiguration pitestRunConfiguration) {
        for ( Module oneModule : pitestRunConfiguration.getValidModules()) {
            comboBox1.addItem(oneModule);
        }

        Module selected = pitestRunConfiguration.getSelectedModule();
        if (selected != null) {
            comboBox1.setSelectedItem(selected);
        }

        textField1.setText(pitestRunConfiguration.getClassToMutate());
        textField2.setText(pitestRunConfiguration.getTestForClass());

    }

    @Override
    protected void applyEditorTo(@NotNull PitestRunConfiguration pitestRunConfiguration) throws ConfigurationException {
        pitestRunConfiguration.setClassToMutate(textField1.getText());
        pitestRunConfiguration.setTestForClass(textField2.getText());
        pitestRunConfiguration.setSelectedModule((Module)comboBox1.getSelectedItem());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return panel1;
    }
}
