package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
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
    public void handleSync(Player player, BlockEntityDataPacket packet, long receiveTime) {
        var pos = packet.getBlockPosition();
        var blockEntity = player.getControlledEntity().getDimension().getBlockEntity(pos.getX(), pos.getY(), pos.getZ());
        var baseComponent = (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
        baseComponent.applyPlayerChange(player.getControlledEntity(), packet.getData());
        // Send new data to other viewers
        baseComponent.sendBlockEntityToViewers();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_ENTITY_DATA;
    }
}
