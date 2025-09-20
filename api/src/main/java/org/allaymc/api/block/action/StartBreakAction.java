package org.allaymc.api.block.action;

/**
 * StartBreakAction is a block action to make the cracks in a block start forming, following the break time set in
 * the action.
 *
 * @param breakTime the time needs to break the block, in seconds
 * @author daoge_cmd
 */
public record StartBreakAction(double breakTime) implements BlockAction {
}
