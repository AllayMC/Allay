package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;

/**
 * {@code BlockEntityLecternBaseComponent} exposes a lectern's retained book,
 * page selection, and stored page count. The server recalculates page count
 * when assigning a book and clamps ordinary page changes to that retained
 * count, while persistence stores the fields separately.
 *
 * @author daoge_cmd
 */
public interface BlockEntityLecternBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the retained book item, or {@code null} for an empty lectern. The
     * server exposes the same mutable stack, so caller mutations do not
     * automatically recompute page count or update comparator output.
     */
    ItemStack getBook();

    /**
     * Retains a book reference, resets its selected page, and refreshes derived
     * state. {@code null} clears the book; the server derives page count only
     * from written or writable book capabilities and then updates comparators
     * and viewers.
     */
    void setBook(ItemStack book);

    /**
     * Returns whether the lectern retains a non-{@code null} book reference.
     */
    boolean hasBook();

    /**
     * Returns the zero-based page index currently retained by the lectern.
     */
    int getCurrentPage();

    /**
     * Selects a page using the retained page count and updates observers. The
     * server clamps ordinary inputs to the available range, but raw NBT counts
     * can affect integer bounds; {@code Integer.MIN_VALUE} causes subtraction
     * overflow.
     */
    void setCurrentPage(int page);

    /**
     * Returns the stored page count associated with the lectern's book. Book
     * assignment derives it from supported book types, while NBT loading
     * retains a supplied count directly without recomputing it.
     */
    int getTotalPages();

    /**
     * Requests a retained book's drop and clears it through the ordinary
     * setter. An empty lectern does nothing; after the request returns, page
     * fields reset and observers refresh without waiting for entity admission.
     */
    void dropBook();
}
