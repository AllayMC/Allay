package org.allaymc.api.container;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface ContainerHolder {
    @UnmodifiableView
    Map<FullContainerType<?>, Container> getContainers();

    <T extends Container> T getContainer(FullContainerType<T> type);

    <T extends Container> T getContainerBySlotType(ContainerSlotType slotType);

    void addContainer(Container container);
}
