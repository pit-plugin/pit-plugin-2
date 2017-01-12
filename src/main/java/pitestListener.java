import org.pitest.mutationtest.ClassMutationResults;
import org.pitest.mutationtest.MutationResult;

/**
 * Created by marek on 21.12.16.
 */
public class pitestListener implements org.pitest.mutationtest.MutationResultListener{

    @Override
    public void runStart() {

    }

    @Override
    public void handleMutationResult(ClassMutationResults classMutationResults) {

        for(MutationResult res : classMutationResults.getMutations()) {
            System.out.println(res.getDetails().getLineNumber() + ":" + res.getDetails().getDescription());
        }

    }

    @Override
    public void runEnd() {

    }
}