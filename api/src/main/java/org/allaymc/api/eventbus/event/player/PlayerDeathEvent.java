package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.utils.tuple.Pair;

/**
 * Called when a player dies.
 *
 * @author Kanelucky
 */
public class PlayerDeathEvent extends PlayerEvent implements CancellableEvent {

    private Pair<String, String[]> deathInfo;

    public PlayerDeathEvent(EntityPlayer player, Pair<String, String[]> deathInfo) {
        super(player);
        this.deathInfo = deathInfo;
    }

    /**
     * Gets the complete death information.
     */
    public Pair<String, String[]> getDeathInfo() {
        return deathInfo;
    }

    /**
     * Sets the complete death information.
     */
    public void setDeathInfo(Pair<String, String[]> deathInfo) {
        this.deathInfo = deathInfo;
    }

    /**
     * Gets the death message translation key.
     */
    public String getDeathMessage() {
        return deathInfo.left();
    }

    /**
     * Sets the death message.
     */
    public void setDeathMessage(String deathMessage) {
        this.deathInfo = new Pair<>(deathMessage, new String[0]);
    }

    /**
     * Sets the death message translation key and formatting arguments.
     */
    public void setDeathMessage(String translationKey, String... arguments) {
        this.deathInfo = new Pair<>(translationKey, arguments);
    }
}