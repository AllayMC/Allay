package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockStandingSignBaseComponentImpl} maintains standing signs and removes
 * them when their floor loses solid support. Normal and silk-touch drops both
 * resolve the configured sign item lazily, allowing block and item registration to
 * use separate initialization order.
 *
 * @author daoge_cmd
 */
public class BlockStandingSignBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code dropItemType} resolves the sign item type when each drop is
     * requested. The constructor retains the supplied resolver, allowing item
     * registration to finish before that lookup occurs.
     */
    protected final Supplier<ItemType<?>> dropItemType;

    /**
     * Creates a sign component retaining its lazy drop-item resolver.
     * {@code dropItemTypeSupplier} is called on each drop request and must supply
     * a usable item type when those requests occur.
     */
    public BlockStandingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> dropItemTypeSupplier) {
        super(blockType);
        this.dropItemType = dropItemTypeSupplier;
    }

    /**
     * Creates a sign component whose drop type resolves from the supplied item
     * identifier. The constructor retains the identifier's lookup operation rather
     * than resolving its item type immediately.
     */
    public BlockStandingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItemId) {
        this(blockType, dropItemId::getItemType);
    }

    /**
     * Runs shared handling and checks the standing sign's support below. A
     * nonsolid neighbor breaks the sign only for a downward-side update, leaving
     * other neighbor changes to shared handling.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    /**
     * Returns one newly created item from the lazy sign drop type.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemType.get().createItemStack(1));
    }

    /**
     * Returns one newly created sign item for the silk-touch path.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return dropItemType.get().createItemStack(1);
    }
}
