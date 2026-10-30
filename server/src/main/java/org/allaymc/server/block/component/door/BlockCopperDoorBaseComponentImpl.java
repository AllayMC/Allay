package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockCopperDoorBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.component.annotation.Dependency;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * {@code BlockCopperDoorBaseComponentImpl} coordinates oxidation and waxing between
 * copper door halves while retaining ordinary door behavior. Only the lower half
 * forwards random updates, and the upper half follows higher oxidation observed below
 * it; this prevents each half from independently advancing through random oxidation.
 *
 * <p>
 * Waxing propagates from either half to its counterpart, but this synchronization
 * path does not propagate unwaxing or lower oxidation. A neighbor with any copper
 * door behavior qualifies, allowing the halves to remain related after a variant
 * change. When both oxidation and wax change together, the code resolves oxidation
 * first and then applies the wax state to that selected type before copying the
 * existing properties.
 *
 * @author IWareQ
 */
public class BlockCopperDoorBaseComponentImpl extends BlockDoorBaseComponentImpl {
    /**
     * Injected copper oxidation capability.
     */
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    /**
     * Creates copper door base behavior for the supplied block type.
     */
    public BlockCopperDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards random updates only for the lower copper door half. The upper half
     * receives oxidation changes through neighbor synchronization instead of making
     * an independent random advancement attempt.
     */
    @Override
    public void onRandomUpdate(Block block) {
        // The lower half owns random oxidation attempts. The upper half follows
        // variant changes through neighbor updates instead of drawing another
        // independent oxidation result.
        if (!block.getPropertyValue(UPPER_BLOCK_BIT)) {
            super.onRandomUpdate(block);
        }
    }

    /**
     * Applies ordinary door support and redstone handling before synchronizing copper
     * variant changes. The neighbor must expose copper door behavior; upper halves
     * can advance to a higher oxidation level observed below.
     *
     * <p>
     * Waxing can propagate from either half, while unwaxing and lower oxidation do
     * not propagate through this path. If both changes apply, oxidation is selected
     * first and waxing uses the selected counterpart so one lookup does not discard
     * the other change. The final type copies the current state's properties before a
     * dimension write.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (!isSameDoor(neighbor)) {
            return;
        }

        var blockBehavior = (BlockCopperDoorBehavior) block.getBlockType().getBlockBehavior();
        var neighborBlockBehavior = (BlockCopperDoorBehavior) neighbor.getBlockType().getBlockBehavior();

        var isUpperBlock = block.getPropertyValue(UPPER_BLOCK_BIT);
        OxidationLevel newOxidationLevel = null;
        boolean shouldbeWaxed = false;
        if (isUpperBlock && face == BlockFace.DOWN) {
            var oxidationLevel = blockBehavior.getOxidationLevel();
            var neighborOxidationLevel = neighborBlockBehavior.getOxidationLevel();
            if (oxidationLevel.ordinal() < neighborOxidationLevel.ordinal()) {
                newOxidationLevel = neighborOxidationLevel;
            }

            if (!blockBehavior.isWaxed() && neighborBlockBehavior.isWaxed()) {
                shouldbeWaxed = true;
            }
        } else if (!isUpperBlock && face == BlockFace.UP) {
            if (!blockBehavior.isWaxed() && neighborBlockBehavior.isWaxed()) {
                shouldbeWaxed = true;
            }
        }

        BlockType<? extends BlockOxidationComponent> newBlockType = null;
        if (newOxidationLevel != null) {
            newBlockType = blockBehavior.getBlockWithOxidationLevel(newOxidationLevel);
        }

        // Apply wax after oxidation selection so simultaneous changes retain the
        // newly selected level instead of reverting to this half's original oxidation
        // type.
        if (shouldbeWaxed) {
            if (newBlockType != null) {
                newBlockType = newBlockType.getBlockBehavior().getBlockWithWaxed(true);
            } else {
                newBlockType = blockBehavior.getBlockWithWaxed(true);
            }
        }

        if (newBlockType != null) {
            var newBlockState = newBlockType.copyPropertyValuesFrom(block.getBlockState());
            block.getDimension().setBlockState(block.getPosition(), newBlockState);
        }
    }

    /**
     * Returns whether the neighbor belongs to any copper door variant.
     */
    @Override
    protected boolean isSameDoor(Block neighbor) {
        return neighbor.getBlockType().getBlockBehavior() instanceof BlockCopperDoorBehavior;
    }
}
