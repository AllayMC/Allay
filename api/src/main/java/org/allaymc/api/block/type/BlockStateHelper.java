package org.allaymc.api.block.type;

import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.StructureException;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;

public class BlockStateHelper {
    public static BlockState fromNbt(NbtMap value) {
        if(value.getInt("version") > ProtocolInfo.BLOCK_STATE_VERSION_NUM){
            throw new StructureException("block version should equal to or less than " + ProtocolInfo.BLOCK_STATE_VERSION_NUM);
        }
        var blockStates = value;
        if(value.getInt("version") < ProtocolInfo.BLOCK_STATE_VERSION_NUM){
            blockStates = BlockStateUpdaters.updateBlockState(value, BlockStateUpdaters.LATEST_VERSION);
        }
        return Registries.BLOCK_STATE_PALETTE.get(HashUtils.fnv1a_32_nbt(
                NbtMap.builder().putString("name", blockStates.getString("name"))
                        .putCompound("states", blockStates.getCompound("states")).build()
        ));
    }
}
