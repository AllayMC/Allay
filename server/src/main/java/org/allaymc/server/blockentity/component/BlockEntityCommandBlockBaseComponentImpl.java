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
 * {@code BlockEntityCommandBlockBaseComponentImpl} stores command-block
 * settings, evaluates execution eligibility, and acts as an unrestricted
 * command sender. Repeating mode tracks activation edges separately from its
 * delay counter; ordinary and chain modes are triggered through external
 * behavior. Mode comes from the block at the stored position rather than a
 * persisted mode setting.
 *
 * <p>
 * {@code execute} evaluates the block behind a conditional block, dispatches a
 * cancellable command event when active, and recursively follows facing chain
 * blocks. The result flag describes whether this processing path continued,
 * while {@code successCount} records command success separately. Local command
 * cancellation returns before this block's execution snapshot, but upstream
 * propagation ignores a downstream cancellation result. A thrown propagation
 * failure can instead prevent the upstream tick and mode snapshot from being
 * assigned.
 *
 * <p>
 * Output tracking controls retained text, while the owning world's output game
 * rule controls broadcasts to permitted players across the server. NBT stores
 * settings, result fields, and the last snapshot, but it does not preserve the
 * repeating activation counter or grant external control over this sender's
 * permissions.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityCommandBlockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCommandBlockBaseComponent {

    /**
     * {@code TAG_COMMAND} names persisted command text. The command-block tag
     * constants identify settings, result metadata, and compatibility entries
     * during NBT exchange.
     */
    protected static final String TAG_COMMAND = "Command";
    /**
     * Persisted command result count.
     */
    protected static final String TAG_SUCCESS_COUNT = "SuccessCount";
    /**
     * Optional retained output text.
     */
    protected static final String TAG_LAST_OUTPUT = "LastOutput";
    /**
     * Persisted output tracking flag.
     */
    protected static final String TAG_TRACK_OUTPUT = "TrackOutput";
    /**
     * Persisted execution server tick.
     */
    protected static final String TAG_LAST_EXECUTION = "LastExecution";
    /**
     * Persisted always-active execution flag.
     */
    protected static final String TAG_AUTO = "auto";
    /**
     * Persisted conditional execution flag.
     */
    protected static final String TAG_CONDITIONAL_MODE = "conditionalMode";
    /**
     * Persisted evaluated condition result.
     */
    protected static final String TAG_CONDITION_MET = "conditionMet";
    /**
     * Persisted remembered redstone input.
     */
    protected static final String TAG_POWERED = "powered";
    /**
     * Persisted repeating delay ticks.
     */
    protected static final String TAG_TICK_DELAY = "TickDelay";
    /**
     * Persisted activation-edge execution flag.
     */
    protected static final String TAG_EXECUTE_ON_FIRST_TICK = "ExecuteOnFirstTick";
    /**
     * Persisted last execution mode.
     */
    protected static final String TAG_LP_COMMAND_MODE = "LPCommandMode";
    /**
     * Persisted last conditional setting.
     */
    protected static final String TAG_LP_CONDIONAL_MODE = "LPCondionalMode";
    /**
     * Persisted last redstone-required setting.
     */
    protected static final String TAG_LP_REDSTONE_MODE = "LPRedstoneMode";
    /**
     * Persisted command-block format version.
     */
    protected static final String TAG_VERSION = "Version";

    /**
     * {@code CURRENT_VERSION} supplies the command-block format version written
     * by {@code saveNBT}; loading does not branch on this stored version entry.
     */
    protected static final int CURRENT_VERSION = 10;
    /**
     * {@code MAX_CHAIN_DEPTH} bounds the supplied recursive chain-depth
     * argument. {@code execute} rejects a depth strictly greater than this
     * value before command processing.
     */
    protected static final int MAX_CHAIN_DEPTH = 65536;

    /**
     * Injected owning command-block entity.
     */
    @ComponentObject
    protected BlockEntityCommandBlock thisCommandBlock;

    /**
     * {@code command} holds text for later dispatch. The generated setter
     * retains {@code null}, and execution treats it like an empty string while
     * NBT saving normalizes it.
     */
    @Getter
    @Setter
    protected String command = "";
    /**
     * Retained conditional-success result count.
     */
    @Getter
    @Setter
    protected int successCount;
    /**
     * {@code lastOutput} holds optional retained feedback. Tracking methods
     * replace it only when tracking is enabled, while nonempty command dispatch
     * clears it first.
     */
    @Getter
    @Setter
    protected String lastOutput;
    /**
     * Current output retention setting.
     */
    @Getter
    @Setter
    protected boolean trackingOutput = true;
    /**
     * {@code lastExecution} stores the server tick of completed processing.
     * {@code execute} resets success and returns early when the current tick
     * equals this value.
     */
    @Getter
    @Setter
    protected long lastExecution;
    /**
     * Stored always-active execution flag.
     */
    @Getter
    @Setter
    protected boolean auto;
    /**
     * Stored conditional execution flag.
     */
    @Getter
    @Setter
    protected boolean conditional;
    /**
     * Latest evaluated neighbor condition.
     */
    @Getter
    @Setter
    protected boolean conditionMet;
    /**
     * Remembered active redstone input.
     */
    @Getter
    @Setter
    protected boolean powered;
    /**
     * Nonnegative repeating delay ticks.
     */
    @Getter
    protected int tickDelay;
    /**
     * Activation-edge execution selection flag.
     */
    @Getter
    @Setter
    protected boolean executeOnFirstTick;

    // lastOutputCommandMode, lastOutputConditionalMode, and
    // lastOutputRedstoneMode capture mode settings only after chain propagation
    // completes; NBT saving writes that retained snapshot. Loading can replace
    // each snapshot field independently.
    /**
     * Mode at completed processing.
     */
    protected CommandBlockMode lastOutputCommandMode;
    /**
     * Conditional setting at completion.
     */
    protected boolean lastOutputConditionalMode;
    /**
     * Redstone-required setting at completion.
     */
    protected boolean lastOutputRedstoneMode;

    // currentTickCount advances only after wasActiveLastTick records
    // activation. Losing repeating mode or activation clears both, so a later
    // activation starts another interval.
    /**
     * Elapsed active delay counter.
     */
    protected int currentTickCount;
    /**
     * Previous repeating activation observation.
     */
    protected boolean wasActiveLastTick;

    /**
     * Creates common command-block state with empty command text and output
     * tracking enabled.
     */
    public BlockEntityCommandBlockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Advances repeating-mode activation and delay state before triggering
     * execution. Nonrepeating or inactive blocks reset the counter and
     * prior-active flag. The first active tick records the edge and triggers
     * only when {@code executeOnFirstTick} is set, then returns. Later ticks
     * compare the old counter value before incrementing it, so a delay of
     * {@code n} produces a trigger after {@code n + 1} subsequent active ticks.
     * Changing delay does not reset the in-progress counter, and this hook
     * ignores the trigger's boolean result.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (getMode() != CommandBlockMode.REPEATING) {
            wasActiveLastTick = false;
            currentTickCount = 0;
            return;
        }

        var active = auto || powered;
        if (!active) {
            wasActiveLastTick = false;
            currentTickCount = 0;
            return;
        }

        // Record activation before triggering so the next tick enters delay
        // counting instead of repeatedly treating the same active interval as a
        // new edge.
        if (!wasActiveLastTick) {
            wasActiveLastTick = true;
            currentTickCount = 0;
            if (executeOnFirstTick) {
                trigger();
            }
            return;
        }

        // Compare before incrementing, making a delay of n skip n later active
        // ticks and trigger on the next; activation itself returned above.
        if (currentTickCount++ < tickDelay) {
            return;
        }

        trigger();
        currentTickCount = 0;
    }

    /**
     * Processes command execution and facing-chain propagation at the supplied
     * depth. Excessive depth, a disabled command-block game rule, invalid
     * facing, or a cancelled command event returns {@code false}. An equal
     * {@code lastExecution} tick instead clears success and returns
     * {@code true} without dispatch or propagation. Conditional state is
     * refreshed before activation is tested.
     *
     * <p>
     * A permitted nonempty command clears retained output before its
     * cancellable event. Cancellation leaves that clear in place but does not
     * update the execution stamp. Empty command text leaves the previous
     * success count intact. Inactive or unmet conditions clear success but
     * still propagate the facing chain, and downstream return flags are
     * ignored. Completed processing records the tick and mode snapshot only
     * after propagation returns; failures do not roll back earlier command,
     * output, or chain side effects.
     */
    @Override
    public boolean execute(int chainDepth) {
        // Bound recursive propagation depth even when facing links form a
        // cycle.
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
        // A completed execution stamped in this server tick suppresses another
        // attempt, while clearing its success count so successors do not reuse
        // that result.
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
            // Keep traversing chain blocks after a failed condition or inactive
            // source, but clear this result so conditional successors observe
            // failure rather than stale success.
            successCount = 0;
            propagateChain(facing, chainDepth);
        }

        lastExecution = serverTick;
        lastOutputCommandMode = getMode();
        lastOutputConditionalMode = conditional;
        lastOutputRedstoneMode = !auto;

        return true;
    }

    /**
     * Stores a nonnegative bound for the repeating-execution delay counter.
     * Negative inputs select zero; the current counter and activation flag
     * remain unchanged, so an active interval immediately uses the new bound.
     */
    @Override
    public void setTickDelay(int tickDelay) {
        this.tickDelay = Math.max(0, tickDelay);
    }

    /**
     * Attempts execution on a facing neighbor only when its block type is chain
     * mode. A compatible command-block entity receives {@code chainDepth + 1};
     * its boolean result is ignored, and incompatible or missing entities end
     * this branch.
     */
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

    /**
     * Updates the retained condition from the block immediately behind this
     * facing. Unconditional mode sets the result to {@code true} without a
     * neighbor lookup. Conditional mode requires valid facing and a
     * command-block entity behind it whose retained success count is positive;
     * other cases select {@code false}. The lookup does not execute that
     * neighbor or establish that its success was recorded in the current tick,
     * so this method evaluates the state currently available.
     */
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

    /**
     * Derives execution mode from the block type at the current position.
     * Repeating and chain types select their corresponding modes; every other
     * block type selects {@code CommandBlockMode.NORMAL}, including an
     * unexpected replacement.
     */
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

    /**
     * Returns the custom name when present, otherwise the default sender label.
     * The fallback is {@code "!"}; an empty custom name remains present and is
     * returned rather than selecting that fallback.
     */
    @Override
    public String getCommandSenderName() {
        return hasCustomName() ? getCustomName() : "!";
    }

    /**
     * Returns a new zero-rotation command origin centered within this block.
     */
    @Override
    public Location3dc getCommandExecuteLocation() {
        var pos = getPosition();
        // Center the command origin within the block on all axes.
        return new Location3d(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, 0, 0, getDimension());
    }

    /**
     * Retains a message when tracking is enabled and delegates player
     * broadcasting. Disabled tracking leaves earlier output intact, while the
     * world's output game rule independently determines whether the message is
     * broadcast.
     */
    @Override
    public void sendMessage(String message) {
        if (trackingOutput) {
            lastOutput = message;
        }
        broadcastOutput(message);
    }

    /**
     * Translates feedback with the server's translator, optionally retains it,
     * and broadcasts. Output tracking controls only the retained text; the
     * owning world's output game rule independently controls the
     * player-delivery path.
     */
    @Override
    public void sendTranslatable(String translatable, Object... args) {
        var translated = I18n.get().tr(translatable, args);
        if (trackingOutput) {
            lastOutput = translated;
        }
        broadcastOutput(translated);
    }

    /**
     * Retains translated command outputs when tracking and nonempty output
     * allow it. The retained text joins translated entries with newlines, while
     * an empty array leaves previous text unchanged. Independently, the owning
     * world's {@code GameRule.COMMAND_BLOCK_OUTPUT} enables delivery of the
     * original output containers to players whose controlled entity has
     * command-block permission. Recipients come from the server-wide player
     * manager rather than only this dimension, and downstream senders retain
     * responsibility for their normal output handling.
     */
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

    /**
     * Broadcasts formatted command feedback when the owning world's output rule
     * permits it. Recipients come from the server-wide player manager and
     * require command-block use permission; output tracking does not gate this
     * delivery path.
     */
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

    /**
     * Returns {@code Tristate.TRUE} for every requested command permission.
     * This sender does not consult a configurable permission calculator or the
     * permission string, so command blocks retain their unrestricted permission
     * behavior.
     */
    @Override
    public Tristate hasPermission(String permission) {
        return Tristate.TRUE;
    }

    /**
     * Returns a calculator that grants every permission through
     * {@code Tristate.TRUE}.
     */
    @Override
    public PermissionCalculator getPermissionCalculator() {
        return perm -> Tristate.TRUE;
    }

    /**
     * Ignores calculator replacement so this sender continues granting every
     * permission. The supplied reference is not retained or invoked, and
     * subsequent calculator queries still return an unrestricted granting
     * function.
     */
    @Override
    public void setPermissionCalculator(PermissionCalculator calculator) {
    }

    /**
     * Builds common NBT with command settings, retained results, and execution
     * metadata. {@code null} command text becomes {@code ""}, a missing
     * snapshot mode becomes ordinal zero, and absent output text omits its tag.
     * Repeating activation counters are not serialized.
     */
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
        builder.putBoolean(TAG_EXECUTE_ON_FIRST_TICK, executeOnFirstTick);
        builder.putInt(TAG_LP_COMMAND_MODE, lastOutputCommandMode != null ? lastOutputCommandMode.ordinal() : 0);
        builder.putBoolean(TAG_LP_CONDIONAL_MODE, lastOutputConditionalMode);
        builder.putBoolean(TAG_LP_REDSTONE_MODE, lastOutputRedstoneMode);
        builder.putInt(TAG_VERSION, CURRENT_VERSION);
        if (lastOutput != null) {
            builder.putString(TAG_LAST_OUTPUT, lastOutput);
        }
        return builder.build();
    }

    /**
     * Loads supplied command settings and result metadata after common NBT
     * processing. Missing tags retain current values, negative delays clamp to
     * zero, and an unknown snapshot mode ID becomes {@code null}; activation
     * counters remain unchanged.
     */
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
        nbt.listenForBoolean(TAG_EXECUTE_ON_FIRST_TICK, v -> executeOnFirstTick = v);
        nbt.listenForInt(TAG_LP_COMMAND_MODE, v -> lastOutputCommandMode = CommandBlockMode.fromId(v));
        nbt.listenForBoolean(TAG_LP_CONDIONAL_MODE, v -> lastOutputConditionalMode = v);
        nbt.listenForBoolean(TAG_LP_REDSTONE_MODE, v -> lastOutputRedstoneMode = v);
        nbt.listenForString(TAG_LAST_OUTPUT, v -> lastOutput = v);
    }
}
