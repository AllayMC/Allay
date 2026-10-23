package org.allaymc.api.block.action;

/**
 * {@code StartBreakAction} represents the beginning of a block breaking animation
 * with a duration in seconds. The client uses that duration to progress the visible
 * cracks, while the dimension supplies the position of the block being broken.
 *
 * @param breakTime total breaking duration in seconds
 *
 * @author daoge_cmd
 */
public record StartBreakAction(double breakTime) implements BlockAction {
}
