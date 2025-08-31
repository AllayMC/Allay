package org.allaymc.api.item.component;

import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * @author daoge_cmd
 */
public interface ItemWritableBookBaseComponent extends ItemBaseComponent {

    /**
     * Gets the total number of pages in the book.
     *
     * @return the total number of pages in the book
     */
    @Range(from = 0, to = 50)
    int getPageCount();

    /**
     * Gets texts of all pages.
     *
     * @return texts of all pages
     */
    @UnmodifiableView
    List<String> getPages();

    /**
     * Gets the text of a specific page from the book.
     *
     * @param page the specific page
     *
     * @return the text of the specific page, or {@code null} if the page does not exist
     *
     * @throws IllegalArgumentException if the page is out of bounds
     */
    String getPageText(@Range(from = 0, to = 49) int page);

    /**
     * Removes a specific page from the book.
     *
     * @param page the specific page to be removed
     *
     * @throws IllegalArgumentException if the page is out of bounds
     */
    void removePage(@Range(from = 0, to = 49) int page);

    /**
     * Inserts a page within the book.
     *
     * @param page the page to insert
     * @param text the text of the page
     *
     * @throws IllegalArgumentException if the page is out of bounds or text bytes length is longer than 256 characters
     */
    void insertPage(@Range(from = 0, to = 49) int page, String text);

    /**
     * Set the text of a specific page, and if the page doesn't exist it will be created
     *
     * @param page the specific page
     * @param text the text to set
     *
     * @throws IllegalArgumentException if the page is out of bounds or text bytes length is longer than 256 characters
     */
    void setPage(@Range(from = 0, to = 49) int page, String text);

    /**
     * Swaps two different pages.
     *
     * @param page1 the first page
     * @param page2 the second page
     *
     * @throws IllegalArgumentException if page1 or page2 is out of bounds
     */
    void swapPage(@Range(from = 0, to = 49) int page1, @Range(from = 0, to = 49) int page2);
}
