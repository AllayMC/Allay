package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.network.processor.PacketProcessor;
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
        var baseComponent = (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
        baseComponent.applyClientChange(player, packet.getData());
        // Send new data to other viewers
        baseComponent.sendBlockEntityToViewers();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_ENTITY_DATA;
    }
}
