package org.allaymc.api.ddui;

import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.jetbrains.annotations.ApiStatus;

/**
 * Internal DDUI session contract used to collect dirty property updates for a viewer.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public interface DataDrivenScreenSession {

    /**
     * Marks the given property as dirty so it can be flushed to the client later.
     *
     * @param property changed property
     */
    void markDirty(DataDrivenProperty<?> property);
}
