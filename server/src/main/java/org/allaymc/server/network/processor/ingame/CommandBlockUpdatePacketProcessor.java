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
 * {@code CommandBlockUpdatePacketProcessor} applies command-block UI settings
 * for creative players holding the
 * command-block permission. Nonblock requests and missing command-block
 * entities are ignored. The requested mode selects a block type, preserving
 * facing direction while updating the conditional block property before entity
 * fields are changed.
 *
 * <p>
 * Changing block type can rebuild its block entity, so the handler fetches that
 * entity again after the world update and stops if it is absent. The
 * replacement receives command, output, delay, name, and activation settings
 * before viewer synchronization. An always-active normal block is triggered
 * after those fields are assigned. The process does not roll back the world
 * block change or earlier fields if refetching or a later update fails, and
 * minecart command blocks remain unsupported.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CommandBlockUpdatePacketProcessor extends PacketProcessor<CommandBlockUpdatePacket> {

    /**
     * Applies settings only for a permitted creative player and an existing
     * command-block target. Minecart requests are ignored, and block type
     * follows the requested command mode while retaining the original facing
     * direction.
     *
     * <p>
     * The world block or conditional property changes before the block entity
     * is fetched again, because a type switch can replace the entity. The
     * refreshed entity receives clamped delay and other settings, then a normal
     * always-active block is triggered before broadcasting. A missing refreshed
     * entity stops processing after the block update; later failures also
     * propagate without undoing earlier changes.
     */
    @Override
    public void handleSync(Player player, CommandBlockUpdatePacket packet, long receiveTime) {
        var entity = player.getControlledEntity();

        if (!entity.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean() || entity.getGameMode() != GameMode.CREATIVE) {
            log.warn("Player {} tried to modify command block without permission", player.getOriginName());
            return;
        }

        if (!packet.isBlock()) {
            // TODO: Add minecart entity lookup and command-setting updates
            // before accepting the nonblock form of this packet.
            return;
        }

        var dimension = entity.getDimension();
        var blockPos = packet.getBlockPosition();
        int x = blockPos.getX(), y = blockPos.getY(), z = blockPos.getZ();

        if (!(dimension.getBlockEntity(x, y, z) instanceof BlockEntityCommandBlock)) {
            log.warn("Player {} tried to update non-command-block entity at ({}, {}, {})", player.getOriginName(), x, y, z);
            return;
        }

        var currentBlockState = dimension.getBlockState(x, y, z);
        var currentBlockType = currentBlockState.getBlockType();
        var targetBlockType = switch (packet.getMode()) {
            case REPEATING -> BlockTypes.REPEATING_COMMAND_BLOCK;
            case CHAIN -> BlockTypes.CHAIN_COMMAND_BLOCK;
            default -> BlockTypes.COMMAND_BLOCK;
        };

        // Change the block representation before editing entity fields, because
        // a mode-driven type switch can rebuild the block entity and discard
        // writes made to its old instance.
        if (currentBlockType != targetBlockType) {
            var facingDirection = currentBlockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
            var newBlockState = targetBlockType.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, facingDirection)
                    .setPropertyValue(BlockPropertyTypes.CONDITIONAL_BIT, packet.isConditional());
            dimension.setBlockState(x, y, z, newBlockState);
        } else {
            dimension.updateBlockProperty(BlockPropertyTypes.CONDITIONAL_BIT, packet.isConditional(), x, y, z);
        }

        // Fetch the current entity after the world mutation so subsequent
        // settings target the replacement rather than an instance detached by
        // the type switch.
        var commandBlock = (BlockEntityCommandBlock) dimension.getBlockEntity(x, y, z);
        if (commandBlock == null) {
            log.warn("Command block entity missing after block type switch at ({}, {}, {})", x, y, z);
            return;
        }

        commandBlock.setCommand(packet.getCommand());
        commandBlock.setTrackingOutput(packet.isOutputTracked());
        commandBlock.setConditional(packet.isConditional());
        // Clamp to nonnegative integer ticks before narrowing the wire value.
        commandBlock.setTickDelay((int) Math.max(0L, Math.min(packet.getTickDelay(), Integer.MAX_VALUE)));
        commandBlock.setExecuteOnFirstTick(packet.isExecutingOnFirstTick());

        var customName = packet.getName();
        if (!customName.isEmpty()) {
            commandBlock.setCustomName(customName);
        } else {
            commandBlock.setCustomName(null);
        }

        // The packet expresses redstone dependence while the entity stores
        // automatic activation; invert this flag so both representations
        // describe the same mode.
        boolean isRedstoneMode = packet.isRedstoneMode();
        commandBlock.setAuto(!isRedstoneMode);

        // Normal always-active requests trigger after settings are assigned, so
        // execution observes the submitted command and delay rather than the
        // previous block-entity configuration.
        if (!isRedstoneMode && packet.getMode() == CommandBlockMode.NORMAL) {
            commandBlock.trigger();
        }

        ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) commandBlock).getBaseComponent()).sendBlockEntityToViewers();
    }

    /**
     * Returns the command block update packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_BLOCK_UPDATE;
    }
}
