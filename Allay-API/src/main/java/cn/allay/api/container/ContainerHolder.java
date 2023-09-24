package cn.allay.api.container;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
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
    Map<FullContainerType<?>, Container> getContainers();

    @Nullable <T extends Container> T getContainer(FullContainerType<T> type);

    @Nullable
    default <T extends Container> T getContainerBySlotType(ContainerSlotType slotType) {
        return getContainer(FullContainerType.fromSlotType(slotType));
    }

    void addContainer(Container container);
}
