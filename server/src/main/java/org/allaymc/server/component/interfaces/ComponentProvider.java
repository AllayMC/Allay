package org.allaymc.server.component.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
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
    static <T extends Component> ComponentProvider<T> of(Supplier<T> provider, Class<?> componentClass) {
        return new SimpleComponentProvider<>((info) -> provider.get(), componentClass);
    }

    static <T extends Component> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<?> componentClass) {
        return new SimpleComponentProvider<>(provider, componentClass);
    }

    static <T extends Component> ComponentProvider<T> ofSingleton(T singleton) {
        return of((info) -> singleton, singleton.getClass());
    }

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
     * Gets the class of the component provided by this provider.
     *
     * @return the component class
     */
    Class<?> getComponentClass();

    /**
     * Finds the identifier for the component.
     *
     * @return the component identifier, or {@code null} if not found
     */
    default Identifier findComponentIdentifier() {
        return findComponentIdentifier(getComponentClass());
    }

    @Getter
    @AllArgsConstructor
    class SimpleComponentProvider<T extends Component, I extends ComponentInitInfo> implements ComponentProvider<T> {
        private Function<I, T> provider;
        private Class<?> componentClass;

        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply((I) info);
        }
    }
}
