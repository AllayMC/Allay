package cn.allay.api.entity.component.impl.container;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.container.Container;
import cn.allay.api.container.ContainerViewer;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.ItemStack;
import com.google.common.collect.BiMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityContainerViewerComponent extends ContainerViewer {
    @Override
    @Inject
    byte assignInventoryId();

    @Override
    @Inject
    void sendContents(Container container);

    @Override
    @Inject
    void sendContent(Container container, int slot);

    @Override
    @Inject
    void onOpen(byte assignedId, Container container);

    @Override
    @Inject
    void onClose(byte assignedId, Container container);

    @Override
    @Inject
    void onSlotChange(Container container, int slot, ItemStack current);

    @Override
    @Inject
    @Nullable <T extends Container> T getOpenedContainer(FullContainerType<T> type);

    @Override
    @Inject
    Container getOpenedContainer(byte id);

    @Override
    @Inject
    @UnmodifiableView BiMap<Byte, Container> getId2ContainerBiMap();

    @Override
    @Inject
    @UnmodifiableView BiMap<FullContainerType<?>, Container> getType2ContainerBiMap();
}
