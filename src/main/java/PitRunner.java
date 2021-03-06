import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pawel on 12.01.17.
 */
public class PitRunner {
    public void runMutation() throws IOException, InterruptedException {
        String cp, OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("win")){
            cp = "\"lib/*\"";
        } else {
            cp = "./lib/pitest-1.1.11.jar:./lib/pitest-command-line-1.1.11.jar:" +
                    "./lib/junit-4.12.jar:./lib/hamcrest-core-1.3.jar";
        }
        String command = String.format("java  " +
                        "-cp %s  " +
                        "org.pitest.mutationtest.commandline.MutationCoverageReport  " +
                        "--classPath %s " +
                        "--reportDir %s " +
                        "--targetClasses %s " +
                        "--targetTests %s " +
                        "--sourceDirs %s",
                cp,
                "../pit-example-master/target/classes," +
                        "../pit-example-master/target/test-classes",
                "../pitReports",
                "pitexample.*",
                "pitexample.*",
                "../pit-example-master"
        );

        Output.getInstance().println(command);
        Process process = Runtime.getRuntime().exec(command);


        Scanner sc = new Scanner(process.getInputStream());
        while (sc.hasNext()) {
            Output.getInstance().println(sc.nextLine());
        }
        sc = new Scanner(process.getErrorStream());
        while (sc.hasNext()) {
           Output.getInstance().error(sc.nextLine());
        }
        process.waitFor();
    }
}
