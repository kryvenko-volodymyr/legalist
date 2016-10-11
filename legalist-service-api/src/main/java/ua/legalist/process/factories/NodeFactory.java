package ua.legalist.process.factories;

import ua.legalist.model.Node;

public interface NodeFactory {
    
    /*
    * Returns new non-transient Node object
    */
    Node newNode (String title, String details);
    
}
