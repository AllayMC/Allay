package org.allaymc.api.container;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface ContainerHolder {
    @UnmodifiableView
    Map<FullContainerType<?>, Container> getContainers();

    boolean hasContainer(FullContainerType<?> type);

    <T extends Container> T getContainer(FullContainerType<T> type);

    <T extends Container> T getContainerBySlotType(ContainerSlotType slotType);

    void addContainer(Container container);
}
