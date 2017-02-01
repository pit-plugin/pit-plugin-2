import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pawel on 12.01.17.
 */
public class PitRunner {
    public void runMutation() throws IOException, InterruptedException {

        String command = String.format("java  " +
                        "-cp %s  " +
                        "org.pitest.mutationtest.commandline.MutationCoverageReport  " +
                        "--classPath %s " +
                        "--reportDir %s " +
                        "--targetClasses %s " +
                        "--targetTests %s " +
                        "--sourceDirs %s",
                "./lib/pitest-1.1.11.jar:./lib/pitest-command-line-1.1.11.jar:./lib/junit-4.12.jar:./lib/hamcrest-core-1.3.jar",
                "/home/pawel/libs/pit-example-master/target/classes,/home/pawel/libs/pit-example-master/target/test-classes",
                "/home/pawel/temp",
                "pitexample.*",
                "pitexample.*",
                "/home/pawel/libs/pit-example-master"
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
