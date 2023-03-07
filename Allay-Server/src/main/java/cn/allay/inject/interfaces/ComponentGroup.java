package cn.allay.inject.interfaces;

import java.util.List;

/**
 * Describe a group of components <p/>
 * And can be built into object using {@link ComponentInjector}
 */
public interface ComponentGroup<T> {
    /**
     * @return the parent class
     */
    Class<T> getParentClass();

    /**
     * @return the components
     */
    List<ComponentImpl> getComponents();
}
