package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.LeverDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.PowerSound;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.LEVER_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;

/**
 * Implementation of the lever block, a toggleable redstone power source.
 *
 * @author daoge_cmd
 */
public class BlockLeverBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockLeverBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            LeverDirection direction = calculateLeverDirection(placementInfo);
            blockState = blockState.setPropertyValue(LEVER_DIRECTION, direction);

            // Verify that the attachment block has a full surface on the attached face
            BlockFace attachedFace = getAttachedFace(direction);
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(attachedFace.opposite())) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

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

        // Play lever click sound
        block.addSound(new PowerSound(newPoweredState));

        // Trigger neighbor updates to propagate power change
        dimension.updateAround(block.getPosition());

        // Also update the block the lever is attached to
        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);
        Vector3ic attachedPos = attachedFace.offsetPos(block.getPosition());
        dimension.updateAround(attachedPos);

        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // Check if the attached block still has a full surface
        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);

        if (face == attachedFace) {
            if (!neighbor.getBlockStateData().collisionShape().isFull(attachedFace.opposite())) {
                block.breakBlock();
            }
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        // If the lever was on when removed, update around attached block
        if (oldBlock.getPropertyValue(OPEN_BIT)) {
            LeverDirection direction = oldBlock.getPropertyValue(LEVER_DIRECTION);
            BlockFace attachedFace = getAttachedFace(direction);
            Vector3ic attachedPos = attachedFace.offsetPos(oldBlock.getPosition());
            oldBlock.getDimension().updateAround(attachedPos);
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(OPEN_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Lever provides strong power to the block it's attached to
        if (!block.getPropertyValue(OPEN_BIT)) {
            return 0;
        }

        LeverDirection direction = block.getPropertyValue(LEVER_DIRECTION);
        BlockFace attachedFace = getAttachedFace(direction);

        // Strong power goes INTO the attached block, so we check if the query
        // is coming from the direction of the attached block
        if (face == attachedFace) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    private LeverDirection calculateLeverDirection(PlayerInteractInfo placementInfo) {
        BlockFace clickedFace = placementInfo.blockFace();
        double yaw = placementInfo.player().getLocation().yaw();

        return switch (clickedFace) {
            case DOWN -> {
                // Lever on ceiling, determine orientation based on player facing
                if (isEastWestFacing(yaw)) {
                    yield LeverDirection.DOWN_EAST_WEST;
                } else {
                    yield LeverDirection.DOWN_NORTH_SOUTH;
                }
            }
            case UP -> {
                // Lever on floor, determine orientation based on player facing
                if (isEastWestFacing(yaw)) {
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

    private boolean isEastWestFacing(double yaw) {
        // Normalize yaw to 0-360 range
        yaw = ((yaw % 360) + 360) % 360;
        // East-West is around 90 and 270 degrees
        return (yaw >= 45 && yaw < 135) || (yaw >= 225 && yaw < 315);
    }

    private BlockFace getAttachedFace(LeverDirection direction) {
        return switch (direction) {
            case DOWN_EAST_WEST, DOWN_NORTH_SOUTH -> BlockFace.UP;    // Attached to ceiling
            case UP_EAST_WEST, UP_NORTH_SOUTH -> BlockFace.DOWN;      // Attached to floor
            case EAST -> BlockFace.WEST;    // Attached to west side
            case WEST -> BlockFace.EAST;    // Attached to east side
            case NORTH -> BlockFace.SOUTH;  // Attached to south side
            case SOUTH -> BlockFace.NORTH;  // Attached to north side
        };
    }
}
