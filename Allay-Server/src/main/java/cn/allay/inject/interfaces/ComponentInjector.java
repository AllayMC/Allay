package cn.allay.inject.interfaces;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 *
 * This interface describes a component injector
 */
public interface ComponentInjector<T> {
    /**
     * Defines the parent class for this injector
     * @param parentClass the parent class
     * @return the injector
     */
    ComponentInjector<T> parentClass(Class<T> parentClass);

    /**
     * Bind a set of implementations for the injector <br/>
     * The method of the component earlier in the list will override the method of the component later (if there is a method conflict)
     * @param components components
     * @return the injector
     */
    ComponentInjector<T> withComponent(List<ComponentImpl> components);

    /**
     * Load components & parent class from a {@link ComponentGroup}
     * @param componentGroup the component group
     * @return the injector
     */
    ComponentInjector<T> loadComponentGroup(ComponentGroup<T> componentGroup);

    /**
     * Build the class
     *
     * @return the class
     */
    Class<T> inject();
}
