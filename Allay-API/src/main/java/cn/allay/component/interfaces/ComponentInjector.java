package cn.allay.component.interfaces;

import cn.allay.api.ApiInstanceHolder;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * This interface describes a component injector
 */
public interface ComponentInjector<T> {

    /**
     * Create a new injector
     * @return a new injector
     */
    static <U> ComponentInjector<U> createInjector() {
        return ComponentInjectorFactory.FACTORY.get().create();
    }

    /**
     * Defines the parent class for this injector
     *
     * @param parentClass the parent class
     * @return the injector
     */
    ComponentInjector<T> parentClass(Class<T> parentClass);

    /**
     * Bind a set of implementations for the injector <br/>
     * The method of the component earlier in the list will override the method of the component later (if there is a method conflict)
     *
     * @param components components
     * @return the injector
     */
    ComponentInjector<T> withComponent(List<? extends ComponentImpl> components);

    /**
     * Build the class<br/>
     * Note that we guarantee that the returned class implements the {@link ComponentedObject} interface
     *
     * @return the class
     */
    Class<T> inject();

    interface ComponentInjectorFactory {

        ApiInstanceHolder<ComponentInjectorFactory> FACTORY = ApiInstanceHolder.create();

        <R> ComponentInjector<R> create();
    }
}
