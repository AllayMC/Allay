package org.allaymc.server.item.component;

import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemShulkerBoxBaseComponent;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3ic;

import java.util.*;

/**
 * @author daoge_cmd
 */
public class ItemShulkerBoxBaseComponentImpl extends ItemBaseComponentImpl implements ItemShulkerBoxBaseComponent {

    protected static final String TAG_ITEMS = "Items";
    protected static final String TAG_SLOT = "Slot";

    protected Map<Integer, ItemStack> storedItems;

    public ItemShulkerBoxBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.storedItems = new HashMap<>();
    }

    @Override
    public @UnmodifiableView Map<Integer, ItemStack> getStoredItems() {
        return Collections.unmodifiableMap(storedItems);
    }

    @Override
    public void setStoredItems(Map<Integer, ItemStack> items) {
        var map = new HashMap<Integer, ItemStack>();
        for (var entry : items.entrySet()) {
            if (!entry.getValue().isEmptyOrAir()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        this.storedItems = map;
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForList(TAG_ITEMS, NbtType.COMPOUND, itemsNbt -> {
            var map = new HashMap<Integer, ItemStack>();
            for (var nbt : itemsNbt) {
                int slot = nbt.getByte(TAG_SLOT);
                map.put(slot, NBTIO.getAPI().fromItemStackNBT(nbt));
            }
            this.storedItems = map;
        });
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (!storedItems.isEmpty()) {
            var itemsNbt = new ArrayList<NbtMap>(storedItems.size());
            for (var entry : storedItems.entrySet()) {
                itemsNbt.add(entry.getValue().saveNBT()
                        .toBuilder()
                        .putByte(TAG_SLOT, (byte)(int) entry.getKey())
                        .build());
            }
            builder.putList(TAG_ITEMS, NbtType.COMPOUND, itemsNbt);
        }
        return builder.build();
    }

    @Override
    protected void tryApplyBlockEntityNBT(Dimension dimension, Vector3ic placeBlockPos) {
        super.tryApplyBlockEntityNBT(dimension, placeBlockPos);
        var blockEntity = dimension.getBlockEntity(placeBlockPos);
        if (blockEntity instanceof BlockEntityContainerHolderComponent component) {
            var container = component.getContainer();
            for (var entry : storedItems.entrySet()) {
                container.setItemStack(entry.getKey(), entry.getValue(), false);
            }
        }
    }
}
