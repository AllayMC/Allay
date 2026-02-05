package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.Attachment;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBell;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.block.BellRingEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Implementation of bell block behavior.
 *
 * @author daoge_cmd
 */
public class BlockBellBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityBell> blockEntityHolderComponent;

    public BlockBellBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, placementInfo);
        }

        var face = placementInfo.blockFace();
        var playerDirection = placementInfo.player().getHorizontalFace();

        Attachment attachment;
        BlockFace bellDirection;

        switch (face) {
            case UP -> {
                attachment = Attachment.STANDING;
                bellDirection = playerDirection.opposite();
            }
            case DOWN -> {
                attachment = Attachment.HANGING;
                bellDirection = playerDirection.opposite();
            }
            default -> {
                bellDirection = face;
                // Check if opposite side also has support
                var oppositeSide = placementInfo.getClickedBlock().offsetPos(face);
                if (canSupportBell(oppositeSide, face.opposite())) {
                    attachment = Attachment.MULTIPLE;
                } else {
                    attachment = Attachment.SIDE;
                }
            }
        }

        // Verify support
        if (!checkSupport(dimension, placeBlockPos, attachment, bellDirection)) {
            return false;
        }

        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.ATTACHMENT, attachment)
                .setPropertyValue(BlockPropertyTypes.DIRECTION_4, bellDirection.getHorizontalIndex());

        return super.place(dimension, newState, placeBlockPos, placementInfo);
    }

    private boolean checkSupport(Dimension dimension, Vector3ic pos, Attachment attachment, BlockFace direction) {
        return switch (attachment) {
            case STANDING -> canSupportBell(new Block(dimension, new Vector3i(pos.x(), pos.y() - 1, pos.z())), BlockFace.UP);
            case HANGING -> canSupportBell(new Block(dimension, new Vector3i(pos.x(), pos.y() + 1, pos.z())), BlockFace.DOWN);
            case SIDE -> {
                var offset = direction.opposite().getOffset();
                yield canSupportBell(new Block(dimension, new Vector3i(
                        pos.x() + offset.x(),
                        pos.y() + offset.y(),
                        pos.z() + offset.z()
                )), direction);
            }
            case MULTIPLE -> {
                var offset1 = direction.getOffset();
                var side1 = new Block(dimension, new Vector3i(
                        pos.x() + offset1.x(),
                        pos.y() + offset1.y(),
                        pos.z() + offset1.z()
                ));
                var offset2 = direction.opposite().getOffset();
                var side2 = new Block(dimension, new Vector3i(
                        pos.x() + offset2.x(),
                        pos.y() + offset2.y(),
                        pos.z() + offset2.z()
                ));
                yield canSupportBell(side1, direction.opposite()) && canSupportBell(side2, direction);
            }
        };
    }

    /**
     * Check if a block can support a bell on the specified face.
     * <p>
     * According to MC wiki: "Bells can only be placed below a block with a full
     * center bottom face or on a block with a full top surface and side faces."
     *
     * @param block the potential support block
     * @param face the face of the support block that the bell attaches to
     * @return true if the block can support a bell on that face
     */
    private boolean canSupportBell(Block block, BlockFace face) {
        var shape = block.getBlockStateData().collisionShape();

        return switch (face) {
            // Bell hangs from block above - needs full center at bottom
            case DOWN -> shape.isCenterFull(BlockFace.DOWN);
            // Bell stands on block below - needs full top surface
            case UP -> shape.isFull(BlockFace.UP);
            // Bell attaches to side - needs full side face
            default -> shape.isFull(face);
        };
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var attachment = block.getPropertyValue(BlockPropertyTypes.ATTACHMENT);
        var direction = BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4));

        // Check if bell lost support
        if (!checkSupport(block.getDimension(), block.getPosition(), attachment, direction)) {
            block.breakBlock();
            return;
        }

        // Check redstone power
        var toggleBit = block.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);
        var powered = block.isPowered();

        if (powered && !toggleBit) {
            // Rising edge - ring the bell
            ring(block, null, BellRingEvent.RingCause.REDSTONE);
            block.getDimension().updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, true, block.getPosition());
        } else if (!powered && toggleBit) {
            block.getDimension().updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, false, block.getPosition());
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();

        return ring(block, player, BellRingEvent.RingCause.PLAYER_INTERACTION);
    }

    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        ring(block, projectile, BellRingEvent.RingCause.PROJECTILE);
    }

    /**
     * Ring the bell.
     *
     * @param block  the bell block
     * @param entity the entity that caused the ring, or null
     * @param cause  the cause of the ring
     * @return true if the bell was successfully rung
     */
    public boolean ring(Block block, Entity entity, BellRingEvent.RingCause cause) {
        // Calculate direction based on entity position or default
        int direction = 0;
        if (entity != null) {
            var entityPos = entity.getLocation();
            var blockPos = block.getPosition();
            var dx = entityPos.x() - (blockPos.x() + 0.5);
            var dz = entityPos.z() - (blockPos.z() + 0.5);

            if (Math.abs(dx) > Math.abs(dz)) {
                direction = dx > 0 ? 1 : 3; // East or West
            } else {
                direction = dz > 0 ? 2 : 0; // South or North
            }
        }

        // Validate direction based on attachment
        var attachment = block.getPropertyValue(BlockPropertyTypes.ATTACHMENT);
        var bellDirection = block.getPropertyValue(BlockPropertyTypes.DIRECTION_4);

        if (!isValidHitDirection(attachment, bellDirection, direction)) {
            return false;
        }

        // Call event
        var event = new BellRingEvent(block, cause, entity, direction);
        event.call();
        if (event.isCancelled()) {
            return false;
        }

        // Get block entity and ring
        var bellEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (bellEntity != null) {
            bellEntity.ring(direction);
        }

        return true;
    }

    private boolean isValidHitDirection(Attachment attachment, int bellDirection, int hitDirection) {
        return switch (attachment) {
            case STANDING -> {
                // Standing bells can only be hit from the axis they face
                var bellAxis = bellDirection % 2; // 0 or 2 = Z axis, 1 or 3 = X axis
                var hitAxis = hitDirection % 2;
                yield bellAxis == hitAxis;
            }
            case MULTIPLE -> {
                // Multiple attachment can be hit from perpendicular directions
                var bellAxis = bellDirection % 2;
                var hitAxis = hitDirection % 2;
                yield bellAxis != hitAxis;
            }
            case SIDE, HANGING -> true; // Can be hit from any direction
        };
    }
}
