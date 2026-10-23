package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.container.Container;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code BlockTrappedChestBaseComponentImpl} pairs trapped chests and produces
 * redstone power from viewers of their active inventory view. Pairing considers
 * only trapped neighbors with matching facing, and sneaking player placement skips
 * that pairing search. Single and combined views both receive open and close
 * listeners for surrounding-block updates.
 *
 * <p>
 * Viewer power reads the combined container only while the chest reports a pair,
 * then caps the viewer count at the maximum redstone strength. The component
 * registers listeners before its pairing attempt, allowing later combined-view
 * configuration to retain those notifications. Pairing itself is an ordered
 * two-sided operation, with no rollback when the reciprocal step fails.
 *
 * @author daoge_cmd
 */
public class BlockTrappedChestBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves trapped-chest pairing and active
     * views. Component initialization injects this dependency before the composed
     * block behavior handles its world callbacks.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockTrappedChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared placement handling, registers viewer notifications, and attempts
     * a compatible trapped-chest pair. A missing entity ends processing, while
     * sneaking placement retains the registered listeners and skips pairing. The
     * first compatible unpaired trapped neighbor records this chest before the
     * reciprocal pairing is attempted. Its second result is ignored and the search
     * stops, so a failed reciprocal step leaves the first relationship in place.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var thisChest = blockEntityHolderComponent.getBlockEntity(oldBlock.getPosition());
        if (thisChest == null) {
            return;
        }

        // Attach notifications before pairing so later use of the combined view
        // still updates surrounding redstone when its viewers open or close the
        // chest.
        setupContainerListeners(thisChest, oldBlock);

        if (placementInfo != null && placementInfo.player().isSneaking()) {
            return;
        }

        var direction = newBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        for (var face : new BlockFace[]{blockFace.rotateY(), blockFace.rotateYCCW()}) {
            var neighborBlock = oldBlock.offsetPos(face);
            if (neighborBlock.getBlockType() != BlockTypes.TRAPPED_CHEST) {
                continue;
            }
            var other = neighborBlock.getBlockEntity();
            if (other instanceof BlockEntityChest otherChest && !otherChest.isPaired()) {
                if (direction == otherChest.getBlockState().getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)) {
                    // Let the neighboring chest establish its lead state before
                    // this chest records the reciprocal relationship; the
                    // following result is not checked for rollback.
                    if (otherChest.tryPairWith(thisChest)) {
                        thisChest.tryPairWith(otherChest);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Registers surrounding-block updates for container open and close
     * notifications. Both the chest's own container and its distinct combined view
     * receive listeners; this method registers callbacks on each call without
     * retaining removal handles.
     */
    protected void setupContainerListeners(BlockEntityChest chest, Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();

        Container container = chest.getContainer();
        container.addOpenListener(viewer -> dimension.updateAround(pos));
        container.addCloseListener(viewer -> dimension.updateAround(pos));

        Container doubleContainer = chest.getDoubleChestContainer();
        if (doubleContainer != container) {
            doubleContainer.addOpenListener(viewer -> dimension.updateAround(pos));
            doubleContainer.addCloseListener(viewer -> dimension.updateAround(pos));
        }
    }

    /**
     * Returns the viewer-based strength of the chest's active inventory view. An
     * absent block entity produces zero, while a present entity must be a chest
     * compatible with this block behavior.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        var chest = (BlockEntityChest) block.getBlockEntity();
        if (chest == null) {
            return 0;
        }

        return calculateSignalStrength(chest);
    }

    /**
     * Returns viewer-based strength only toward the block below the chest. Every
     * other face returns zero; the downward case delegates to the same weak-power
     * calculation. A missing chest therefore also produces zero.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (face != BlockFace.DOWN) {
            return 0;
        }
        return getWeakPower(block, face);
    }

    /**
     * Returns the active container's viewer count capped at maximum redstone
     * strength. A paired chest selects its combined view, while an unpaired chest
     * uses its own inventory.
     */
    protected int calculateSignalStrength(BlockEntityChest chest) {
        Container container;
        if (chest.isPaired()) {
            container = chest.getDoubleChestContainer();
        } else {
            container = chest.getContainer();
        }

        int viewerCount = container.getViewers().size();
        // Viewer count can exceed the fifteen-level redstone signal range.
        return Math.min(viewerCount, MAX_REDSTONE_POWER);
    }
}
