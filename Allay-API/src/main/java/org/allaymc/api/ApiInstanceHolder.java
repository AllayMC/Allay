package org.allaymc.api;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.ApiStatus;

/**
 * Allay Project 2023/3/17
 *
 * @author daoge_cmd
 */
@NoArgsConstructor
@ApiStatus.Internal
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
