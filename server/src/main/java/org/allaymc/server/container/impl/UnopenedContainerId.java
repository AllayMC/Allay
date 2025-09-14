package org.allaymc.server.container.impl;

/**
 * UnopenedContainerId contains ids that can be used to send container content to the
 * player without opening the container.
 * <p>
 * Some containers are seen by the player by default, such as the player's inventory.
 * So it is meaningless to open these containers, and we use these ids to send the
 * content of these containers to the player.
 *
 * @author daoge_cmd
 * @see AbstractPlayerContainer
 */
public interface UnopenedContainerId {
    int PLAYER_INVENTORY = 0;
    int OFFHAND = 119;
    int ARMOR = 120;
    int CURSOR = 124;
}
