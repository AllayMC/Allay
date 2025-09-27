package org.allaymc.api.container;

import lombok.Getter;

/**
 * ContainerType represents a type of container, which can be used to get container
 * instance from a {@link ContainerHolder}. The instances of this class are compared
 * by reference.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
public class ContainerType<T extends Container> {

    @Getter
    private final int size;

    public ContainerType(int size) {
        this.size = size;
    }
}
