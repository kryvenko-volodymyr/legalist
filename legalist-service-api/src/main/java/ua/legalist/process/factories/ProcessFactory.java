package ua.legalist.process.factories;

import ua.legalist.model.Node;
import ua.legalist.model.Process;

public interface ProcessFactory {
    
    /*
    * Returns new non-transient Process object with "nodes" field 
    * containing genealogy of the Node argument.
    */
    Process newProcess (Node node);
    
}
