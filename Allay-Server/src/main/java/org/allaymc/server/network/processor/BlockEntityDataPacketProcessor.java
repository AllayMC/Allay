package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;

/**
 * @author daoge_cmd
 */
public class BlockEntityDataPacketProcessor extends PacketProcessor<BlockEntityDataPacket> {

    @Override
    public void handleSync(EntityPlayer player, BlockEntityDataPacket packet, long receiveTime) {
        var pos = packet.getBlockPosition();
        var blockEntity = player.getDimension().getBlockEntity(pos.getX(), pos.getY(), pos.getZ());
        blockEntity.applyClientChange(player, packet.getData());
        // Send new data to other viewers
        blockEntity.sendBlockEntityDataPacketToViewers();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_ENTITY_DATA;
    }
}
