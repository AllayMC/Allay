package org.allaymc.server.item.component.common;

import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.common.ItemItemStorableComponent;
import org.allaymc.api.item.component.event.ItemLoadExtraTagEvent;
import org.allaymc.api.item.component.event.ItemPlacedAsBlockEvent;
import org.allaymc.api.item.component.event.ItemSaveExtraTagEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2024/6/21
 *
 * @author daoge_cmd
 */
public class ItemItemStorableComponentImpl implements ItemItemStorableComponent {

    protected List<NbtMap> storedItems = List.of();

    @EventHandler
    protected void onLoadExtraTag(ItemLoadExtraTagEvent event) {
        var extraTag = event.getExtraTag();
        if (extraTag.containsKey("Items")) {
            storedItems = extraTag.getList("Items", NbtType.COMPOUND);
        }
    }

    @EventHandler
    protected void onSaveExtraTag(ItemSaveExtraTagEvent event) {
        var builder = event.getExtraTag();
        if (!storedItems.isEmpty()) {
            builder.put("Items", storedItems);
        }
    }

    @EventHandler
    protected void onPlacedAsBlock(ItemPlacedAsBlockEvent event) {
        var blockEntity = event.getDimension().getBlockEntity(event.getPlaceBlockPos());
        if (blockEntity instanceof BlockEntityContainerHolderComponent containerHolderComponent) {
            containerHolderComponent.getContainer().loadNBT(storedItems);
        }
    }

    @Override
    public @UnmodifiableView List<NbtMap> getStoredItems() {
        return Collections.unmodifiableList(storedItems);
    }

    @Override
    public void setStoredItems(List<NbtMap> items) {
        storedItems = items;
    }
}
