package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityShelfBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author daoge_cmd
 */
public class BlockEntityShelfBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityShelfBaseComponent {

    protected static final String TAG_ITEMS = "Items";

    protected ItemStack[] items;

    public BlockEntityShelfBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.items = new ItemStack[SLOT_COUNT];
        for (int i = 0; i < SLOT_COUNT; i++) {
            items[i] = ItemAirStack.AIR_STACK;
        }
    }

    @Override
    public ItemStack getItemStack(int slot) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return ItemAirStack.AIR_STACK;
        }
        return items[slot];
    }

    @Override
    public void setItemStack(int slot, ItemStack item) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return;
        }
        items[slot] = item != null ? item : ItemAirStack.AIR_STACK;
        sendBlockEntityToViewers();
        getDimension().updateComparatorOutputLevel(getPosition());
    }

    @Override
    public ItemStack swapItemStack(int slot, ItemStack newItem) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return ItemAirStack.AIR_STACK;
        }
        var old = items[slot];
        items[slot] = newItem != null ? newItem : ItemAirStack.AIR_STACK;
        sendBlockEntityToViewers();
        getDimension().updateComparatorOutputLevel(getPosition());
        return old;
    }

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (items[i] != ItemAirStack.AIR_STACK) {
                getDimension().dropItem(items[i], MathUtils.center(position));
                items[i] = ItemAirStack.AIR_STACK;
            }
        }
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putList(TAG_ITEMS, NbtType.COMPOUND, Arrays.stream(this.items).map(ItemStack::saveNBT).toList())
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        for (int i = 0; i < SLOT_COUNT; i++) {
            items[i] = ItemAirStack.AIR_STACK;
        }

        nbt.listenForList(TAG_ITEMS, NbtType.COMPOUND, itemsNbt -> {
            int slot = 0;
            for (var itemNbt : itemsNbt) {
                items[slot] = NBTIO.getAPI().fromItemStackNBT(itemNbt);
                slot++;
            }
        });
    }
}
