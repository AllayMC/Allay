package cn.allay.api.block.component.base;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponent;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.function.*;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.client.Client;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.world.World;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends OnNeighborChanged, OnRandomUpdate, Place, OnPlace, OnInteract, OnReplace, OnScheduledUpdate, BlockComponent {
    BlockType<? extends BlockBehavior> getBlockType();

    default void sendBlockUpdateTo(BlockState blockState, int x, int y, int z, int layer, Client client) {
        client.sendPacket(createBlockUpdatePacket(blockState, x, y, z, layer));
    }

    default UpdateBlockPacket createBlockUpdatePacket(BlockState blockState, int x, int y, int z, int layer) {
        var updateBlockPacket = new UpdateBlockPacket();
        updateBlockPacket.setBlockPosition(Vector3i.from(x, y, z));
        updateBlockPacket.setDefinition(blockState.toNetworkBlockDefinitionRuntime());
        updateBlockPacket.setDataLayer(layer);
        updateBlockPacket.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
        return updateBlockPacket;
    }
}
