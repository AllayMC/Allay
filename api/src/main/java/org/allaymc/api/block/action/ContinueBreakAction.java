package org.allaymc.api.block.action;

/**
 * {@code ContinueBreakAction} represents an update to an existing block breaking
 * animation using a duration in seconds. The player input processor can recalculate
 * that duration as equipment or movement conditions change and broadcasts the update
 * at the block being broken.
 *
 * @param breakTime total breaking duration in seconds
 *
 * @author daoge_cmd
 */
public record ContinueBreakAction(double breakTime) implements BlockAction {
}
