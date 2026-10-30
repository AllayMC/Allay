package org.allaymc.server.block.component.chorus;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockChorusPlantBaseComponentImpl} implements chorus-plant support and ordinary fruit
 * drops. Lower chorus plants or end stone provide direct support, while a supported horizontal
 * plant can support a branch only when the current plant is not blocked both above and below.
 *
 * <p>
 * Neighbor updates re-evaluate that combined support rule and break unsupported segments
 * through normal block breaking. The sideways case takes precedence once a supported neighbor
 * is found, so the current block's own lower support is not an unconditional override for a
 * vertically blocked branch. Ordinary drops independently choose a fruit with equal
 * probability; silk touch supplies an air item instead of preserving the plant.
 *
 * @author IWareQ
 */
public class BlockChorusPlantBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the chorus plant behavior component for the supplied block type.
     */
    public BlockChorusPlantBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the base neighbor hook and rechecks the plant's combined direct and sideways
     * support. Any neighbor face can trigger breaking when the current surrounding states fail
     * the support predicate.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (!canBeSupportedAt(block)) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the current plant has valid direct or lateral support. A horizontal
     * chorus-plant neighbor counts only when it itself has chorus plant or end stone below.
     * Once such a neighbor exists, the current segment must not be blocked both above and
     * below.
     *
     * <p>
     * If no eligible side neighbor exists, the current segment relies entirely on its own
     * lower support. The two paths deliberately use different observations: lateral support
     * includes vertical clearance, while direct support checks only the lower block type.
     */
    private boolean canBeSupportedAt(Block current) {
        boolean sideSupport = false;
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            var neighbor = current.offsetPos(face);
            if (neighbor.getBlockType() == BlockTypes.CHORUS_PLANT && hasDownSupport(neighbor)) {
                sideSupport = true;
                break;
            }
        }

        if (sideSupport) {
            var upperBlocked = !current.offsetPos(BlockFace.UP).isAir();
            var downBlocked = !current.offsetPos(BlockFace.DOWN).isAir();
            return !(upperBlocked && downBlocked);
        } else {
            return hasDownSupport(current);
        }
    }

    /**
     * Returns whether chorus plant or end stone lies directly below.
     */
    private boolean hasDownSupport(Block current) {
        var downType = current.offsetPos(BlockFace.DOWN).getBlockType();
        return downType == BlockTypes.CHORUS_PLANT || downType == BlockTypes.END_STONE;
    }

    /**
     * Returns one chorus fruit with equal probability or an empty drop set. The random choice
     * does not depend on the supplied tool or breaking entity in this implementation.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return Set.of(ItemTypes.CHORUS_FRUIT.createItemStack());
        }

        return Set.of();
    }

    /**
     * Returns an air item instead of preserving the chorus plant.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return ItemTypes.AIR.createItemStack();
    }
}
