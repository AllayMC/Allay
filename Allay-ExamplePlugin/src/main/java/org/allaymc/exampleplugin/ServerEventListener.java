package org.allaymc.exampleplugin;

import org.allaymc.api.event.EventHandler;
import org.allaymc.api.event.player.PlayerInitializedEvent;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class ServerEventListener {

    @EventHandler
    private void onPlayerInitialized(PlayerInitializedEvent event) {
        event.getPlayer().sendText("allay-chan: welcome to the server");
    }
}
