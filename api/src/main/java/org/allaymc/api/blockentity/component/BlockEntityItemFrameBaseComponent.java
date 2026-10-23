package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.jetbrains.annotations.Range;

/**
 * {@code BlockEntityItemFrameBaseComponent} exposes a frame's displayed stack
 * and one of eight rotation positions. The server uses the shared air-stack
 * sentinel for an empty frame, updates map-display block properties when
 * contents change, and persists both stack and rotation.
 *
 * @author daoge_cmd
 */
public interface BlockEntityItemFrameBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the displayed stack, using {@link ItemAirStack#AIR_STACK} for
     * emptiness. The server exposes its retained mutable stack directly, so
     * caller mutations bypass the property, comparator, and viewer updates
     * performed by setters.
     */
    ItemStack getItemStack();

    /**
     * Retains a displayed stack and updates map state, viewers, and comparator
     * output. Callers use {@link ItemAirStack#AIR_STACK} to clear the frame;
     * the server does not normalize a {@code null} value into that sentinel.
     */
    void setItemStack(ItemStack itemStack);

    /**
     * Clears the frame through {@code setItemStack} with the shared air-stack
     * sentinel. The operation follows the setter's ordinary map-property,
     * viewer, and comparator updates and leaves the rotation unchanged.
     */
    default void clearItemStack() {
        setItemStack(ItemAirStack.AIR_STACK);
    }

    /**
     * Returns the stored rotation index among the frame's eight positions.
     */
    @Range(from = 0, to = 7)
    int getItemRotation();

    /**
     * Stores a rotation index from zero through seven and updates viewers. The
     * server rejects values outside that range before mutation, then refreshes
     * comparator output after broadcasting the new rotation.
     */
    void setItemRotation(@Range(from = 0, to = 7) int itemRotation);
}
