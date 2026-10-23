package org.allaymc.server.block.component.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockDoubleSlabBaseComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;

import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockDoubleSlabBaseComponentImpl} resolves the single-slab counterpart used
 * for ordinary double-slab drops. The supplier delays counterpart lookup until
 * needed, while ordinary drops create two single-slab items and the inherited base
 * behavior chooses the separate silk touch path.
 *
 * @author daoge_cmd
 */
public class BlockDoubleSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockDoubleSlabBaseComponent {
    /**
     * {@code singleSlabType} resolves the single-slab counterpart when needed,
     * allowing block type registration to complete after this component is
     * constructed.
     */
    protected final Supplier<BlockType<?>> singleSlabType;

    /**
     * Creates slab behavior with a retained single-slab type supplier. The supplier
     * is evaluated by counterpart lookup, so construction does not require that
     * related block type to be available immediately.
     */
    public BlockDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> singleSlabTypeSupplier) {
        super(blockType);
        this.singleSlabType = singleSlabTypeSupplier;
    }

    /**
     * Creates slab behavior using a deferred lookup for its single-slab identifier.
     * {@code singleSlabId} resolves the counterpart only when placement or drop
     * handling needs the related block type.
     */
    public BlockDoubleSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId singleSlabId) {
        this(blockType, singleSlabId::getBlockType);
    }

    /**
     * Returns the single-slab block type resolved by the retained supplier.
     */
    @Override
    public BlockType<?> getSingleSlabBlockType() {
        return singleSlabType.get();
    }

    /**
     * Returns two single-slab items as the ordinary double-slab block drop.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(getSingleSlabBlockType().getItemType().createItemStack(2));
    }
}
