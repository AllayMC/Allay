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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code ItemShulkerBoxBaseComponentImpl} carries a shulker box's slot-indexed
 * inventory between item persistence and placed block entities. The component
 * owns its map container but retains mutable item stack references. Replacing
 * the stored contents copies the map and discards empty or air values.
 *
 * <p>
 * Each saved stack receives a {@code "Slot"} byte inside its NBT compound so
 * loading can rebuild the slot association independently of map iteration order.
 * Slot keys are not validated before narrowing to a byte; callers must supply
 * valid container slots. Placement first applies inherited block-entity NBT and
 * then assigns the retained stacks to a container holder at the placed position.
 *
 * @author daoge_cmd
 */
public class ItemShulkerBoxBaseComponentImpl extends ItemBaseComponentImpl implements ItemShulkerBoxBaseComponent {

    /**
     * {@code TAG_ITEMS} names the stored-stack list, while {@code TAG_SLOT} records
     * each stack's slot inside its compound so list order does not determine
     * inventory placement.
     */
    protected static final String TAG_ITEMS = "Items";
    /**
     * Stored-stack slot byte key.
     */
    protected static final String TAG_SLOT = "Slot";

    /**
     * {@code storedItems} owns slot mappings but retains shared mutable stack values.
     * The getter protects map structure only, and placement passes these values to
     * the placed container.
     */
    protected Map<Integer, ItemStack> storedItems;

    /**
     * Creates a shulker-box component with an initially empty contents map.
     */
    public ItemShulkerBoxBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.storedItems = new HashMap<>();
    }

    /**
     * Returns an unmodifiable view of the current stored-items map.
     * The item stack values remain shared and mutable; replacing {@code storedItems}
     * later does not retarget a previously returned map view.
     */
    @Override
    public @UnmodifiableView Map<Integer, ItemStack> getStoredItems() {
        return Collections.unmodifiableMap(storedItems);
    }

    /**
     * Replaces stored contents with a new map containing nonempty, non-air entries.
     * The map retains supplied stack references and does not validate slot keys;
     * callers must use slots valid for the eventual placed container.
     */
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

    /**
     * Rebuilds stored slot mappings from a present item-compound list after inherited
     * loading. An absent list leaves the current map unchanged; a present list
     * creates a replacement map even when it is empty.
     *
     * <p>
     * Each compound's signed byte slot identifies its item independently of list
     * order. Later duplicate slots replace earlier entries. Stack decoding delegates
     * to {@code NBTIO}, whose server implementation can substitute air after a load
     * failure; this loader retains that result rather than applying setter filtering.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForList(TAG_ITEMS, NbtType.COMPOUND, itemsNbt -> {
            var map = new HashMap<Integer, ItemStack>();
            for (var nbt : itemsNbt) {
                // Slot belongs to the stack compound rather than its list
                // position, preserving inventory placement when map iteration changes.
                int slot = nbt.getByte(TAG_SLOT);
                map.put(slot, NBTIO.getAPI().fromItemStackNBT(nbt));
            }
            this.storedItems = map;
        });
    }

    /**
     * Adds stored stacks and their slot associations to inherited extra NBT.
     * An empty map adds no item list; otherwise each stack saves its own complete
     * item compound before this method adds the slot byte.
     *
     * <p>
     * The explicit slot field preserves association despite arbitrary map iteration
     * order. Slot integers narrow to signed bytes without validation, so out-of-range
     * keys can change on a later load. Callers must keep keys within valid container
     * slots rather than rely on this method to reject them.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (!storedItems.isEmpty()) {
            var itemsNbt = new ArrayList<NbtMap>(storedItems.size());
            for (var entry : storedItems.entrySet()) {
                // Persist the slot with its stack so decoding does not depend on
                // hash-map iteration order; valid container slots fit this byte.
                itemsNbt.add(entry.getValue().saveNBT()
                        .toBuilder()
                        .putByte(TAG_SLOT, (byte)(int) entry.getKey())
                        .build());
            }
            builder.putList(TAG_ITEMS, NbtType.COMPOUND, itemsNbt);
        }
        return builder.build();
    }

    /**
     * Applies inherited block-entity data before assigning stored item references to
     * a placed container holder. A missing block entity or a block entity without
     * a container receives no stored-item assignments; invalid slot keys are left
     * to the container implementation.
     */
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
