package org.allaymc.api.container;

import lombok.Getter;

/**
 * {@code ContainerType} identifies a container layout and carries its slot count for
 * allocation and lookup. Instances use reference identity rather than value equality, so
 * callers must reuse the same type instance when accessing a registered container
 * through a holder.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
public class ContainerType<T extends Container> {

    /**
     * Container slot count used when implementations allocate item storage.
     */
    @Getter
    private final int size;

    /**
     * Creates a distinct container identity with the supplied slot count. This
     * constructor stores {@code size} without validating it; allocating container
     * storage can subsequently reject a negative slot count.
     */
    public ContainerType(int size) {
        this.size = size;
    }
}
