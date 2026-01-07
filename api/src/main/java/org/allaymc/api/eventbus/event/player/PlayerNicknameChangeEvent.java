package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.player.OfflinePlayer;

/**
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class PlayerNicknameChangeEvent extends Event {
    protected final OfflinePlayer player;
    protected final String oldNickname;
    protected final String newNickname;
}
