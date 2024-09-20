package org.allaymc.api;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.ApiStatus;

/**
 * ApiInstanceHolder is used to hold the API instance that
 * needs to be implemented and provided by api implementation (Allay-Server).
 *
 * @author daoge_cmd
 */
@NoArgsConstructor
@ApiStatus.Internal
public final class ApiInstanceHolder<T> {

    private T instance;

    /**
     * Create a new empty api instance holder with the specific type.
     *
     * @return the new api instance holder.
     * @param <T> the type of the api instance that the holder will hold.
     */
    public static <T> ApiInstanceHolder<T> create() {
        return new ApiInstanceHolder<>();
    }

    /**
     * Set the instance of the api implementation.
     * <p>
     * This method only works when the instance is null, which means that you can only set the instance once.'
     * Calls this method multiple times will not change the instance.
     *
     * @param instance the instance of the api implementation.
     */
    public void set(T instance) {
        if (this.instance == null) this.instance = instance;
    }

    /**
     * Get the instance of the api implementation.
     *
     * @return the instance of the api implementation.
     */
    public T get() {
        return this.instance;
    }
}
