package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;

/**
 * Base component for lectern block entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityLecternBaseComponent extends BlockEntityBaseComponent {
    /**
     * Get the book currently on the lectern.
     *
     * @return the book item, or {@code null} if no book is present
     */
    ItemStack getBook();

    /**
     * Set the book on the lectern.
     *
     * @param book the book item, or {@code null} to remove the book
     */
    void setBook(ItemStack book);

    /**
     * Check if the lectern has a book.
     *
     * @return true if a book is present
     */
    boolean hasBook();

    /**
     * Get the current page number (0-indexed).
     *
     * @return the current page
     */
    int getCurrentPage();

    /**
     * Set the current page number (0-indexed).
     *
     * @param page the page number
     */
    void setCurrentPage(int page);

    /**
     * Get the total number of pages in the book.
     *
     * @return the total pages, or 0 if no book
     */
    int getTotalPages();

    /**
     * Drop the book from the lectern.
     */
    void dropBook();
}
