package org.allaymc.api.container;

import lombok.NoArgsConstructor;
import org.allaymc.api.utils.exception.ContainerException;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
@NoArgsConstructor
public class BaseContainerHolder implements ContainerHolder {
    protected final Map<FullContainerType<?>, Container> typeToContainer = new HashMap<>();
    protected final Map<ContainerSlotType, FullContainerType<?>> slotTypeToFullType = new HashMap<>();

    public BaseContainerHolder(Container... containers) {
        for (var container : containers) {
            this.addContainer(container);
        }
    }

    @Override
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return Collections.unmodifiableMap(typeToContainer);
    }

    @Override
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return (T) typeToContainer.get(type);
    }

    @Override
    public <T extends Container> T getContainerBySlotType(ContainerSlotType slotType) {
        return (T) getContainer(slotTypeToFullType.get(slotType));
    }

    @Override
    public void addContainer(Container container) {
        if (typeToContainer.containsKey(container.getContainerType()))
            throw new ContainerException("Inventory " + container.getClass().getSimpleName() + "already exists");
        typeToContainer.put(container.getContainerType(), container);
        container.getContainerType().heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }
}
