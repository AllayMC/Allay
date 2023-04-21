package cn.allay.component.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
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

    T provide(ComponentInitInfo info);

    Class<T> getComponentClass();

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
