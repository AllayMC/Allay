package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Consumer;

/**
 * FakeContainer is a type of container that is `fake`. They are not held by any {@link ContainerHolder}, and
 * is usually used to display a GUI. To create fake container instance, use {@link FakeContainerFactory#getFactory()}.
 *
 * @author daoge_cmd
 */
public interface FakeContainer extends Container {

    /**
     * @see #addPlayer(EntityPlayer, Consumer)
     */
    default void addPlayer(EntityPlayer player) {
        addPlayer(player, $ -> {
        });
    }

    /**
     * Adds a player to the fake container, allowing them to interact with it.
     * The callback is invoked with {@code true} if the player was successfully added,
     * or {@code false} otherwise.
     * <p>
     * Please note that you should use this method instead of using {@link #addViewer(ContainerViewer)},
     * since we need to send fake blocks to the client before we can open the container. This requires
     * a delay between the two operations (send fake blocks, open container) due to the client limitation.
     *
     * @param player   the player to be added to the container
     * @param callback a callback function that handles whether the addition was successful
     */
    void addPlayer(EntityPlayer player, Consumer<Boolean> callback);

    /**
     * Adds a click listener to the fake container. The listener will be called when
     * the item in the specified slot is clicked/moved by an {@link EntityPlayer}.
     *
     * @param listener the listener to add
     */
    void addClickListener(int slot, Runnable listener);

    /**
     * Removes a previously added click listener from the fake container.
     *
     * @param listener the listener to remove. Should be the same one that was added previously
     */
    void removeClickListener(int slot, Runnable listener);

    /**
     * Removes all registered click listeners from the fake container.
     */
    void removeAllClickListeners();

    /**
     * Sets the custom name of the fake container. The custom name will appear in the header of the container UI.
     *
     * @param customName the custom name of the container to set, or {@code null} to remove the custom name
     */
    void setCustomName(String customName);

    /**
     * Retrieves the custom name of the fake container.
     *
     * @return the custom name of the fake container, or {@code null} if no custom name has been set
     */
    String getCustomName();

    /**
     * Sets an {@link ItemStack} to a specific slot in the container and attaches a listener
     * that will be notified when the item in the slot is clicked or interacted with by a {@link EntityPlayer}.
     *
     * @param slot      the slot index where the {@link ItemStack} will be placed
     * @param itemStack the {@link ItemStack} to set in the specified slot
     * @param listener  the listener to attach to the slot
     */
    default void setItemStackWithListener(int slot, ItemStack itemStack, Runnable listener) {
        setItemStack(slot, itemStack);
        addClickListener(slot, listener);
    }
}
