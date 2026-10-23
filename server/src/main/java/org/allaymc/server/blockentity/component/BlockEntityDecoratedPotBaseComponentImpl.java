package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

/**
 * {@code BlockEntityDecoratedPotBaseComponentImpl} stores a pot's single item
 * stack and four decorated faces. One-item insertion either copies an initial
 * stack or merges into compatible contents, then updates comparator output and
 * viewers. The caller's input stack is never decremented by that insertion
 * path.
 *
 * <p>
 * {@code tryInsertItem} checks both the pot capacity and the retained item's
 * stack limit before increasing existing contents. Direct {@code setItem} calls
 * bypass those checks and retain the supplied mutable stack. NBT loading also
 * assigns decoded contents directly and reloads decoration independently; it
 * does not recalculate comparator output or broadcast those loaded values.
 *
 * @author IWareQ
 */
public class BlockEntityDecoratedPotBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityDecoratedPotBaseComponent {
    /**
     * {@code TAG_ITEM} names the stored stack compound, keeping pot contents
     * separate from the four decoration identifiers during NBT loading and
     * saving.
     */
    protected static final String TAG_ITEM = "item";

    /**
     * {@code item} holds shared mutable pot contents, or {@code null} when
     * empty. The generated getter and {@code setItem} expose or retain the same
     * stack reference.
     */
    @Getter
    private ItemStack item;
    /**
     * Current four-face pot decoration.
     */
    @Getter
    private PotSherds sherds = PotSherds.DEFAULT;

    /**
     * Creates common pot state with empty contents and four brick faces.
     */
    public BlockEntityDecoratedPotBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Retains the supplied stack and refreshes comparator output before
     * viewers. {@code null} clears contents; the method performs no copy or
     * capacity check, and a later update failure leaves the new reference
     * stored.
     */
    @Override
    public void setItem(ItemStack item) {
        this.item = item;
        this.getDimension().updateComparatorOutputLevel(getPosition());
        this.sendBlockEntityToViewers();
    }

    /**
     * Attempts to store one item while leaving the supplied stack unchanged. An
     * empty pot receives a copied stack with count one. Existing contents must
     * merge with the input when count is ignored and remain below both the pot
     * capacity and their item-type stack limit. Rejected inputs leave contents
     * unchanged and return {@code false}; accepted inputs update comparator
     * output and viewers after storing the item, without rollback if those
     * updates fail.
     */
    @Override
    public boolean tryInsertItem(ItemStack itemToInsert) {
        if (itemToInsert == null || itemToInsert.getCount() <= 0) {
            return false;
        }

        if (this.item == null) {
            var newItem = itemToInsert.copy(true);
            newItem.setCount(1);
            this.setItem(newItem);
            return true;
        }

        if (!this.item.canMerge(itemToInsert, true)) {
            return false;
        }

        var count = this.item.getCount();
        // The pot capacity does not override smaller item-type limits, so both
        // bounds must allow the next single-item increment before changing
        // contents.
        if (count >= MAX_STACK_SIZE || count >= this.item.getItemType().getItemData().maxStackSize()) {
            return false;
        }

        this.item.increaseCount(1);
        this.getDimension().updateComparatorOutputLevel(getPosition());
        this.sendBlockEntityToViewers();
        return true;
    }

    /**
     * Stores decorated faces and sends updated pot state to viewers.
     * {@code null} selects {@code PotSherds.DEFAULT}; the stored item remains
     * unchanged, and delivery failure does not restore the previous decoration.
     */
    @Override
    public void setSherds(PotSherds sherds) {
        this.sherds = sherds != null ? sherds : PotSherds.DEFAULT;
        this.sendBlockEntityToViewers();
    }

    /**
     * Copies decorated faces from a held decorated-pot item during placement.
     * Events without placement information or without that item capability
     * preserve the current decoration and do not replace the stored contents.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) {
            return;
        }

        var itemStack = placementInfo.player().getItemInHand();
        if (itemStack instanceof ItemDecoratedPotBaseComponent decoratedPotItem) {
            this.sherds = decoratedPotItem.getSherds();
        }
    }

    /**
     * Drops the stored stack when the decorated-pot block is replaced. An empty
     * pot produces no item drop; occupied contents are cleared only after the
     * dimension's drop operation returns.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (this.item == null) {
            return;
        }

        var current = event.getCurrentBlock();
        current.getDimension().dropItem(this.item, MathUtils.center(current.getPosition()));
        this.item = null;
    }

    /**
     * Builds common NBT with any retained item and all four decorated faces. An
     * empty pot omits the item compound; decoration is serialized independently
     * through the retained {@code PotSherds} value.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        if (this.item != null) {
            builder.putCompound(TAG_ITEM, this.item.saveNBT());
        }
        builder.putList(PotSherds.TAG_SHERDS, NbtType.STRING, this.sherds.toNBTList());
        return builder.build();
    }

    /**
     * Loads common state, any supplied item compound, and pot decoration. A
     * missing item compound preserves contents, while a missing or incomplete
     * sherd list resets decoration through {@code PotSherds.fromNBT}.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_ITEM, value -> this.item = NBTIO.getAPI().fromItemStackNBT(value));
        this.sherds = PotSherds.fromNBT(nbt);
    }
}
