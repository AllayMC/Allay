package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCommandBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.event.block.CommandBlockExecuteEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of command block block entity component.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityCommandBlockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCommandBlockBaseComponent {

    protected static final String TAG_COMMAND = "Command";
    protected static final String TAG_SUCCESS_COUNT = "SuccessCount";
    protected static final String TAG_LAST_OUTPUT = "LastOutput";
    protected static final String TAG_TRACK_OUTPUT = "TrackOutput";
    protected static final String TAG_LAST_EXECUTION = "LastExecution";
    protected static final String TAG_AUTO = "auto";
    protected static final String TAG_CONDITIONAL_MODE = "conditionalMode";
    protected static final String TAG_CONDITION_MET = "conditionMet";
    protected static final String TAG_POWERED = "powered";
    protected static final String TAG_TICK_DELAY = "TickDelay";
    protected static final String TAG_EXECUTE_ON_FIRST_TICK = "ExecuteOnFirstTick";
    protected static final String TAG_LP_COMMAND_MODE = "LPCommandMode";
    protected static final String TAG_LP_CONDIONAL_MODE = "LPCondionalMode";
    protected static final String TAG_LP_REDSTONE_MODE = "LPRedstoneMode";
    protected static final String TAG_VERSION = "Version";

    protected static final int CURRENT_VERSION = 10;
    protected static final int MAX_CHAIN_DEPTH = 65536;

    @ComponentObject
    protected BlockEntityCommandBlock thisCommandBlock;

    @Getter
    @Setter
    protected String command = "";
    @Getter
    @Setter
    protected int successCount;
    @Getter
    @Setter
    protected String lastOutput;
    @Getter
    @Setter
    protected boolean trackingOutput = true;
    @Getter
    @Setter
    protected long lastExecution;
    @Getter
    @Setter
    protected boolean auto;
    @Getter
    @Setter
    protected boolean conditional;
    @Getter
    @Setter
    protected boolean conditionMet;
    @Getter
    @Setter
    protected boolean powered;
    @Getter
    protected int tickDelay;
    @Getter
    @Setter
    protected boolean executingOnFirstTick;

    protected CommandBlockMode lastOutputCommandMode;
    protected boolean lastOutputConditionalMode;
    protected boolean lastOutputRedstoneMode;

    protected int currentTickCount;
    protected boolean wasActiveLastTick;

    public BlockEntityCommandBlockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (getMode() != CommandBlockMode.REPEATING) {
            wasActiveLastTick = false;
            currentTickCount = 0;
            return;
        }

        // Repeating command blocks only tick when auto or powered
        var active = auto || powered;
        if (!active) {
            wasActiveLastTick = false;
            currentTickCount = 0;
            return;
        }

        // Handle the activation edge for executeOnFirstTick.
        if (!wasActiveLastTick) {
            wasActiveLastTick = true;
            currentTickCount = 0;
            if (executingOnFirstTick) {
                execute(0);
            }
            return;
        }

        if (currentTickCount++ < tickDelay) {
            return;
        }

        execute(0);
        currentTickCount = 0;
    }

    @Override
    public boolean execute(int chainDepth) {
        if (chainDepth > MAX_CHAIN_DEPTH) {
            log.warn("Command block chain depth exceeded maximum of {} at position {}", MAX_CHAIN_DEPTH, getPosition());
            return false;
        }

        var dimension = getDimension();
        if (!(boolean) dimension.getWorld().getWorldData().getGameRuleValue(GameRule.COMMAND_BLOCKS_ENABLED)) {
            return false;
        }

        var pos = getPosition();
        var blockState = dimension.getBlockState(pos);
        var facing = BlockFace.fromIndex(blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
        if (facing == null) return false;

        var serverTick = Server.getInstance().getTick();
        if (lastExecution == serverTick) {
            successCount = 0;
            return true;
        }

        updateConditionMet();
        if (conditionMet && (auto || powered)) {
            if (command != null && !command.isEmpty()) {
                lastOutput = null;
                var block = new Block(blockState, new Position3i(pos, dimension));

                var event = new CommandBlockExecuteEvent(block, thisCommandBlock, command);
                if (!event.call()) {
                    return false;
                }

                var result = Registries.COMMANDS.execute(thisCommandBlock, event.getCommand());
                successCount = result.isSuccess() ? result.status() : 0;
            }

            propagateChain(facing, chainDepth);
        } else {
            // Condition not met, still propagate chain but set success to 0
            successCount = 0;
            propagateChain(facing, chainDepth);
        }

        lastExecution = serverTick;
        lastOutputCommandMode = getMode();
        lastOutputConditionalMode = conditional;
        lastOutputRedstoneMode = !auto;

        return true;
    }

    @Override
    public void setTickDelay(int tickDelay) {
        this.tickDelay = Math.max(0, tickDelay);
    }

    protected void propagateChain(BlockFace facing, int chainDepth) {
        var pos = getPosition();
        var dimension = getDimension();
        var nextPos = new Position3i(facing.offsetPos(pos.x(), pos.y(), pos.z()), dimension);
        var nextBlockState = dimension.getBlockState(nextPos);
        if (nextBlockState.getBlockType() == BlockTypes.CHAIN_COMMAND_BLOCK) {
            var nextBlockEntity = dimension.getBlockEntity(nextPos);
            if (nextBlockEntity instanceof BlockEntityCommandBlock nextCB) {
                nextCB.execute(chainDepth + 1);
            }
        }
    }

    protected void updateConditionMet() {
        if (!conditional) {
            conditionMet = true;
            return;
        }

        var pos = getPosition();
        var dimension = getDimension();
        var blockState = dimension.getBlockState(pos);
        var facing = BlockFace.fromIndex(blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
        if (facing == null) {
            conditionMet = false;
            return;
        }

        var behindPos = new Position3i(facing.opposite().offsetPos(pos.x(), pos.y(), pos.z()), dimension);
        var behindBlockEntity = dimension.getBlockEntity(behindPos);
        if (behindBlockEntity instanceof BlockEntityCommandBlock behindCB) {
            conditionMet = behindCB.getSuccessCount() > 0;
        } else {
            conditionMet = false;
        }
    }

    @Override
    public CommandBlockMode getMode() {
        var blockType = getBlockState().getBlockType();
        if (blockType == BlockTypes.REPEATING_COMMAND_BLOCK) {
            return CommandBlockMode.REPEATING;
        } else if (blockType == BlockTypes.CHAIN_COMMAND_BLOCK) {
            return CommandBlockMode.CHAIN;
        }
        return CommandBlockMode.NORMAL;
    }

    // CommandSender implementation

    @Override
    public String getCommandSenderName() {
        return hasCustomName() ? getCustomName() : "!";
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        var pos = getPosition();
        return new Location3d(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, 0, 0, getDimension());
    }

    @Override
    public void sendMessage(String message) {
        if (trackingOutput) {
            lastOutput = message;
        }
        broadcastOutput(message);
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        var translated = I18n.get().tr(translatable, args);
        if (trackingOutput) {
            lastOutput = translated;
        }
        broadcastOutput(translated);
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (trackingOutput && outputs.length > 0) {
            lastOutput = Arrays.stream(outputs)
                    .map(output -> I18n.get().tr(output.str(), output.args()))
                    .collect(Collectors.joining("\n"));
        }
        if (getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT)) {
            Server.getInstance().getPlayerManager().forEachPlayer(player -> {
                if (player.getControlledEntity().hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
                    player.getControlledEntity().sendCommandOutputs(sender, status, permissions, outputs);
                }
            });
        }
    }

    protected void broadcastOutput(String message) {
        if (!getDimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT)) {
            return;
        }
        var formatted = "§7§o[" + getCommandSenderName() + ": " + message + "]";
        var playerManager = Server.getInstance().getPlayerManager();
        playerManager.forEachPlayer(player -> {
            if (player.getControlledEntity().hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
                player.getControlledEntity().sendMessage(formatted);
            }
        });
    }

    // Permissible implementation - command blocks always have all permissions

    @Override
    public Tristate hasPermission(String permission) {
        return Tristate.TRUE;
    }

    @Override
    public PermissionCalculator getPermissionCalculator() {
        return perm -> Tristate.TRUE;
    }

    @Override
    public void setPermissionCalculator(PermissionCalculator calculator) {
        // Command blocks always have all permissions
    }

    // NBT save/load

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putString(TAG_COMMAND, command != null ? command : "");
        builder.putInt(TAG_SUCCESS_COUNT, successCount);
        builder.putBoolean(TAG_TRACK_OUTPUT, trackingOutput);
        builder.putLong(TAG_LAST_EXECUTION, lastExecution);
        builder.putBoolean(TAG_AUTO, auto);
        builder.putBoolean(TAG_CONDITIONAL_MODE, conditional);
        builder.putBoolean(TAG_CONDITION_MET, conditionMet);
        builder.putBoolean(TAG_POWERED, powered);
        builder.putInt(TAG_TICK_DELAY, tickDelay);
        builder.putBoolean(TAG_EXECUTE_ON_FIRST_TICK, executingOnFirstTick);
        builder.putInt(TAG_LP_COMMAND_MODE, lastOutputCommandMode != null ? lastOutputCommandMode.ordinal() : 0);
        builder.putBoolean(TAG_LP_CONDIONAL_MODE, lastOutputConditionalMode);
        builder.putBoolean(TAG_LP_REDSTONE_MODE, lastOutputRedstoneMode);
        builder.putInt(TAG_VERSION, CURRENT_VERSION);
        if (lastOutput != null) {
            builder.putString(TAG_LAST_OUTPUT, lastOutput);
        }
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForString(TAG_COMMAND, v -> command = v);
        nbt.listenForInt(TAG_SUCCESS_COUNT, v -> successCount = v);
        nbt.listenForBoolean(TAG_TRACK_OUTPUT, v -> trackingOutput = v);
        nbt.listenForLong(TAG_LAST_EXECUTION, v -> lastExecution = v);
        nbt.listenForBoolean(TAG_AUTO, v -> auto = v);
        nbt.listenForBoolean(TAG_CONDITIONAL_MODE, v -> conditional = v);
        nbt.listenForBoolean(TAG_CONDITION_MET, v -> conditionMet = v);
        nbt.listenForBoolean(TAG_POWERED, v -> powered = v);
        nbt.listenForInt(TAG_TICK_DELAY, this::setTickDelay);
        nbt.listenForBoolean(TAG_EXECUTE_ON_FIRST_TICK, v -> executingOnFirstTick = v);
        nbt.listenForInt(TAG_LP_COMMAND_MODE, v -> lastOutputCommandMode = CommandBlockMode.fromId(v));
        nbt.listenForBoolean(TAG_LP_CONDIONAL_MODE, v -> lastOutputConditionalMode = v);
        nbt.listenForBoolean(TAG_LP_REDSTONE_MODE, v -> lastOutputRedstoneMode = v);
        nbt.listenForString(TAG_LAST_OUTPUT, v -> lastOutput = v);
    }
}
