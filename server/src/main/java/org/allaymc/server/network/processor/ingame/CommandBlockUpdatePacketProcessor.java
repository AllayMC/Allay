package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.CommandBlockMode;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;

/**
 * Handles command block update packets from the client.
 * These packets are sent when a player modifies command block settings through the UI.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CommandBlockUpdatePacketProcessor extends PacketProcessor<CommandBlockUpdatePacket> {

    @Override
    public void handleSync(Player player, CommandBlockUpdatePacket packet, long receiveTime) {
        var entity = player.getControlledEntity();

        // Only creative players with command block permission can modify command blocks
        if (!entity.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean() || entity.getGameMode() != GameMode.CREATIVE) {
            log.warn("Player {} tried to modify command block without permission", player.getOriginName());
            return;
        }

        if (!packet.isBlock()) {
            // TODO: Minecart command block - not implemented yet
            return;
        }

        var dimension = entity.getDimension();
        var blockPos = packet.getBlockPosition();
        int x = blockPos.getX(), y = blockPos.getY(), z = blockPos.getZ();

        if (!(dimension.getBlockEntity(x, y, z) instanceof BlockEntityCommandBlock)) {
            log.warn("Player {} tried to update non-command-block entity at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        // Determine which block type to switch to based on mode
        var currentBlockState = dimension.getBlockState(x, y, z);
        var currentBlockType = currentBlockState.getBlockType();
        var targetBlockType = switch (packet.getMode()) {
            case REPEATING -> BlockTypes.REPEATING_COMMAND_BLOCK;
            case CHAIN -> BlockTypes.CHAIN_COMMAND_BLOCK;
            default -> BlockTypes.COMMAND_BLOCK;
        };

        // Switch block type first, as this may rebuild the block entity
        if (currentBlockType != targetBlockType) {
            var facingDirection = currentBlockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
            var newBlockState = targetBlockType.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, facingDirection)
                    .setPropertyValue(BlockPropertyTypes.CONDITIONAL_BIT, packet.isConditional());
            dimension.setBlockState(x, y, z, newBlockState);
        } else {
            dimension.updateBlockProperty(BlockPropertyTypes.CONDITIONAL_BIT, packet.isConditional(), x, y, z);
        }

        // Re-fetch the block entity after potential rebuild
        var commandBlock = (BlockEntityCommandBlock) dimension.getBlockEntity(x, y, z);
        if (commandBlock == null) {
            log.warn("Command block entity missing after block type switch at ({}, {}, {})", x, y, z);
            return;
        }

        // Update command block properties
        commandBlock.setCommand(packet.getCommand());
        commandBlock.setTrackingOutput(packet.isOutputTracked());
        commandBlock.setConditional(packet.isConditional());
        commandBlock.setTickDelay((int) Math.max(0L, Math.min(packet.getTickDelay(), Integer.MAX_VALUE)));
        commandBlock.setExecutingOnFirstTick(packet.isExecutingOnFirstTick());

        var customName = packet.getName();
        if (!customName.isEmpty()) {
            commandBlock.setCustomName(customName);
        } else {
            commandBlock.setCustomName(null);
        }

        // Redstone mode: when redstoneMode is false, it means "always active" (auto = true)
        boolean isRedstoneMode = packet.isRedstoneMode();
        commandBlock.setAuto(!isRedstoneMode);

        // If switching to "always active" on a normal command block, trigger it immediately
        if (!isRedstoneMode && packet.getMode() == CommandBlockMode.NORMAL) {
            commandBlock.trigger();
        }

        // Send updated block entity to viewers
        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) commandBlock).getBaseComponent()).sendBlockEntityToViewers();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_BLOCK_UPDATE;
    }
}
