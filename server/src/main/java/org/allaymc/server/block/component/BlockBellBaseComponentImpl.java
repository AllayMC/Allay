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
 * {@code BlockBellBaseComponentImpl} places bells on supported surfaces and
 * translates player, projectile, and redstone triggers into bell block-entity
 * rings. Placement derives a standing, hanging, single-side, or double-side
 * attachment from the clicked surface and available supports. Later neighbor
 * updates recheck those supports before processing redstone power.
 *
 * <p>
 * Ringing derives an animation direction from the source entity's position, with
 * north as the default for entity-free triggers. {@code isValidHitDirection}
 * combines that direction with the stored attachment and orientation before an
 * event is dispatched. Redstone uses {@code TOGGLE_BIT} to detect a rising edge,
 * and the bit advances even when the resulting ring is vetoed.
 *
 * @author daoge_cmd
 */
public class BlockBellBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves bell block entities for accepted
     * rings. Component initialization injects the dependency before the composed
     * behavior handles interaction or neighbor updates.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityBell> blockEntityHolderComponent;

    /**
     * Creates the bell block component for the supplied registered block type.
     */
    public BlockBellBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a bell with an attachment supported by the clicked surface. A
     * {@code null} placement context delegates to ordinary placement. Player
     * placement uses the player's opposite facing for floor and ceiling
     * attachments, or the clicked horizontal face for a side attachment. A second
     * support selects the double-side form. {@code checkSupport} validates the
     * selected form before the new attachment and direction properties reach
     * shared placement handling.
     */
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
                var oppositeSide = placementInfo.getClickedBlock().offsetPos(face);
                if (canSupportBell(oppositeSide, face.opposite())) {
                    attachment = Attachment.MULTIPLE;
                } else {
                    attachment = Attachment.SIDE;
                }
            }
        }

        if (!checkSupport(dimension, placeBlockPos, attachment, bellDirection)) {
            return false;
        }

        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.ATTACHMENT, attachment)
                .setPropertyValue(BlockPropertyTypes.DIRECTION_4, bellDirection.getHorizontalIndex());

        return super.place(dimension, newState, placeBlockPos, placementInfo);
    }

    /**
     * Returns whether the blocks required by the selected attachment support the
     * bell. Standing and hanging forms test one vertical neighbor; the double-side
     * form requires both horizontal supports.
     */
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
     * Returns whether the collision shape supports the requested bell attachment.
     * Ceiling attachment needs a full center bottom face, while floor and side
     * attachments require a full face.
     */
    private boolean canSupportBell(Block block, BlockFace face) {
        var shape = block.getBlockStateData().collisionShape();

        return switch (face) {
            case DOWN -> shape.isCenterFull(BlockFace.DOWN);
            case UP -> shape.isFull(BlockFace.UP);
            default -> shape.isFull(face);
        };
    }

    /**
     * Runs shared handling, breaks unsupported bells, and detects redstone rising
     * edges. {@code TOGGLE_BIT} records the observed power after the ring attempt,
     * including when ringing is rejected or cancelled.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var attachment = block.getPropertyValue(BlockPropertyTypes.ATTACHMENT);
        var direction = BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4));

        if (!checkSupport(block.getDimension(), block.getPosition(), attachment, direction)) {
            block.breakBlock();
            return;
        }

        var toggleBit = block.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);
        var powered = block.isPowered();

        // Remember the powered state after attempting the ring, so a vetoed event
        // does not cause repeated ring attempts while power remains high.
        if (powered && !toggleBit) {
            ring(block, null, BellRingEvent.RingCause.REDSTONE);
            block.getDimension().updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, true, block.getPosition());
        } else if (!powered && toggleBit) {
            block.getDimension().updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, false, block.getPosition());
        }
    }

    /**
     * Attempts a player-triggered ring using the clicked bell and player. A
     * missing interaction returns {@code false}; otherwise direction validation
     * and event cancellation determine the returned ring result.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();

        return ring(block, player, BellRingEvent.RingCause.PLAYER_INTERACTION);
    }

    /**
     * Attempts a projectile-triggered ring using the projectile's current
     * position.
     */
    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        ring(block, projectile, BellRingEvent.RingCause.PROJECTILE);
    }

    /**
     * Validates a ringing direction and dispatches a cancellable bell request. The
     * larger horizontal displacement from the bell center chooses the source side;
     * ties use the north-south axis, and {@code null} sources default to north.
     * Attachment and stored orientation can reject that side before dispatch. An
     * accepted event forwards the direction to the resolved bell entity. The
     * method still returns {@code true} if a holder supplies no bell entity after
     * event acceptance.
     */
    public boolean ring(Block block, Entity entity, BellRingEvent.RingCause cause) {
        int direction = 0;
        if (entity != null) {
            var entityPos = entity.getLocation();
            var blockPos = block.getPosition();
            var dx = entityPos.x() - (blockPos.x() + 0.5);
            var dz = entityPos.z() - (blockPos.z() + 0.5);

            // Choose the dominant horizontal axis so diagonal sources still map to
            // one animation side; equal displacements select the north-south axis.
            if (Math.abs(dx) > Math.abs(dz)) {
                direction = dx > 0 ? 1 : 3; // East or West
            } else {
                direction = dz > 0 ? 2 : 0; // South or North
            }
        }

        var attachment = block.getPropertyValue(BlockPropertyTypes.ATTACHMENT);
        var bellDirection = block.getPropertyValue(BlockPropertyTypes.DIRECTION_4);

        if (!isValidHitDirection(attachment, bellDirection, direction)) {
            return false;
        }

        var event = new BellRingEvent(block, cause, entity, direction);
        event.call();
        if (event.isCancelled()) {
            return false;
        }

        var bellEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (bellEntity != null) {
            bellEntity.ring(direction);
        }

        return true;
    }

    /**
     * Returns whether the animation direction is valid for this attachment.
     * Standing bells require the orientation axis, double-side bells require the
     * perpendicular axis, and side or hanging bells accept either axis.
     */
    private boolean isValidHitDirection(Attachment attachment, int bellDirection, int hitDirection) {
        return switch (attachment) {
            case STANDING -> {
                // Direction parity groups opposite sides onto the same horizontal
                // axis.
                var bellAxis = bellDirection % 2; // 0 or 2 = Z axis, 1 or 3 = X axis
                var hitAxis = hitDirection % 2;
                yield bellAxis == hitAxis;
            }
            case MULTIPLE -> {
                // The double-side mount accepts the axis perpendicular to its
                // orientation.
                var bellAxis = bellDirection % 2;
                var hitAxis = hitDirection % 2;
                yield bellAxis != hitAxis;
            }
            case SIDE, HANGING -> true; // Can be hit from any direction
        };
    }
}
