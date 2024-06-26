package org.allaymc.api;

import lombok.NoArgsConstructor;

/**
 * Allay Project 2023/3/17
 *
 * @author daoge_cmd
 */
@NoArgsConstructor
public final class ApiInstanceHolder<T> {

    private T instance;

    public static <T> ApiInstanceHolder<T> create() {
        return new ApiInstanceHolder<>();
    }

    public void set(T instance) {
        if (this.instance == null) this.instance = instance;
    }

    public T get() {
        return this.instance;
    }
}
