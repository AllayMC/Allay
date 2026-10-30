package org.allaymc.api.blockentity.component;

import org.allaymc.api.command.CommandSender;

/**
 * {@code BlockEntityCommandBlockBaseComponent} exposes command-block execution
 * settings, retained results, and command-sender behavior. The server derives
 * execution mode from the surrounding block type, evaluates conditional
 * neighbors, and can propagate execution through facing chain blocks
 * independently of the stored command's success count.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCommandBlockBaseComponent extends BlockEntityBaseComponent, CommandSender {

    /**
     * {@code CommandBlockMode} identifies impulse, repeating, and chain
     * execution categories selected from the surrounding command-block type.
     *
     * @author daoge_cmd
     */
    enum CommandBlockMode {
        /**
         * Ordinary impulse execution mode.
         */
        NORMAL,
        /**
         * Active repeating execution mode.
         */
        REPEATING,
        /**
         * Facing-linked chain execution mode.
         */
        CHAIN;

        /**
         * {@code VALUES} caches declaration-order modes so NBT mode IDs can be
         * resolved without allocating a fresh enum array for each lookup.
         */
        private static final CommandBlockMode[] VALUES = values();

        /**
         * Returns the mode associated with a declaration-order numeric ID.
         * Negative IDs and IDs beyond the known constants return {@code null},
         * leaving callers to choose how unsupported stored mode data is
         * handled.
         */
        public static CommandBlockMode fromId(int id) {
            // Unknown persisted IDs cannot identify a known mode, so expose
            // absence to the loader rather than selecting a different execution
            // category silently.
            if (id < 0 || id >= VALUES.length) return null;
            return VALUES[id];
        }
    }

    /**
     * Returns the retained command text used when execution is permitted. The
     * server initially stores an empty string and permits {@code null}; both
     * values skip command dispatch while the execution path can still propagate
     * a chain.
     */
    String getCommand();

    /**
     * Stores command text for a later execution attempt without dispatching it.
     * The server retains {@code null} as an empty-command selection and does
     * not broadcast or reset existing execution results through this setter.
     */
    void setCommand(String command);

    /**
     * Returns the mode derived from the block type at this entity's position.
     * The server recognizes repeating and chain block types explicitly and
     * treats other surrounding block types as {@code CommandBlockMode.NORMAL}.
     */
    CommandBlockMode getMode();

    /**
     * Returns whether the stored always-active execution setting is enabled.
     */
    boolean isAuto();

    /**
     * Replaces the always-active flag consulted by subsequent execution
     * attempts.
     */
    void setAuto(boolean auto);

    /**
     * Returns whether execution depends on the command block behind this one.
     */
    boolean isConditional();

    /**
     * Replaces the conditional-mode flag consulted by subsequent execution
     * attempts.
     */
    void setConditional(boolean conditional);

    /**
     * Returns the retained condition result from the latest condition
     * evaluation.
     */
    boolean isConditionMet();

    /**
     * Returns whether this command block remembers an active redstone input.
     */
    boolean isPowered();

    /**
     * Replaces the remembered redstone-input flag used by execution activation
     * checks.
     */
    void setPowered(boolean powered);

    /**
     * Returns the retained result count consulted by conditional successor
     * blocks.
     */
    int getSuccessCount();

    /**
     * Replaces the retained command result count without dispatching another
     * command.
     */
    void setSuccessCount(int count);

    /**
     * Returns whether output messages are retained as this block's latest
     * output.
     */
    boolean isTrackingOutput();

    /**
     * Changes whether later command output replaces the retained output text.
     * The server leaves existing text unchanged when tracking is disabled, and
     * this setting does not independently control player broadcasts.
     */
    void setTrackingOutput(boolean trackOutput);

    /**
     * Returns retained output text, or {@code null} when none is currently
     * stored. The server can retain earlier text while tracking is disabled and
     * clears text before dispatching a nonempty permitted command.
     */
    String getLastOutput();

    /**
     * Replaces retained output text without sending it to players. {@code null}
     * clears the reference, and the server's generated setter does not change
     * the tracking flag or execution result count.
     */
    void setLastOutput(String lastOutput);

    /**
     * Returns the nonnegative repeating-execution delay setting measured in
     * game ticks.
     */
    int getTickDelay();

    /**
     * Stores the repeating-execution delay setting used by later ticking. The
     * server clamps negative inputs to zero without resetting the existing
     * activation counter, so an in-progress interval uses the new bound.
     */
    void setTickDelay(int tickDelay);

    /**
     * Returns whether a repeating block triggers on its next activation edge.
     */
    boolean isExecuteOnFirstTick();

    /**
     * Replaces the flag controlling execution on a repeating block's activation
     * edge.
     */
    void setExecuteOnFirstTick(boolean executeOnFirstTick);

    /**
     * Starts an execution attempt at chain depth zero through {@code execute}.
     * The returned flag follows that operation's continuation semantics and
     * does not by itself establish that a command was dispatched or succeeded.
     */
    default boolean trigger() {
        return execute(0);
    }

    /**
     * Attempts command-block processing at the supplied recursive chain depth.
     * The server returns {@code false} for rejected attempts and cancelled
     * command events; {@code true} can also represent duplicate-tick
     * suppression or an inactive block that propagates its chain without a
     * successful command.
     */
    boolean execute(int chainDepth);
}
