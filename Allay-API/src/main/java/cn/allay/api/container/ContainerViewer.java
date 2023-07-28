package cn.allay.api.container;

import cn.allay.api.item.ItemStack;
import com.google.common.collect.BiMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface ContainerViewer {

    byte assignInventoryId();

    void sendContents(Container container);

    void sendContent(Container container, int slot);

    void onOpen(byte assignedId, Container container);

    void onClose(byte assignedId, Container container);

    void onSlotChange(Container container, int slot, ItemStack current);

    @Nullable
    Container getOpenedContainer(FullContainerType type);

    @Nullable
    Container getOpenedContainer(byte id);

    @UnmodifiableView
    BiMap<Byte, Container> getId2ContainerBiMap();

    @UnmodifiableView
    BiMap<FullContainerType, Container> getType2ContainerBiMap();
}
