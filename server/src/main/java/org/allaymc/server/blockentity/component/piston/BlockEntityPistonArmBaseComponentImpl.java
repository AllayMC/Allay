package org.allaymc.server.blockentity.component.piston;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityPistonArmBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.blockentity.interfaces.BlockEntityPistonArm;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of piston arm block entity component.
 *
 * @author daoge_cmd
 */
public class BlockEntityPistonArmBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityPistonArmBaseComponent {

    protected static final String TAG_STATE = "State";
    protected static final String TAG_NEW_STATE = "NewState";
    protected static final String TAG_PROGRESS = "Progress";
    protected static final String TAG_LAST_PROGRESS = "LastProgress";
    protected static final String TAG_STICKY = "Sticky";
    protected static final String TAG_EXTENDING = "Extending";
    protected static final String TAG_ATTACHED_BLOCKS = "AttachedBlocks";
    protected static final String TAG_FACING = "facing";
    protected static final String TAG_POWERED = "powered";

    @ComponentObject
    protected BlockEntityPistonArm thisPistonArm;

    @Getter
    @Setter
    protected BlockFace facing = BlockFace.NORTH;
    @Getter
    @Setter
    protected boolean sticky;
    @Getter
    protected boolean extending;
    @Getter
    protected float progress;
    @Getter
    protected float lastProgress = 1;
    @Getter
    @Setter
    protected byte state;
    @Getter
    @Setter
    protected byte newState = 1;
    @Getter
    protected boolean finished = true;
    @Getter
    @Setter
    protected boolean powered;

    protected List<Vector3ic> attachedBlocks = new ArrayList<>();
    protected Map<Vector3ic, BlockState> attachedBlockStates = new HashMap<>();

    public BlockEntityPistonArmBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putByte(TAG_STATE, state);
        builder.putByte(TAG_NEW_STATE, newState);
        builder.putFloat(TAG_PROGRESS, progress);
        builder.putFloat(TAG_LAST_PROGRESS, lastProgress);
        builder.putBoolean(TAG_STICKY, sticky);
        builder.putBoolean(TAG_EXTENDING, extending);
        builder.putBoolean(TAG_POWERED, powered);
        builder.putInt(TAG_FACING, facing.ordinal());

        // Save attached blocks as int array (x, y, z, x, y, z, ...)
        List<Integer> attachedBlocksList = new ArrayList<>();
        for (var pos : attachedBlocks) {
            attachedBlocksList.add(pos.x());
            attachedBlocksList.add(pos.y());
            attachedBlocksList.add(pos.z());
        }
        builder.putList(TAG_ATTACHED_BLOCKS, NbtType.INT, attachedBlocksList);

        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_STATE, value -> state = value);
        nbt.listenForByte(TAG_NEW_STATE, value -> newState = value);
        nbt.listenForFloat(TAG_PROGRESS, value -> progress = value);
        nbt.listenForFloat(TAG_LAST_PROGRESS, value -> lastProgress = value);
        nbt.listenForBoolean(TAG_STICKY, value -> sticky = value);
        nbt.listenForBoolean(TAG_EXTENDING, value -> extending = value);
        nbt.listenForBoolean(TAG_POWERED, value -> powered = value);
        nbt.listenForInt(TAG_FACING, value -> facing = BlockFace.fromIndex(value));

        nbt.listenForList(TAG_ATTACHED_BLOCKS, NbtType.INT, blocks -> {
            attachedBlocks.clear();
            for (int i = 0; i < blocks.size(); i += 3) {
                attachedBlocks.add(new Vector3i(
                        blocks.get(i),
                        blocks.get(i + 1),
                        blocks.get(i + 2)
                ));
            }
        });
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (finished) {
            return;
        }

        // Update progress - PNX style: both progress and lastProgress are updated together
        if (extending) {
            progress = Math.min(1, progress + MOVE_STEP);
            lastProgress = Math.min(1, lastProgress + MOVE_STEP);
        } else {
            progress = Math.max(0, progress - MOVE_STEP);
            lastProgress = Math.max(0, lastProgress - MOVE_STEP);
        }

        // Move collided entities
        moveCollidedEntities();

        // Check if animation is complete
        if (progress == lastProgress) {
            finishAnimation();
        }
    }

    protected void moveCollidedEntities() {
        var dimension = position.dimension();
        var pushDirection = extending ? facing : facing.opposite();

        // Move entities colliding with attached blocks
        for (var pos : attachedBlocks) {
            var blockEntityPos = pushDirection.offsetPos(pos);
            var blockEntity = dimension.getBlockEntity(blockEntityPos);
            if (blockEntity instanceof BlockEntityMovingBlock movingBlock) {
                moveEntitiesForMovingBlock(movingBlock, pushDirection);
            }
        }

        // Move entities colliding with the piston head itself
        var bb = new AABBd(0, 0, 0, 1, 1, 1);
        var offset = pushDirection.getOffset();
        bb = bb.translate(
                position.x() + (offset.x() * progress),
                position.y() + (offset.y() * progress),
                position.z() + (offset.z() * progress)
        );
        // Add extra height for horizontal movement to catch entities standing on blocks
        if (pushDirection.isHorizontal()) {
            bb = new AABBd(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY + 0.25, bb.maxZ);
        }

        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(bb);
        for (var entity : entities) {
            moveEntity(entity, pushDirection);
        }
    }

    protected void moveEntitiesForMovingBlock(BlockEntityMovingBlock movingBlock, BlockFace moveDirection) {
        var blockState = movingBlock.getMovingBlockState();
        if (blockState == null) {
            return;
        }

        var dimension = position.dimension();
        var movingPos = movingBlock.getPosition();

        // Calculate bounding box for the moving block
        var bb = new AABBd(0, 0, 0, 1, 1, 1);
        var moveOffset = moveDirection.getOffset();
        bb = bb.translate(
                movingPos.x() + (progress * moveOffset.x()) - moveOffset.x(),
                movingPos.y() + (progress * moveOffset.y()) - moveOffset.y(),
                movingPos.z() + (progress * moveOffset.z()) - moveOffset.z()
        );
        // Add extra height for horizontal movement
        if (moveDirection.isHorizontal()) {
            bb = new AABBd(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY + 0.25, bb.maxZ);
        }

        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(bb);
        for (var entity : entities) {
            moveEntity(entity, moveDirection);
        }
    }

    protected void moveEntity(Entity entity, BlockFace moveDirection) {
        // Don't push downward
        if (moveDirection == BlockFace.DOWN) {
            return;
        }

        var diff = Math.abs(progress - lastProgress);
        if (diff == 0) {
            return;
        }

        // Only move entities that compute movement server-side
        // (e.g., players handle their own client-side movement)
        if (entity instanceof EntityPhysicsComponent physicsComponent &&
            !physicsComponent.computeMovementServerSide()) {
            return;
        }

        // Move the entity
        var entityOffset = moveDirection.getOffset();
        double dx = diff * entityOffset.x();
        double dy = diff * entityOffset.y() * (moveDirection == BlockFace.UP ? 2 : 1);
        double dz = diff * entityOffset.z();

        var location = entity.getLocation();
        var newLocation = new org.allaymc.api.math.location.Location3d(
                location.x() + dx,
                location.y() + dy,
                location.z() + dz,
                location.pitch(),
                location.yaw(),
                location.dimension()
        );
        entity.teleport(newLocation);
    }

    protected void finishAnimation() {
        finished = true;
        state = newState = (byte) (extending ? 2 : 0);

        var dimension = position.dimension();
        var pushDirection = extending ? facing : facing.opposite();

        // Finalize moving blocks
        for (var pos : attachedBlocks) {
            var newPos = pushDirection.offsetPos(pos);
            var blockEntity = dimension.getBlockEntity(newPos);
            if (blockEntity instanceof BlockEntityMovingBlock movingBlockEntity) {
                // Get the original state before removing the moving block
                var originalState = movingBlockEntity.getMovingBlockState();
                var movingBlockEntityNBT = movingBlockEntity.getMovingBlockEntityNBT();

                // Setting the block state will automatically remove the old block entity
                // and create a new one if the original block had a block entity
                if (originalState != null) {
                    dimension.setBlockState(newPos, originalState);
                    // Restore block entity if any
                    if (movingBlockEntityNBT != null) {
                        dimension.getBlockEntity(newPos).loadNBT(movingBlockEntityNBT);
                    }
                } else {
                    dimension.setBlockState(newPos, BlockTypes.AIR.getDefaultState());
                }
            }
        }

        // Remove piston arm if retracting
        if (!extending) {
            var armPos = facing.offsetPos(position);
            var armState = dimension.getBlockState(armPos);
            if (armState.getBlockType() == BlockTypes.PISTON_ARM_COLLISION ||
                armState.getBlockType() == BlockTypes.STICKY_PISTON_ARM_COLLISION) {
                dimension.setBlockState(armPos, BlockTypes.AIR.getDefaultState());
            }
        }

        attachedBlocks.clear();
        attachedBlockStates.clear();

        // Send update to viewers
        sendBlockEntityToViewers();

        // PNX: Schedule a self-check on next tick to ensure correct state
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(position, java.time.Duration.ofMillis(50));
    }

    @Override
    public List<Vector3ic> getAttachedBlocks() {
        return new ArrayList<>(attachedBlocks);
    }

    @Override
    public void preExtending(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates) {
        finished = false;
        extending = true;
        // PNX preMove: set both to same value initially
        progress = 0;
        lastProgress = 0;
        state = newState = 1;
        attachedBlocks = new ArrayList<>(blocksToMove);
        attachedBlockStates = new HashMap<>(originalStates);

        // Send initial state to client with progress = lastProgress = 0
        // This must be sent BEFORE MOVING_BLOCK entities are created
        sendBlockEntityToViewers();
    }

    @Override
    public void preRetracting(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates) {
        finished = false;
        extending = false;
        // PNX preMove: set both to same value initially
        progress = 1;
        lastProgress = 1;
        state = newState = 3;
        attachedBlocks = new ArrayList<>(blocksToMove);
        attachedBlockStates = new HashMap<>(originalStates);

        // Send initial state to client with progress = lastProgress = 1
        // This must be sent BEFORE MOVING_BLOCK entities are created
        sendBlockEntityToViewers();
    }

    @Override
    public void startMoving() {
        // PNX move: set lastProgress for animation start
        // This is called AFTER MOVING_BLOCK entities are created
        if (extending) {
            lastProgress = -MOVE_STEP;
        } else {
            lastProgress = 1 + MOVE_STEP;
        }
        // The tick() method will handle the animation from here
    }
}
