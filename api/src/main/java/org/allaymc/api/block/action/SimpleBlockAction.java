package org.allaymc.api.block.action;

/**
 * {@code SimpleBlockAction} identifies block animations that require no additional
 * action data. Opening and closing actions describe container lid changes, while
 * stopping a break animation removes its cracks; the dimension supplies the position
 * associated with each action.
 *
 * @author daoge_cmd
 */
public enum SimpleBlockAction implements BlockAction {
    /**
     * Container opening animation action.
     */
    OPEN,
    /**
     * Container closing animation action.
     */
    CLOSE,
    /**
     * Block crack removal action.
     */
    STOP_BREAK
}
