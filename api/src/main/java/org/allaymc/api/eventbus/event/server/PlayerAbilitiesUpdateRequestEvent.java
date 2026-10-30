package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerAbility;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * {@code PlayerAbilitiesUpdateRequestEvent} represents an operator's proposal
 * to apply an ability set to a player.
 * The inherited {@code player} identifies the requesting operator, while {@code target}
 * identifies the recipient. The ability set is an unmodifiable copy of the
 * proposal; cancellation prevents the packet processor from applying it.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerAbilitiesUpdateRequestEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code target} holds the live recipient of the requested update. The
     * recipient can also be the requesting operator when the client selects itself.
     */
    protected final Player target;
    /**
     * {@code abilities} holds the complete proposed ability set. Construction
     * copies the source collection so later source changes cannot alter this proposal.
     */
    protected final Set<PlayerAbility> abilities;

    /**
     * Creates a request retaining both player references and copying the proposed
     * abilities. The ability set may be empty, but {@code null} sets or elements are
     * invalid and the resulting snapshot is unmodifiable.
     */
    public PlayerAbilitiesUpdateRequestEvent(Player player, Player target, Set<PlayerAbility> abilities) {
        super(player);
        this.target = target;
        // Empty collections do not identify an enum element type for copyOf;
        // an explicitly typed empty set preserves the same immutable snapshot contract.
        this.abilities = abilities.isEmpty()
                ? Collections.unmodifiableSet(EnumSet.noneOf(PlayerAbility.class))
                : Collections.unmodifiableSet(EnumSet.copyOf(abilities));
    }
}
