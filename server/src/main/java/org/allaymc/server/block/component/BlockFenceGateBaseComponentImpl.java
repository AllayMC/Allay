package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.FenceGateSound;
import org.allaymc.server.block.BlockPlaceHelper;
import org.joml.Vector3ic;

import static org.allaymc.api.block.data.BlockTags.POWER_SOURCE;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockFenceGateBaseComponentImpl} handles gate orientation, wall lowering,
 * manual opening, and redstone response. Wall adjacency controls the lowered shape
 * independently of opening; neighbor power checks run only when the changed neighbor
 * is or was a tagged power source, preserving manual gate state after unrelated
 * changes.
 *
 * @author IWareQ
 */
public class BlockFenceGateBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the fence gate block behavior for the supplied block type.
     */
    public BlockFenceGateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the update, refreshes wall lowering, and checks qualifying
     * power-source changes. {@code oldNeighborState} may be {@code null}; the new
     * neighbor alone then determines whether redstone state is reevaluated.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        block.updateBlockProperty(IN_WALL_BIT, shouldBeLowered(block));
        // Only newly present or removed tagged power sources reevaluate opening, so
        // ordinary neighbor changes do not close a manually opened gate.
        boolean isNewPowerSource = neighbor.getBlockType().hasBlockTag(POWER_SOURCE);
        boolean wasOldPowerSource = oldNeighborState != null && oldNeighborState.getBlockType().hasBlockTag(POWER_SOURCE);
        
        if (isNewPowerSource || wasOldPowerSource) {
            checkRedstonePower(block);
        }
    }

    /**
     * Places a gate with player-derived cardinal facing and wall-dependent lowering.
     * {@code placementInfo} may be {@code null}, in which case the facing helper
     * retains the incoming state's existing direction.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        blockState = BlockPlaceHelper.processMinecraftCardinalDirectionProperty(blockState, placeBlockPos, placementInfo);
        var current = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = blockState.setPropertyValue(IN_WALL_BIT, shouldBeLowered(current));
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    /**
     * Forwards the postplacement event and synchronizes the new gate with nearby
     * redstone power. The new block snapshot uses the incoming state and the old
     * block's position for this initial check.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        var newBlock = new Block(newBlockState, oldBlock.getPosition());
        checkRedstonePower(newBlock);
    }

    /**
     * Offers the interaction to companion components, then toggles the gate with its
     * sound. Opening can reverse gate facing to match the player;
     * {@code interactInfo} must be present for this manual path.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedPos = interactInfo.clickedBlockPos();
        var clickedBlockState = interactInfo.getClickedBlock();
        var open = !clickedBlockState.getPropertyValue(OPEN_BIT);
        if (open) {
            var playerFacing = interactInfo.player().getHorizontalFace();
            var direction = clickedBlockState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
            if (playerFacing == BlockFace.from(direction).opposite()) {
                dimension.updateBlockProperty(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection(), clickedPos);
            }
        }

        clickedBlockState.updateBlockProperty(OPEN_BIT, open);
        clickedBlockState.addSound(new FenceGateSound(clickedBlockState.getBlockState(), open));
        return true;
    }

    /**
     * Returns whether either side perpendicular to the gate facing contains a wall.
     */
    private boolean shouldBeLowered(Block current) {
        var direction = current.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        blockFace = blockFace.rotateY();
        return current.offsetPos(blockFace).getBehavior() instanceof BlockWallBehavior ||
               current.offsetPos(blockFace.opposite()).getBehavior() instanceof BlockWallBehavior;
    }

    /**
     * Synchronizes gate opening with whether its power level is positive. The state
     * and sound change only when the requested opening differs from the gate's
     * current {@code OPEN_BIT}.
     */
    protected void checkRedstonePower(Block block) {
        var power = block.getPower();

        var shouldBeOpen = power > 0;
        var isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            block.updateBlockProperty(OPEN_BIT, shouldBeOpen);
            block.addSound(new FenceGateSound(block.getBlockState(), shouldBeOpen));
        }
    }
}
