package org.allaymc.server.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerException;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class BaseContainerHolder implements ContainerHolder {
    protected final Map<ContainerType<?>, Container> typeToContainer = new HashMap<>();
    protected final Map<ContainerSlotType, ContainerType<?>> slotTypeToFullType = new HashMap<>();

    public BaseContainerHolder(Container... containers) {
        for (Container container : containers) {
            this.addContainer(container);
        }
    }

    @Override
    public @UnmodifiableView Map<ContainerType<?>, Container> getContainers() {
        return Collections.unmodifiableMap(typeToContainer);
    }

    @Override
    public boolean hasContainer(ContainerType<?> type) {
        return typeToContainer.containsKey(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getContainer(ContainerType<T> type) {
        return (T) typeToContainer.get(type);
    }

    @SuppressWarnings("unchecked")
    public <T extends Container> T getContainer(ContainerSlotType slotType) {
        return (T) getContainer(slotTypeToFullType.get(slotType));
    }

    @Override
    public void addContainer(Container container) {
        if (typeToContainer.containsKey(container.getContainerType())) {
            throw new ContainerException("Inventory " + container.getClass().getSimpleName() + " already exists");
        }

        typeToContainer.put(container.getContainerType(), container);
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }
}
