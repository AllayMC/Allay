package org.allaymc.api.component.interfaces;

import org.allaymc.api.ApiInstanceHolder;

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
     *
     * @return the injector
     */
    ComponentInjector<T> interfaceClass(Class<T> interfaceClass);

    /**
     * Bind a set of implementations for the injector.
     * <p>
     * When the dynamic class is instantiated, the component instance will be obtained from the Provider.
     * <p>
     * If there are multiple implementation methods for a method to be injected, they will be executed in the order in the component list
     * and the return value is the return value of the last executed method
     *
     * @param providers component providers
     *
     * @return the injector
     */
    ComponentInjector<T> component(List<ComponentProvider<? extends Component>> providers);

    ComponentInjector<T> useCachedClass(Class<T> cachedClass);

    /**
     * Build the class.
     * <p>
     * Note that we guarantee that the returned class implements the {@link ComponentedObject} interface
     *
     * @return the class
     */
    Class<T> inject(boolean alwaysUpdate);

    interface ComponentInjectorFactory {

        ApiInstanceHolder<ComponentInjectorFactory> FACTORY = ApiInstanceHolder.create();

        <R> ComponentInjector<R> create();
    }
}
