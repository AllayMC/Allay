package org.allaymc.api.world.gamerule;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * GameRules is a container for storing and managing game rules.
 *
 * @author Cool_Loong
 */
public interface GameRules {
    /**
     * Get the game rules.
     *
     * @return the game rules
     */
    @UnmodifiableView
    Map<GameRule, Object> getGameRules();

    /**
     * Set a game rule's value.
     *
     * @param gameRule the game rule
     * @param value    the value
     */
    void set(GameRule gameRule, Object value);

    /**
     * Get a game rule's value.
     *
     * @param gameRule the game rule
     * @param <V>      the type of the value
     *
     * @return the value
     */
    <V> V get(GameRule gameRule);
}
