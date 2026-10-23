package org.allaymc.api.world.gamerule;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code GameRules} stores values associated with supported world rules and
 * exposes them to gameplay and packet consumers. Rule metadata supplies
 * defaults for missing entries and identifies expected value types. Direct
 * container changes differ from the cancellable changes offered by world
 * metadata.
 *
 * @author Cool_Loong
 */
public interface GameRules {
    /**
     * Returns an unmodifiable view of explicitly stored game rule entries. The
     * view reflects later changes and can omit rules whose defaults remain
     * available through {@link #get(GameRule)}.
     */
    @UnmodifiableView
    Map<GameRule, Object> getGameRules();

    /**
     * Stores a rule value and notifies players in the associated world.
     * Callers must supply the value type declared by {@code gameRule}; this
     * operation bypasses the cancellable world metadata event.
     */
    void set(GameRule gameRule, Object value);

    /**
     * Returns the stored value or the rule's default when no entry exists.
     * Callers choose {@code V} to match the rule category because this generic
     * access does not validate stored value types.
     */
    <V> V get(GameRule gameRule);
}
