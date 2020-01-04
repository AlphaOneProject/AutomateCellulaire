package control;

import model.Constant;
import model.Extension;
import model.Repetition;
import model.Pacman;
import model.State;
import model.Symmetry;

public class ExtensionFactory {
    
    // Attributes.
    
    private static ExtensionFactory instance;
    
    // Methods.
    
    private ExtensionFactory() {}
    
    public static ExtensionFactory getInstance() {
        if (instance == null) instance = new ExtensionFactory();
        return instance;
    }
    
    public Extension getExtension(String extension_type, Object argument) {
        Extension new_extension = null;
        
        switch (extension_type)
        {
        case "repetition":
            new_extension = new Repetition(); 
            break;
        case "pacman":
            new_extension = new Pacman();
            break;
        case "symmetry":
            if (argument.getClass() != int.class) throw new IllegalArgumentException("Get Extension: Invalid argument given.");
            int arg_int = (Integer) argument;
            new_extension = new Symmetry(arg_int);
            break;
        case "constant":
            if (argument.getClass() != State.class) throw new IllegalArgumentException("Get Extension: Invalid argument given.");
            State arg_state = (State) argument;
            new_extension = new Constant(arg_state);
            break;
        default:
            throw new IllegalArgumentException("Get Extension: Invalid extension type provided: \"" + extension_type +
                                               "\"");
        }

        return new_extension;
    }
}
