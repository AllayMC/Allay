package org.allaymc.api.blockentity.component;

import org.allaymc.api.command.CommandSender;

/**
 * Base component for command block block entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCommandBlockBaseComponent extends BlockEntityBaseComponent, CommandSender {

    /**
     * Represents the mode of a command block.
     *
     * @author daoge_cmd
     */
    enum CommandBlockMode {
        NORMAL,
        REPEATING,
        CHAIN;

        private static final CommandBlockMode[] VALUES = values();

        /**
         * Get a CommandBlockMode by its id.
         *
         * @param id the id
         * @return the CommandBlockMode, or {@code null} if the id is invalid
         */
        public static CommandBlockMode fromId(int id) {
            if (id < 0 || id >= VALUES.length) return null;
            return VALUES[id];
        }
    }

    /**
     * Get the command stored in this command block.
     *
     * @return the command string
     */
    String getCommand();

    /**
     * Set the command to be executed by this command block.
     *
     * @param command the command string
     */
    void setCommand(String command);

    /**
     * Get the mode of this command block based on the block type.
     *
     * @return the mode
     */
    CommandBlockMode getMode();

    /**
     * Check if this command block is in "always active" (auto) mode.
     *
     * @return true if auto mode is enabled
     */
    boolean isAuto();

    /**
     * Set whether this command block is in "always active" (auto) mode.
     *
     * @param auto true to enable auto mode
     */
    void setAuto(boolean auto);

    /**
     * Check if this command block is in conditional mode.
     *
     * @return true if conditional mode is enabled
     */
    boolean isConditional();

    /**
     * Set whether this command block is in conditional mode.
     *
     * @param conditional true to enable conditional mode
     */
    void setConditional(boolean conditional);

    /**
     * Check if the condition for this command block is met.
     *
     * @return true if the condition is met
     */
    boolean isConditionMet();

    /**
     * Check if this command block is powered by redstone.
     *
     * @return true if powered
     */
    boolean isPowered();

    /**
     * Set the powered state of this command block.
     *
     * @param powered true if powered
     */
    void setPowered(boolean powered);

    /**
     * Get the success count of the last command execution.
     *
     * @return the success count
     */
    int getSuccessCount();

    /**
     * Set the success count.
     *
     * @param count the success count
     */
    void setSuccessCount(int count);

    /**
     * Check if this command block is tracking output.
     *
     * @return true if tracking output
     */
    boolean isTrackingOutput();

    /**
     * Set whether this command block should track output.
     *
     * @param trackOutput true to track output
     */
    void setTrackingOutput(boolean trackOutput);

    /**
     * Get the last output of this command block.
     *
     * @return the last output, or null if none
     */
    String getLastOutput();

    /**
     * Set the last output of this command block.
     *
     * @param lastOutput the last output
     */
    void setLastOutput(String lastOutput);

    /**
     * Get the tick delay for this command block.
     *
     * @return the tick delay
     */
    int getTickDelay();

    /**
     * Set the tick delay for this command block.
     *
     * @param tickDelay the tick delay
     */
    void setTickDelay(int tickDelay);

    /**
     * Check if this command block should execute on the first tick.
     *
     * @return true if executing on first tick
     */
    boolean isExecuteOnFirstTick();

    /**
     * Set whether this command block should execute on the first tick.
     *
     * @param executeOnFirstTick true to execute on first tick
     */
    void setExecuteOnFirstTick(boolean executeOnFirstTick);

    /**
     * Trigger the command block execution.
     *
     * @return true if execution was successful
     */
    default boolean trigger() {
        return execute(0);
    }

    /**
     * Execute the command in this command block.
     *
     * @param chainDepth the current chain depth for preventing infinite loops
     * @return true if execution was successful
     */
    boolean execute(int chainDepth);
}
