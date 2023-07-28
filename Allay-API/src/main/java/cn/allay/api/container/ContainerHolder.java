package cn.allay.api.container;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface ContainerHolder {
    @UnmodifiableView
    Map<FullContainerType, Container> getContainers();

    @Nullable
    Container getContainer(FullContainerType type);

    @Nullable
    default Container getContainerBySlotType(ContainerSlotType slotType) {
        return getContainer(FullContainerType.fromSlotType(slotType));
    }

    void addContainer(Container container);
}
