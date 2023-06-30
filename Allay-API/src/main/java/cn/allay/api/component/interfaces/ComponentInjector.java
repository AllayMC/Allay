package cn.allay.api.component.interfaces;

import cn.allay.api.ApiInstanceHolder;

import java.util.List;

/**
 * This interface describes a component injector
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface ComponentInjector<T> {

    /**
     * Create a new injector
     *
     * @return a new injector
     */
    static <U> ComponentInjector<U> createInjector() {
        return ComponentInjectorFactory.FACTORY.get().create();
    }

    /**
     * Defines the parent class for this injector
     *
     * @param interfaceClass the interface class
     * @return the injector
     */
    ComponentInjector<T> interfaceClass(Class<T> interfaceClass);

    /**
     * Bind a set of implementations for the injector <br>
     * When the dynamic class is instantiated, the component instance will be obtained from the Provider <br>
     * If there are multiple implementation methods for a method to be injected, they will be executed in the order in the component list <p>
     * and the return value is the return value of the last executed method
     *
     * @param providers component providers
     * @return the injector
     */
    ComponentInjector<T> component(List<ComponentProvider<? extends ComponentImpl>> providers);

    /**
     * Build the class<br/>
     * Note that we guarantee that the returned class implements the {@link ComponentedObject} interface
     *
     * @return the class
     */
    Class<T> inject();

    interface ComponentInjectorFactory {

        ApiInstanceHolder<ComponentInjectorFactory> FACTORY = ApiInstanceHolder.of();

        <R> ComponentInjector<R> create();
    }
}
