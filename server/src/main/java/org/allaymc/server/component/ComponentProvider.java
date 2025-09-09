package org.allaymc.server.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.utils.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author daoge_cmd
 */
public interface ComponentProvider<T extends Component> {
    /**
     * Creates a {@link ComponentProvider} using the given {@link Supplier} and component class.
     *
     * @param provider       the supplier that creates new component instances
     * @param componentClass the class of the component
     * @param <T>            the type of the component
     *
     * @return a component provider using the given supplier
     */
    static <T extends Component> ComponentProvider<T> of(Supplier<T> provider, Class<?> componentClass) {
        return of($ -> provider.get(), componentClass);
    }

    /**
     * Creates a {@link ComponentProvider} using the given {@link Function} and component class.
     *
     * @param provider       a function that accepts initialization info and returns a new component
     * @param componentClass the class of the component
     * @param <T>            the type of the component
     *
     * @return a component provider using the given function
     */
    static <T extends Component> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<?> componentClass) {
        return new Simple<>(provider, componentClass);
    }

    /**
     * Converts a list of component providers into a map indexed by their {@link Identifier}s.
     *
     * @param componentProviders the list of component providers
     * @param <P>                the base type of components
     *
     * @return a map from identifiers to providers
     *
     * @throws IllegalArgumentException if duplicate identifiers are found
     */
    static <P extends Component> Map<Identifier, ComponentProvider<? extends P>> toMap(List<ComponentProvider<? extends P>> componentProviders) {
        Map<Identifier, ComponentProvider<? extends P>> map = new HashMap<>(componentProviders.size());
        for (var provider : componentProviders) {
            var id = provider.findComponentIdentifier();
            if (map.putIfAbsent(id, provider) != null) {
                throw new IllegalArgumentException("Duplicate component: " + id);
            }
        }

        return map;
    }

    /**
     * Searches the class hierarchy to find a static field annotated with {@link Identifier.Component}
     * and returns its {@link Identifier} value.
     *
     * @param clazz the class to inspect
     *
     * @return the identifier if found, otherwise {@code null}
     */
    static Identifier findComponentIdentifier(Class<?> clazz) {
        var current = clazz;
        while (current != null) {
            var id = findComponentIdentifierInClass(current);
            if (id != null) {
                return id;
            }

            current = current.getSuperclass();
        }

        return null;
    }

    /**
     * Finds the first static field annotated with {@link Identifier.Component} within the given class.
     *
     * @param clazz the class to inspect
     *
     * @return the identifier if found, otherwise {@code null}
     */
    static Identifier findComponentIdentifierInClass(Class<?> clazz) {
        try {
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Identifier.Component.class) && Identifier.class == field.getType() && isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    return (Identifier) field.get(null);
                }
            }

            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access component identifier field in " + clazz.getName(), e);
        }
    }

    /**
     * Provides a component instance using the given initialization info.
     *
     * @param info the component initialization information
     *
     * @return the created component instance
     */
    T provide(ComponentInitInfo info);

    /**
     * Retrieves the class of the component provided by this provider.
     *
     * @return the component class
     */
    Class<?> getComponentClass();

    /**
     * Finds the identifier for the component based on its class.
     *
     * @return the component identifier, or {@code null} if not found
     */
    default Identifier findComponentIdentifier() {
        return findComponentIdentifier(getComponentClass());
    }

    /**
     * A simple implementation of {@link ComponentProvider} backed by a {@link Function}.
     *
     * @param <T> the type of the component
     * @param <I> the type of the component initialization info
     */
    @Getter
    @AllArgsConstructor
    class Simple<T extends Component, I extends ComponentInitInfo> implements ComponentProvider<T> {
        private Function<I, T> provider;
        private Class<?> componentClass;

        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply((I) info);
        }
    }
}
