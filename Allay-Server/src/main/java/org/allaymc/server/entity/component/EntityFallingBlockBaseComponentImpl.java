package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.component.fallingblock.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
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
public class EntityFallingBlockBaseComponentImpl extends EntityBaseComponentImpl<EntityFallingBlock> implements EntityFallingBlockBaseComponent {
    public static final int MAX_ALIVE_TICKS = 30 * 20;

    protected BlockState blockState;
    protected int aliveTick;

    public EntityFallingBlockBaseComponentImpl(EntityInitInfo<EntityFallingBlock> info) {
        super(info);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        Objects.requireNonNull(blockState, "blockState must not be null");

        this.metadata.set(EntityFlag.FIRE_IMMUNE, true);
        this.metadata.set(EntityDataTypes.VARIANT, blockState.blockStateHash());
    }

    @Override
    public void tick() {
        super.tick();
        var blockType = blockState.getBlockType();
        if (
                blockType == BlockTypes.SAND_TYPE ||
                blockType == BlockTypes.GRAVEL_TYPE ||
                blockType == BlockTypes.ANVIL_TYPE
        ) {
            aliveTick++;
            if (aliveTick > MAX_ALIVE_TICKS) {
                aliveTick = 0;
                getDimension().getEntityService().removeEntity(thisEntity, () -> {
                    despawnFromAll();
                    getDimension().addLevelEvent(
                            location.x(), location.y(), location.z(),
                            LevelEvent.BLOCK_UPDATE_BREAK, blockState.blockStateHash()
                    );
                    getDimension().dropItem(blockState.toItemStack(), location);
                });
            }
        }

        if (isOnGround()) {
            getDimension().getEntityService().removeEntity(thisEntity, () -> {
                getDimension().setBlockState(
                        (int) location.x(),
                        (int) location.y(),
                        (int) location.z(),
                        blockState
                );
                despawnFromAll();
            });
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
                blockState = BlockStateHashPalette.getRegistry().get(blockStateHash)
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
