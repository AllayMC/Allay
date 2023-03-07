package cn.allay.inject.interfaces;

import java.util.List;

/**
 * The object which has been built by {@link ComponentInjector}
 */
public interface RuntimeComponentObject {
    /**
     * @return Components provided when constructing the object
     */
    List<ComponentImpl> getComponents();
}
