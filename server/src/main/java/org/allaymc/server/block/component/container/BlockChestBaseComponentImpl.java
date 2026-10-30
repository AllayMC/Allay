package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.container.Container;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code BlockChestBaseComponentImpl} pairs newly placed chests with a compatible
 * side neighbor and exposes single- or double-inventory comparator fullness.
 * Sneaking player placement keeps the chest independent. Other placements search
 * the two sides perpendicular to the facing, preferring the clockwise side when
 * both are available.
 *
 * <p>
 * Pairing is recorded on the neighbor before the new chest records the reciprocal
 * relationship. The second result is ignored and the sequence has no rollback, so
 * the component relies on the pairable implementations and usable block entities
 * for the normal reciprocal result. Comparator queries choose the combined view
 * only when the queried chest reports a pair.
 *
 * @author IWareQ
 */
public class BlockChestBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code blockEntityHolderComponent} resolves chest pairing and inventory
     * state. Component initialization injects this dependency before the composed
     * block behavior handles its world callbacks.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared placement handling before attempting a same-facing chest pair.
     * Sneaking player placement skips pairing, while placement without player
     * information still searches the two perpendicular sides. Only an unpaired
     * neighbor with the same facing is considered. The neighbor records this chest
     * first, then this chest records the neighbor, and the scan stops. The second
     * pairing result is ignored, so a failed reciprocal step leaves the first
     * relationship in place.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        if (placementInfo != null && placementInfo.player().isSneaking()) {
            return;
        }

        var thisChest = blockEntityHolderComponent.getBlockEntity(oldBlock.getPosition());

        var direction = newBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        for (var face : new BlockFace[]{blockFace.rotateY(), blockFace.rotateYCCW()}) {
            var other = oldBlock.offsetPos(face).getBlockEntity();
            if (other instanceof BlockEntityChest otherChest && !otherChest.isPaired()) {
                if (direction == otherChest.getBlockState().getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)) {
                    // Record the neighbor's pairing first so the pairable
                    // component chooses its lead before this chest establishes the
                    // reciprocal relationship.
                    if (otherChest.tryPairWith(thisChest)) {
                        thisChest.tryPairWith(otherChest);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Returns whether this container supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the fullness signal from the chest's active inventory view. A paired
     * chest uses its combined container; an unpaired chest uses its own container,
     * and a missing entity returns zero.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var chest = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (chest == null) {
            return 0;
        }
        Container container = chest.isPaired() ? chest.getDoubleChestContainer() : chest.getContainer();
        return container.calculateComparatorSignal();
    }
}
