package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * {@code FakeContainer} represents a synthetic inventory interface backed by client-side
 * block updates rather than a world container holder. Plugins create instances through
 * {@link FakeContainerFactory} and use the stored items and click listeners to present
 * an interface. The normal container slot operations still control contents and their
 * notifications.
 *
 * <p>
 * Opening requires fake blocks to reach the player before the container window opens, so
 * {@code addPlayer} schedules the opening after block updates. Callers must use that
 * operation instead of {@code addViewer}. Closing restores the world block view through
 * the implementation; click listeners observe attempted fake-slot transfers rather than
 * ordinary slot-change notifications.
 *
 * @author daoge_cmd
 */
public interface FakeContainer extends Container {

    /**
     * Starts opening this synthetic interface for the player with an ignored outcome
     * callback. Fake block updates precede the delayed window open, so the container is
     * not necessarily open when this call returns.
     */
    default void addPlayer(Player player) {
        addPlayer(player, $ -> {
        });
    }

    /**
     * Starts fake block publication followed by a delayed attempt to open the player's
     * window. The callback runs after the opening attempt and receives its reported
     * outcome.
     *
     * <p>
     * The current server implementation reports {@code false} and then {@code true} when
     * opening is rejected; callers cannot rely on a single callback for that path.
     * Direct {@code addViewer} is unsupported because it omits the block publication
     * delay.
     */
    void addPlayer(Player player, Consumer<Boolean> callback);

    /**
     * Registers a listener for attempted transfers from the specified fake slot. Fake
     * container transfer handling invokes these listeners before rejecting movement of
     * the displayed stack, independently of ordinary slot-change listeners.
     */
    void addClickListener(int slot, Runnable listener);

    /**
     * Removes the supplied listener from the specified fake slot's click listener set.
     * Callers must supply the registered listener instance; removing an absent
     * registration leaves subsequent clicks unaffected.
     */
    void removeClickListener(int slot, Runnable listener);

    /**
     * Removes every registered click listener from all fake container slots.
     */
    void removeAllClickListeners();

    /**
     * Sets the optional title used for subsequent fake block publication. {@code null}
     * removes the custom title; changing this value does not itself resend fake blocks
     * to players already viewing the container.
     */
    void setCustomName(String customName);

    /**
     * Returns the title used for subsequent fake block publication. {@code null} means
     * that no custom title is configured, allowing the client to display its normal
     * container title instead.
     */
    String getCustomName();

    /**
     * Stores the slot's item and then registers its click listener. Item assignment
     * follows ordinary container notifications before listener registration; existing
     * click listeners on that slot remain registered alongside the supplied listener.
     */
    default void setItemStackWithListener(int slot, ItemStack itemStack, Runnable listener) {
        setItemStack(slot, itemStack);
        addClickListener(slot, listener);
    }
}
