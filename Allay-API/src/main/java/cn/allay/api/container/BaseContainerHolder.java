package cn.allay.api.container;

import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
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
    @Nullable
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return (T) containers.get(type);
    }

    @Override
    public void addContainer(Container container) {
        if (containers.containsKey(container.getContainerType()))
            throw new IllegalArgumentException("Inventory " + container.getClass().getSimpleName() + "already exists");
        containers.put(container.getContainerType(), container);
    }
}
