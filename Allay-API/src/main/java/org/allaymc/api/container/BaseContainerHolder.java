package org.allaymc.api.container;

import org.allaymc.api.exception.ContainerException;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class BaseContainerHolder implements ContainerHolder {
    protected final Map<FullContainerType<?>, Container> containers = new HashMap<>();

    public BaseContainerHolder() {
    }

    public BaseContainerHolder(Container... containers) {
        for (Container container : containers) {
            this.addContainer(container);
        }
    }

    @Override
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return Collections.unmodifiableMap(containers);
    }

    @Override
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return (T) containers.get(type);
    }

    @Override
    public void addContainer(Container container) {
        if (containers.containsKey(container.getContainerType()))
            throw new ContainerException("Inventory " + container.getClass().getSimpleName() + "already exists");
        containers.put(container.getContainerType(), container);
    }
}
