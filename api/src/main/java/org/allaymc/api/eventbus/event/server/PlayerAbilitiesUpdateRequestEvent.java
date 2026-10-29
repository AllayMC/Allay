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
 * Called before an operator's requested ability update is applied to another player.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerAbilitiesUpdateRequestEvent extends ServerPlayerEvent implements CancellableEvent {
    protected final Player target;
    protected final Set<PlayerAbility> abilities;

    public PlayerAbilitiesUpdateRequestEvent(Player player, Player target, Set<PlayerAbility> abilities) {
        super(player);
        this.target = target;
        this.abilities = abilities.isEmpty()
                ? Collections.unmodifiableSet(EnumSet.noneOf(PlayerAbility.class))
                : Collections.unmodifiableSet(EnumSet.copyOf(abilities));
    }
}
