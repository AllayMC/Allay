package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.DoorSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author Dhaiven
 */
public class BlockDoorBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downBlockState.getBlockStateData().isSolid()) {
            return false;
        }

        var upBlockState = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        if (!upBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        BlockFace face = BlockFace.SOUTH;
        if (placementInfo != null) {
            face = placementInfo.player().getHorizontalFace();
        }
        blockState = blockState.setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, face.rotateY().toMinecraftCardinalDirection());

        var leftBlockState = dimension.getBlockState(face.rotateYCCW().offsetPos(placeBlockPos));
        var rightBlockState = dimension.getBlockState(face.rotateY().offsetPos(placeBlockPos));

        var hingeOnLeft = leftBlockState.getBlockType() == getBlockType() ||
                          (!rightBlockState.getBlockStateData().isTransparent() && leftBlockState.getBlockStateData().isTransparent());
        if (hingeOnLeft) { // Door hinge
            blockState = blockState.setPropertyValue(DOOR_HINGE_BIT, true);
        }

        // Bottom and top
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, false), placementInfo) &&
               dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, true), placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        var keep = true;
        if (face == BlockFace.UP) {
            keep = block.getPropertyValue(UPPER_BLOCK_BIT) || isSameDoor(neighbor);
        } else if (face == BlockFace.DOWN) {
            keep = block.getPropertyValue(UPPER_BLOCK_BIT) ? isSameDoor(neighbor) : neighbor.getBlockStateData().isSolid();
        }

        if (!keep) {
            block.breakBlock();
            return;
        }

        // Check redstone power and update door state
        checkRedstonePower(block);
    }

    /**
     * Checks if the door should be opened/closed based on redstone power.
     *
     * @param block the door block
     */
    protected void checkRedstonePower(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        boolean isUpperBlock = block.getPropertyValue(UPPER_BLOCK_BIT);

        // Get the position of the other door half
        Vector3ic otherPos = isUpperBlock
                ? BlockFace.DOWN.offsetPos(pos)
                : BlockFace.UP.offsetPos(pos);

        // Check power at both halves and use the maximum
        int powerAtThis = block.getDimension().getPowerAt(block.getPosition());
        var otherPosition = new Position3i(otherPos, dimension);
        int powerAtOther = block.getDimension().getPowerAt(otherPosition);
        int maxPower = Math.max(powerAtThis, powerAtOther);

        boolean shouldBeOpen = maxPower > 0;
        boolean isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            // Update both halves
            dimension.updateBlockProperty(OPEN_BIT, shouldBeOpen, pos);
            dimension.updateBlockProperty(OPEN_BIT, shouldBeOpen, otherPos);

            // Play sound
            dimension.addSound(MathUtils.center(pos), shouldBeOpen ? new DoorSound(block.getBlockState(), true) : new DoorSound(block.getBlockState(), false));
        }
    }

    protected boolean isSameDoor(Block neighbor) {
        return neighbor.getBlockType() == getBlockType();
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;

        Vector3i pos = (Vector3i) interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);

        Vector3ic otherPos = blockState.getPropertyValue(UPPER_BLOCK_BIT)
                ? BlockFace.DOWN.offsetPos(pos)
                : BlockFace.UP.offsetPos(pos);

        var isOpen = !blockState.getPropertyValue(OPEN_BIT);

        dimension.updateBlockProperty(OPEN_BIT, isOpen, pos);
        dimension.updateBlockProperty(OPEN_BIT, isOpen, otherPos);

        // Shouldn't use addLevelSoundEvent here, which has no effect on client for no reason
        dimension.addSound(MathUtils.center(pos), isOpen ? new DoorSound(blockState, true) : new DoorSound(blockState, false));
        return true;
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            block.getDimension().breakBlock(BlockFace.DOWN.offsetPos(block.getPosition()), null, entity);
        }
        super.onBreak(block, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(UPPER_BLOCK_BIT) ? Set.of() : super.getDrops(block, usedItem, entity);
    }
}
