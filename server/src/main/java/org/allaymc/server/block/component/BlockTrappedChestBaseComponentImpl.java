package org.allaymc.server.block.component;

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
import org.allaymc.server.component.annotation.Dependency;

/**
 * Implementation of the trapped chest block.
 * Outputs redstone signal based on the number of players viewing the chest.
 *
 * @author daoge_cmd
 */
public class BlockTrappedChestBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    public BlockTrappedChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var thisChest = blockEntityHolderComponent.getBlockEntity(oldBlock.getPosition());
        if (thisChest == null) {
            return;
        }

        // Add listeners to update redstone when viewers change
        setupContainerListeners(thisChest, oldBlock);

        if (placementInfo != null && placementInfo.player().isSneaking()) {
            // Java edition behavior: Do not check for pairing if the player is sneaking
            return;
        }

        // Only pair with other trapped chests
        var direction = newBlockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        for (var face : new BlockFace[]{blockFace.rotateY(), blockFace.rotateYCCW()}) {
            var neighborBlock = oldBlock.offsetPos(face);
            // Check if neighbor is also a trapped chest
            if (neighborBlock.getBlockType() != BlockTypes.TRAPPED_CHEST) {
                continue;
            }
            var other = neighborBlock.getBlockEntity();
            if (other instanceof BlockEntityChest otherChest && !otherChest.isPaired()) {
                if (direction == otherChest.getBlockState().getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)) {
                    if (otherChest.tryPairWith(thisChest)) {
                        thisChest.tryPairWith(otherChest);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Sets up container listeners to update redstone when viewers change.
     */
    protected void setupContainerListeners(BlockEntityChest chest, Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();

        // Get the container and add listeners
        Container container = chest.getContainer();
        container.addOpenListener(viewer -> dimension.updateAround(pos));
        container.addCloseListener(viewer -> dimension.updateAround(pos));

        // Also set up listeners for the double chest container
        Container doubleContainer = chest.getDoubleChestContainer();
        if (doubleContainer != container) {
            doubleContainer.addOpenListener(viewer -> dimension.updateAround(pos));
            doubleContainer.addCloseListener(viewer -> dimension.updateAround(pos));
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        var chest = (BlockEntityChest) block.getBlockEntity();
        if (chest == null) {
            return 0;
        }

        return calculateSignalStrength(chest);
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Trapped chest only provides strong power downward (to block below)
        if (face != BlockFace.DOWN) {
            return 0;
        }
        return getWeakPower(block, face);
    }

    /**
     * Calculates the redstone signal strength based on the number of viewers.
     * Signal strength is min(15, viewerCount).
     */
    protected int calculateSignalStrength(BlockEntityChest chest) {
        Container container;
        if (chest.isPaired()) {
            container = chest.getDoubleChestContainer();
        } else {
            container = chest.getContainer();
        }

        int viewerCount = container.getViewers().size();
        return Math.min(viewerCount, MAX_REDSTONE_POWER);
    }
}
