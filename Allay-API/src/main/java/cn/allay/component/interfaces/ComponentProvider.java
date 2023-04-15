package cn.allay.component.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
public interface ComponentProvider<T extends ComponentImpl> {
    T provide();

    Class<T> getComponentClass();

    static <T extends ComponentImpl> ComponentProvider<T> of(Supplier<T> supplier, Class<T> componentClass) {
        return new SimpleComponentProvider<>(supplier, componentClass);
    }

    @AllArgsConstructor
    class SimpleComponentProvider<T extends ComponentImpl> implements ComponentProvider<T> {
        private Supplier<T> supplier;
        @Getter
        private Class<T> componentClass;

        @Override
        public T provide() {
            return supplier.get();
        }
    }
}
