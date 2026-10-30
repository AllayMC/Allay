package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.LeverDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.LEVER_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;

/**
 * {@code BlockLeverBaseComponentImpl} implements attached levers whose stored
 * {@code OPEN_BIT} controls redstone output. Placement combines the clicked support
 * face and player orientation into the lever direction encoding. Interaction toggles
 * power and notifies both the lever's neighboring circuits and the supporting block's
 * neighbors.
 *
 * <p>
 * Floor and ceiling mounts each have two direction values, selected from the player's
 * east-west or north-south axis. Wall mounts instead use the clicked cardinal face
 * directly, so player rotation does not change their support direction.
 * {@code getAttachedFace} then maps those encodings to the supporting block for
 * placement validation, support-loss checks, and strong-power output. These paths must
 * agree on that mapping so a lever tests and powers the same block to which it is
 * attached.
 *
 * @author daoge_cmd
 */
public class BlockLeverBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the lever component for the supplied registered block type.
     */
    public BlockLeverBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a lever using player orientation and the clicked support face. Player
     * placement requires a full attachment surface; {@code null} {@code placementInfo}
     * preserves the supplied direction and skips support validation.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            LeverDirection direction = calculateLeverDirection(placementInfo);
            blockState = blockState.setPropertyValue(LEVER_DIRECTION, direction);

            BlockFace attachedFace = getAttachedFace(direction);
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(attachedFace.opposite())) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Toggles the clicked lever unless inherited interaction handling consumes the
     * action. A {@code null} {@code interactInfo} returns {@code false}; otherwise the
     * requested power change plays a sound and notifies the lever's neighbors and
     * support.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }
        if (interactInfo == null) {
            return false;
        }

        Block block = interactInfo.getClickedBlock();
        boolean newPoweredState = !block.getPropertyValue(OPEN_BIT);
        block.updateBlockProperty(OPEN_BIT, newPoweredState);

        block.addSound(newPoweredState ? SimpleSound.POWER_ON : SimpleSound.POWER_OFF);

        dimension.updateAround(block.getPosition());

        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);
        Vector3ic attachedPos = attachedFace.offsetPos(block.getPosition());
        // The support can relay strong power to circuits beyond the lever's
        // immediate neighbors, so notify its surroundings after the requested toggle.
        dimension.updateAround(attachedPos);

        return true;
    }

    /**
     * Checks the lever's attachment surface after inherited neighbor handling. Updates
     * from the support side ask an unsupported lever to break, while notifications from
     * other sides cause no additional check.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);

        if (face == attachedFace) {
            if (!neighbor.getBlockStateData().collisionShape().isFull(attachedFace.opposite())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Notifies the former support's neighboring circuits after a powered lever is
     * replaced. Inherited replacement handling runs first, and an unpowered old
     * snapshot needs no extra notification from this callback.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        if (oldBlock.getPropertyValue(OPEN_BIT)) {
            LeverDirection direction = oldBlock.getPropertyValue(LEVER_DIRECTION);
            BlockFace attachedFace = getAttachedFace(direction);
            Vector3ic attachedPos = attachedFace.offsetPos(oldBlock.getPosition());
            oldBlock.getDimension().updateAround(attachedPos);
        }
    }

    /**
     * Returns maximum weak power on every face when {@code OPEN_BIT} is set. An unset
     * bit returns {@code 0}, and this query does not sample surrounding redstone power.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(OPEN_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns maximum strong power only toward the support of a powered lever. Other
     * queried faces and unpowered snapshots return {@code 0}, leaving support-based
     * propagation to the dimension.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(OPEN_BIT)) {
            return 0;
        }

        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);

        if (face == attachedFace) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    /**
     * Selects the lever direction encoding from the clicked face and the player's
     * horizontal axis. Floor and ceiling placement distinguish east-west player
     * alignment from north-south alignment, selecting the matching floor or ceiling mount
     * variant. Wall placement returns the clicked cardinal direction
     * without using the player axis.
     *
     * <p>
     * The two encodings for each floor or ceiling mount preserve its visible axis while
     * sharing one support face. {@code getAttachedFace} later resolves either floor
     * variant downward and either ceiling variant upward, keeping orientation selection
     * consistent with support checks and strong-power queries.
     */
    private LeverDirection calculateLeverDirection(PlayerInteractInfo placementInfo) {
        BlockFace clickedFace = placementInfo.blockFace();
        var playerFace = placementInfo.player().getHorizontalFace();

        return switch (clickedFace) {
            case DOWN -> {
                if (playerFace == BlockFace.EAST || playerFace == BlockFace.WEST) {
                    yield LeverDirection.DOWN_EAST_WEST;
                } else {
                    yield LeverDirection.DOWN_NORTH_SOUTH;
                }
            }
            case UP -> {
                if (playerFace == BlockFace.EAST || playerFace == BlockFace.WEST) {
                    yield LeverDirection.UP_EAST_WEST;
                } else {
                    yield LeverDirection.UP_NORTH_SOUTH;
                }
            }
            case NORTH -> LeverDirection.NORTH;
            case SOUTH -> LeverDirection.SOUTH;
            case WEST -> LeverDirection.WEST;
            case EAST -> LeverDirection.EAST;
        };
    }

    /**
     * Returns the direction from the lever toward its supporting block. Floor and
     * ceiling variants share support faces across their two axis encodings, while wall
     * variants point opposite their exposed facing.
     */
    private BlockFace getAttachedFace(LeverDirection direction) {
        return switch (direction) {
            case DOWN_EAST_WEST, DOWN_NORTH_SOUTH -> BlockFace.UP;
            case UP_EAST_WEST, UP_NORTH_SOUTH -> BlockFace.DOWN;
            case EAST -> BlockFace.WEST;
            case WEST -> BlockFace.EAST;
            case NORTH -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.NORTH;
        };
    }
}
