package org.allaymc.api.block.action;

/**
 * @author daoge_cmd
 */
public enum SimpleBlockAction implements BlockAction {
    // OPEN is a block action that open a block at a position. It is sent for blocks such as chests.
    OPEN,
    // CLOSE is a block action that close a block at a position, complementary to the OPEN action.
    CLOSE,
    // STOP_BREAK is a block action to make the cracks forming in a block stop and disappear.
    STOP_BREAK
}
