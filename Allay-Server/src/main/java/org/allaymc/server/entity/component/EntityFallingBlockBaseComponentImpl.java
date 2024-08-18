package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.data.VanillaBlockTags;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.Objects;

/**
 * Allay Project 27/06/2024
 *
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
        Objects.requireNonNull(blockState, "blockState must not be null");

        metadata.set(EntityFlag.FIRE_IMMUNE, true);
        metadata.set(EntityDataTypes.VARIANT, blockState.blockStateHash());
        metadata.set(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (!isOnGround()) return;

        var floorPos = location.floor(new Vector3f());
        var block = getDimension().getBlockState(floorPos);
        if (!block.getBlockType().hasBlockTag(VanillaBlockTags.REPLACEABLE)) {
            getDimension().getEntityService().removeEntity(thisEntity, () -> {
                getDimension().addLevelEvent(
                        location.x(), location.y(), location.z(),
                        LevelEvent.BLOCK_UPDATE_BREAK, blockState.blockStateHash()
                );
                getDimension().dropItem(blockState.toItemStack(), location);
            });
            return;
        }

        getDimension().getEntityService().removeEntity(thisEntity, () -> {
            getDimension().setBlockState(
                    (int) floorPos.x(),
                    (int) floorPos.y(),
                    (int) floorPos.z(),
                    blockState
            );
        });
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
