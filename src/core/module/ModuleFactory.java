package core.module;


import core.module.impl.ModuleImpl;

public class ModuleFactory {
    private ModuleFactory() {
    }

    public static Module getFactory() {
        return new ModuleImpl();
    }
}
