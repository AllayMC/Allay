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
 * @author IWareQ
 */
public class BlockEntityDecoratedPotBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityDecoratedPotBaseComponent {
    protected static final String TAG_ITEM = "item";

    @Getter
    private ItemStack item;
    @Getter
    private PotSherds sherds = PotSherds.DEFAULT;

    public BlockEntityDecoratedPotBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void setItem(ItemStack item) {
        this.item = item;
        this.getDimension().updateComparatorOutputLevel(getPosition());
        this.sendBlockEntityToViewers();
    }

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
        if (count >= MAX_STACK_SIZE || count >= this.item.getItemType().getItemData().maxStackSize()) {
            return false;
        }

        this.item.increaseCount(1);
        this.getDimension().updateComparatorOutputLevel(getPosition());
        this.sendBlockEntityToViewers();
        return true;
    }

    @Override
    public void setSherds(PotSherds sherds) {
        this.sherds = sherds != null ? sherds : PotSherds.DEFAULT;
        this.sendBlockEntityToViewers();
    }

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

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (this.item == null) {
            return;
        }

        var current = event.getCurrentBlock();
        current.getDimension().dropItem(this.item, MathUtils.center(current.getPosition()));
        this.item = null;
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        if (this.item != null) {
            builder.putCompound(TAG_ITEM, this.item.saveNBT());
        }
        builder.putList(PotSherds.TAG_SHERDS, NbtType.STRING, this.sherds.toNBTList());
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_ITEM, value -> this.item = NBTIO.getAPI().fromItemStackNBT(value));
        this.sherds = PotSherds.fromNBT(nbt);
    }
}
