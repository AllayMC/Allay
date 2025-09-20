package org.allaymc.api.block.action;

/**
 * ContinueBreakAction is a block action sent every so often to continue the cracking process of the block. It is
 * only ever sent after a StartBreakAction action, and may have an altered break time if the player is not on the
 * ground, submerged or is using a different item than at first.
 *
 * @param breakTime the time needs to break the block, in seconds
 * @author daoge_cmd
 */
public record ContinueBreakAction(double breakTime) implements BlockAction {
}
