package org.allaymc.api.item.component;

import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * {@code ItemWritableBookBaseComponent} exposes editable book pages through
 * zero-based indices. Page edits accept indices from {@code 0} through
 * {@code 49}, while insertion shifts later pages and replacement can fill
 * intervening positions with empty text.
 *
 * @author daoge_cmd
 */
public interface ItemWritableBookBaseComponent extends ItemBaseComponent {

    /**
     * Returns the number of pages currently stored by this book.
     */
    @Range(from = 0, to = 50)
    int getPageCount();

    /**
     * Returns an unmodifiable view of the current page list. Edits to that
     * list remain visible through the view, but replacing the list while
     * loading data leaves earlier views attached to their earlier list.
     */
    @UnmodifiableView
    List<String> getPages();

    /**
     * Returns the text at an existing zero-based page index. The server
     * rejects indices outside {@code 0} through {@code 49} and throws an index
     * exception for an allowed index without a stored page.
     */
    String getPageText(@Range(from = 0, to = 49) int page);

    /**
     * Removes an existing page and shifts later pages toward the beginning.
     * {@code page} must identify a stored page within {@code 0} through
     * {@code 49}; an absent position causes an index exception.
     */
    void removePage(@Range(from = 0, to = 49) int page);

    /**
     * Inserts text before the given page or at the current end. {@code page}
     * must be within {@code 0} through {@code 49} and no greater than the page
     * count; {@code text} must encode to at most 256 bytes in the platform
     * default charset.
     */
    void insertPage(@Range(from = 0, to = 49) int page, String text);

    /**
     * Stores text at the specified page, adding empty pages for intervening
     * positions. {@code page} must be within {@code 0} through {@code 49}, and
     * non-{@code null} {@code text} must encode to at most 256 bytes in the
     * platform default charset.
     */
    void setPage(@Range(from = 0, to = 49) int page, String text);

    /**
     * Exchanges the text at two existing zero-based page indices. Both indices
     * must identify stored pages within {@code 0} through {@code 49}; swapping
     * a page with itself leaves its text unchanged.
     */
    void swapPage(@Range(from = 0, to = 49) int page1, @Range(from = 0, to = 49) int page2);
}
