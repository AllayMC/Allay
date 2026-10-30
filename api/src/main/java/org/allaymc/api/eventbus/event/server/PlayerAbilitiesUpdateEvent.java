package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerAbility;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * {@code PlayerAbilitiesUpdateEvent} reports a player's abilities after an
 * update when a controlled entity exists.
 * {@code previous} and {@code current} hold separate, unmodifiable snapshots captured
 * at construction, so later ability changes do not alter this notification.
 * The update has already occurred and cannot be cancelled through this event.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerAbilitiesUpdateEvent extends ServerPlayerEvent {
    /**
     * Abilities before the update.
     */
    protected final Set<PlayerAbility> previous;
    /**
     * Abilities after the update.
     */
    protected final Set<PlayerAbility> current;

    /**
     * Creates a notification by copying both supplied ability sets. Empty sets
     * are accepted, while {@code null} sets or {@code null} elements are invalid; the generated
     * getters expose unmodifiable snapshots.
     */
    public PlayerAbilitiesUpdateEvent(Player player, Set<PlayerAbility> previous, Set<PlayerAbility> current) {
        super(player);
        this.previous = copyAbilities(previous);
        this.current = copyAbilities(current);
    }

    /**
     * Copies abilities into an unmodifiable enum set independent of its source.
     * Empty collections retain the {@code PlayerAbility} element type; the copying
     * operations reject {@code null} collections and {@code null} elements.
     */
    protected static Set<PlayerAbility> copyAbilities(Set<PlayerAbility> abilities) {
        // An empty general-purpose set cannot supply EnumSet.copyOf with an
        // element type, so construct the typed empty snapshot explicitly.
        return abilities.isEmpty()
                ? Collections.unmodifiableSet(EnumSet.noneOf(PlayerAbility.class))
                : Collections.unmodifiableSet(EnumSet.copyOf(abilities));
    }
}
