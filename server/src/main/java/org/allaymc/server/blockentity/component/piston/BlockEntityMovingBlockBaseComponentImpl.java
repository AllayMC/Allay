package org.allaymc.server.blockentity.component.piston;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityMovingBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockEntityMovingBlockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityMovingBlockBaseComponent {

    protected static final String TAG_MOVING_BLOCK = "movingBlock";
    protected static final String TAG_MOVING_BLOCK_NAME = "name";
    protected static final String TAG_MOVING_BLOCK_STATES = "states";
    protected static final String TAG_MOVING_ENTITY = "movingEntity";
    protected static final String TAG_PISTON_POS_X = "pistonPosX";
    protected static final String TAG_PISTON_POS_Y = "pistonPosY";
    protected static final String TAG_PISTON_POS_Z = "pistonPosZ";
    protected static final String TAG_EXPANDING = "expanding";

    @ComponentObject
    protected BlockEntityMovingBlock thisMovingBlock;

    @Getter
    @Setter
    protected BlockState movingBlockState;
    @Getter
    @Setter
    protected Vector3ic pistonPos = new Vector3i(0, -1, 0);
    protected NbtMap movingBlockEntityNbt;
    @Getter
    @Setter
    protected boolean expanding = true;

    public BlockEntityMovingBlockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        if (movingBlockState != null) {
            var movingBlockBuilder = NbtMap.builder();
            movingBlockBuilder.putString(TAG_MOVING_BLOCK_NAME, movingBlockState.getBlockType().getIdentifier().toString());
            // Extract the states compound from the block state NBT
            var blockStateNbt = movingBlockState.getBlockStateNBT();
            var states = blockStateNbt.getCompound("states");
            if (states != null) {
                movingBlockBuilder.putCompound(TAG_MOVING_BLOCK_STATES, states);
            }
            builder.putCompound(TAG_MOVING_BLOCK, movingBlockBuilder.build());
        }

        if (movingBlockEntityNbt != null) {
            builder.putCompound(TAG_MOVING_ENTITY, movingBlockEntityNbt);
        }

        builder.putInt(TAG_PISTON_POS_X, pistonPos.x());
        builder.putInt(TAG_PISTON_POS_Y, pistonPos.y());
        builder.putInt(TAG_PISTON_POS_Z, pistonPos.z());
        builder.putBoolean(TAG_EXPANDING, expanding);

        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_MOVING_BLOCK, movingBlock -> {
            var blockName = movingBlock.getString(TAG_MOVING_BLOCK_NAME);
            var states = movingBlock.getCompound(TAG_MOVING_BLOCK_STATES);

            // Build the block state NBT for hash computation
            var blockStateNbt = NbtMap.builder()
                    .putString("name", blockName)
                    .putCompound("states", states != null ? states : NbtMap.EMPTY)
                    .build();

            // Compute the hash and look up in the palette
            int blockStateHash = HashUtils.fnv1a_32_nbt(blockStateNbt);
            var blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
            if (blockState != null) {
                movingBlockState = blockState;
            }
        });

        nbt.listenForCompound(TAG_MOVING_ENTITY, entity -> movingBlockEntityNbt = entity);

        var pistonX = nbt.getInt(TAG_PISTON_POS_X, 0);
        var pistonY = nbt.getInt(TAG_PISTON_POS_Y, -1);
        var pistonZ = nbt.getInt(TAG_PISTON_POS_Z, 0);
        pistonPos = new Vector3i(pistonX, pistonY, pistonZ);

        expanding = nbt.getBoolean(TAG_EXPANDING, true);
    }

    @Override
    public NbtMap getMovingBlockEntityNBT() {
        return this.movingBlockEntityNbt;
    }

    @Override
    public void setMovingBlockEntityNBT(NbtMap nbt) {
        this.movingBlockEntityNbt = nbt;
    }
}
