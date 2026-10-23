package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;

/**
 * {@code BlockEntityDataPacketProcessor} applies client block-entity data through
 * the existing block entity's base component, then sends its resulting state to
 * viewers. Lookup uses the
 * controlled entity's current dimension and the packet position. The handler
 * expects an Allay block entity at that location and performs no missing-entity
 * recovery.
 *
 * @author daoge_cmd
 */
public class BlockEntityDataPacketProcessor extends PacketProcessor<BlockEntityDataPacket> {

    /**
     * Applies the packet NBT and then broadcasts the block entity's resulting
     * state. The target must exist and expose Allay's base component;
     * validation or rejection inside {@code applyPlayerChange} remains delegated to
     * that component.
     */
    @Override
    public void handleSync(Player player, BlockEntityDataPacket packet, long receiveTime) {
        var pos = packet.getBlockPosition();
        var blockEntity = player.getControlledEntity().getDimension().getBlockEntity(pos.getX(), pos.getY(), pos.getZ());
        var baseComponent = (BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent();
        baseComponent.applyPlayerChange(player.getControlledEntity(), packet.getData());
        // Broadcast after applying the change so viewers receive the
        // component's resulting state, including any component-specific
        // validation or changes made while loading the submitted data.
        baseComponent.sendBlockEntityToViewers();
    }

    /**
     * Returns the block entity data packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_ENTITY_DATA;
    }
}
