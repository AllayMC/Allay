package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStack;

/**
 * {@code BlockEntityDecoratedPotBaseComponent} exposes one stored item stack,
 * one-item insertion, and the four decorated faces of a pot. The server updates
 * comparator output when contents change, while direct stack access shares the
 * retained mutable item with callers.
 *
 * @author IWareQ
 */
public interface BlockEntityDecoratedPotBaseComponent extends BlockEntityBaseComponent {
    /**
     * {@code MAX_STACK_SIZE} limits pot insertion and supplies the capacity
     * used when the default comparator calculation scales the retained item
     * count.
     */
    int MAX_STACK_SIZE = 64;

    /**
     * Attempts to add one item without decreasing the supplied stack. The
     * server rejects empty inputs, incompatible contents, and full stacks;
     * successful insertion updates comparator output and the pot's viewers.
     */
    boolean tryInsertItem(ItemStack item);

    /**
     * Returns the retained item stack, or {@code null} for an empty pot. The
     * server exposes the mutable stack directly; changing it bypasses
     * comparator and viewer updates normally performed by pot mutators.
     */
    ItemStack getItem();

    /**
     * Replaces the stored stack, or clears contents for {@code null}. The
     * server retains the reference without capacity checks and then updates
     * comparator output and viewers; the caller retains access to that same
     * stack.
     */
    void setItem(ItemStack item);

    /**
     * Returns whether this pot retains no item stack reference.
     */
    default boolean isEmpty() {
        return this.getItem() == null;
    }

    /**
     * Returns whether a retained stack reaches the pot's fixed capacity. This
     * check uses {@code MAX_STACK_SIZE} alone; insertion can reject a smaller
     * stack when its item type has a lower maximum.
     */
    default boolean isFull() {
        var item = this.getItem();
        return item != null && item.getCount() >= MAX_STACK_SIZE;
    }

    /**
     * Clears the retained contents through {@code setItem} with {@code null}.
     * The server follows its normal comparator and viewer update path, while
     * the pot's decorated faces remain unchanged.
     */
    default void clear() {
        this.setItem(null);
    }

    /**
     * Calculates comparator output from the retained count and fixed pot
     * capacity. An absent stack returns zero, while a present ordinary stack
     * produces at least one. Directly supplied oversized stacks are not clamped
     * to fifteen.
     */
    default int getComparatorOutput() {
        var item = this.getItem();
        if (item == null) {
            return 0;
        }

        // Scale occupied capacity into fourteen increments above the nonempty
        // minimum.
        return Math.max(1, (int) Math.floor(((double) item.getCount() / MAX_STACK_SIZE) * 14d) + 1);
    }

    /**
     * Returns the four decorated face identifiers retained for this pot.
     */
    PotSherds getSherds();

    /**
     * Replaces the decorated faces and sends updated state to viewers. The
     * server replaces {@code null} with {@code PotSherds.DEFAULT}, which
     * represents four brick faces, while leaving the stored item unchanged.
     */
    void setSherds(PotSherds sherds);
}
