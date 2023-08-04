package cn.allay.api.component.interfaces;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
public interface ComponentProvider<T extends ComponentImpl> {
    static <T extends ComponentImpl> ComponentProvider<T> of(Supplier<T> provider, Class<T> componentClass) {
        return new SimpleComponentProvider<>((info) -> provider.get(), componentClass);
    }

    static <T extends ComponentImpl> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<T> componentClass) {
        return new SimpleComponentProvider<>(provider, componentClass);
    }

    static <T extends ComponentImpl> ComponentProvider<T> ofSingleton(T singleton) {
        return of((info) -> singleton, (Class<T>) singleton.getClass());
    }

    static <P extends ComponentImpl> Map<Identifier, ComponentProvider<? extends P>> toMap(List<ComponentProvider<? extends P>> componentProviders) {
        var map = new HashMap<Identifier, ComponentProvider<? extends P>>();
        componentProviders.forEach(componentProvider -> {
            var id = componentProvider.findComponentIdentifier();
            if (map.containsKey(id))
                throw new IllegalArgumentException("Duplicate component: " + id);
            map.put(id, componentProvider);
        });
        return map;
    }

    T provide(ComponentInitInfo info);

    Class<T> getComponentClass();

    @SneakyThrows
    @Nullable
    default Identifier findComponentIdentifier() {
        return findComponentIdentifier(getComponentClass());
    }

    @SneakyThrows
    @Nullable
    static Identifier findComponentIdentifier(Class<? extends ComponentImpl> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ComponentIdentifier.class) &&
                Identifier.class == field.getType() &&
                isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    return (Identifier) field.get(null);
                } finally {
                    field.setAccessible(false);
                }
            }
        }
        return null;
    }

    @AllArgsConstructor
    class SimpleComponentProvider<T extends ComponentImpl> implements ComponentProvider<T> {
        private Function<ComponentInitInfo, T> provider;
        @Getter
        private Class<T> componentClass;

        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply(info);
        }
    }
}
