package org.allaymc.api.item.component;

import org.allaymc.api.item.data.WrittenBookGeneration;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * {@code ItemWrittenBookBaseComponent} exposes a signed book's title, author
 * identity, copying generation, and ordered pages. Signed metadata remains
 * editable through this API, and replacing pages copies the supplied list
 * rather than making the book content permanently immutable.
 *
 * @author daoge_cmd
 */
public interface ItemWrittenBookBaseComponent extends ItemBaseComponent {

    /**
     * Returns the title currently stored in this written book.
     */
    String getTitle();

    /**
     * Replaces the title currently stored in this written book. The server
     * setter retains {@code title} directly without validating its length or
     * rejecting {@code null} at assignment time.
     */
    void setTitle(String title);

    /**
     * Returns the author name currently stored in this written book.
     */
    String getAuthor();

    /**
     * Replaces the author name currently stored in this written book. The
     * server setter retains {@code author} directly without checking account
     * identity or rejecting {@code null} at assignment time.
     */
    void setAuthor(String author);

    /**
     * Returns the author account identifier stored with this book.
     */
    String getXuid();

    /**
     * Replaces the author account identifier stored with this book. The server
     * setter retains {@code xuid} directly without verifying the account or
     * rejecting {@code null} at assignment time.
     */
    void setXuid(String xuid);

    /**
     * Returns the copying generation currently stored with this book.
     */
    WrittenBookGeneration getGeneration();

    /**
     * Replaces the copying generation currently stored with this book. The
     * server setter accepts {@code null} at assignment time, but later
     * serialization requires a generation to obtain its ordinal.
     */
    void setGeneration(WrittenBookGeneration generation);

    /**
     * Returns the number of pages currently stored by this book.
     */
    @Range(from = 0, to = 50)
    int getPageCount();

    /**
     * Returns an unmodifiable view of the book's current page list. A later
     * {@code setPages} call replaces the backing list, so the earlier view
     * continues to expose its earlier pages.
     */
    @UnmodifiableView
    List<String> getPages();

    /**
     * Replaces the book's pages with a copy of the supplied list. The server
     * accepts any list length without enforcing the annotated page-count
     * range, and {@code null} causes a copying failure.
     */
    void setPages(List<String> pages);
}
