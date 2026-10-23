package org.allaymc.server.container.impl;

/**
 * {@code UnopenedContainerId} defines fixed protocol inventory IDs for built-in player
 * storage updates. These IDs let player containers send inventory, armor, offhand, and
 * cursor contents without assigning a new window ID or requiring the corresponding
 * interface to be explicitly opened.
 *
 * @author daoge_cmd
 */
public interface UnopenedContainerId {
    /**
     * The built-in inventory IDs select packet destinations used by {@code
     * AbstractPlayerContainer} when synchronizing storage without an explicit window.
     * Main player inventory destination.
     */
    int PLAYER_INVENTORY = 0;
    /**
     * Built-in offhand inventory destination.
     */
    int OFFHAND = 119;
    /**
     * Built-in armor inventory destination.
     */
    int ARMOR = 120;
    /**
     * Built-in cursor inventory destination.
     */
    int CURSOR = 124;
}
