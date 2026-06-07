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
 * Called after a player's abilities are changed.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerAbilitiesUpdateEvent extends ServerPlayerEvent {
    protected final Set<PlayerAbility> previous;
    protected final Set<PlayerAbility> current;

    public PlayerAbilitiesUpdateEvent(Player player, Set<PlayerAbility> previous, Set<PlayerAbility> current) {
        super(player);
        this.previous = copyAbilities(previous);
        this.current = copyAbilities(current);
    }

    protected static Set<PlayerAbility> copyAbilities(Set<PlayerAbility> abilities) {
        return abilities.isEmpty()
                ? Collections.unmodifiableSet(EnumSet.noneOf(PlayerAbility.class))
                : Collections.unmodifiableSet(EnumSet.copyOf(abilities));
    }
}
