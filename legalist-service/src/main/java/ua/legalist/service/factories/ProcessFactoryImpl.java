package ua.legalist.service.factories;

import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Field;
import ua.legalist.model.Node;
import ua.legalist.process.factories.ProcessFactory;
import ua.legalist.model.Process;

@Service("processFactory")
@Transactional
public class ProcessFactoryImpl implements ProcessFactory {

    @Override
    public Process newProcess(Node persistentNode) {
        Process trnasientProcess = new Process();
        trnasientProcess.setFieldCollection(new HashSet<Field>());
        trnasientProcess.setCurrentNode(persistentNode);
        return trnasientProcess;
    }

}
