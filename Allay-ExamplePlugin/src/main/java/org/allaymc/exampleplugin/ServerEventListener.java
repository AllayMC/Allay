package org.allaymc.exampleplugin;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.player.PlayerInitializedEvent;
import org.allaymc.api.form.Forms;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class ServerEventListener {

    @EventHandler
    private void onPlayerInitialized(PlayerInitializedEvent event) {
        var player = event.getPlayer();
        player.sendText("allay-chan: welcome to the server");
        player.addServerSettingForm(
                Forms.custom()
                        .title("title of this custom form")
                        .dropdown("a dropdown", "a", "b", "c")
                        .input("an input", "a placeholder", "a default text")
                        .label("a label")
                        .slider("a slider", 0f, 100f)
                        .stepSlider("a step slider", "a", "b", "c")
                        .toggle("a toggle", false)
                        .onResponse(responses -> {
                            player.sendText("dropdown: " + responses.get(0));
                            player.sendText("input: " + responses.get(1));
                            player.sendText("label: " + responses.get(2));
                            player.sendText("slider: " + responses.get(3));
                            player.sendText("step slider: " + responses.get(4));
                            player.sendText("toggle: " + responses.get(5));
                        })
        );
    }
}
