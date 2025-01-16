package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.Objects;

/**
 * @author IWareQ
 */
@Getter
@Setter
public class EntityFallingBlockBaseComponentImpl extends EntityBaseComponentImpl implements EntityFallingBlockBaseComponent {

    protected BlockState blockState;

    public EntityFallingBlockBaseComponentImpl(EntityInitInfo info) {
        super(info);
        // The initial onGround state for falling block is false
        // And it will be either turned into block or item based
        // on the block which the falling block fell on
        this.onGround = false;
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        Objects.requireNonNull(blockState, "blockState shouldn't be null");

        metadata.set(EntityFlag.FIRE_IMMUNE, true);
        metadata.set(EntityDataTypes.VARIANT, blockState.blockStateHash());
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        tickFalling();
    }

    protected void tickFalling() {
        if (this.willBeDespawnedNextTick()) {
            // The falling block entity already became block
            return;
        }

        var dimension = getDimension();
        var currentBlock = dimension.getBlockState(location);

        if (onGround) {
            if (!getBlockStateStandingOn().blockState().getBlockStateData().shape().isFull(BlockFace.UP)) {
                // Falling on a block which is not full in upper face, for example torch.
                // In this case, the falling block should be turned into item instead of block
                dimension.dropItem(currentBlock.toItemStack(), location);
            } else {
                // Set block state immediately when falling on ground to prevent
                // the falling block entity above from getting into the ground.
                dimension.setBlockState(location, blockState);
            }
            despawn();
        } else {
            if (currentBlock.getBlockType() == BlockTypes.AIR) {
                return;
            }

            var floorLoc = location.floor(new Vector3f());
            if (currentBlock.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
                dimension.breakBlock((int) floorLoc.x(), (int) floorLoc.y(), (int) floorLoc.z(), null, null);
            } else {
                // The falling block get into a non-replaceable block for some reason
                // In this case, just let the falling block become item
                dimension.dropItem(currentBlock.toItemStack(), location);
                despawn();
            }
        }
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt("BlockStateHash", blockState.blockStateHash())
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt("BlockStateHash", blockStateHash ->
                blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash)
        );
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(-0.49f, 0, -0.49f, 0.49f, 0.98f, 0.49f);
    }

    @Override
    public float getGravity() {
        return 0.04f;
    }

    @Override
    public float getNetworkOffset() {
        return 0.49f;
    }
}
