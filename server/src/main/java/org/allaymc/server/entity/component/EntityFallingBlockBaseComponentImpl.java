package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
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
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        Objects.requireNonNull(blockState, "blockState shouldn't be null");

        metadata.set(EntityFlag.FIRE_IMMUNE, true);
        metadata.set(EntityDataTypes.VARIANT, blockState.blockStateHash());
        metadata.set(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void onFall() {
        if (this.willBeDespawnedNextTick()) {
            // The falling block entity already became block
            return;
        }

        super.onFall();
        var dimension = getDimension();

        var blockReplaced = dimension.getBlockState(location);
        var floorLoc = location.floor();
        if (blockReplaced.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            dimension.breakBlock((int) floorLoc.x(), (int) floorLoc.y(), (int) floorLoc.z(), null, null);
        }

        // Set block state immediately when falling on ground to prevent
        // the falling block entity above from getting into the ground.
        dimension.setBlockState(location, blockState);
        dimension.getEntityService().removeEntity(thisEntity);
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
    public float getBaseOffset() {
        return 0.49f;
    }
}
