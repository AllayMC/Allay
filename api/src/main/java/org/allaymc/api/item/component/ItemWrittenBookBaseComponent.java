package org.allaymc.api.item.component;

import org.allaymc.api.item.data.WrittenBookGeneration;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * @author daoge_cmd
 */
public interface ItemWrittenBookBaseComponent extends ItemBaseComponent {

    /**
     * Gets the title of this book.
     *
     * @return the title of this book.
     */
    String getTitle();

    /**
     * Sets the title of this book.
     *
     * @param title the title of this book.
     */
    void setTitle(String title);

    /**
     * Gets the author of this book.
     *
     * @return the author of this book.
     */
    String getAuthor();

    /**
     * Sets the author of this book.
     *
     * @param author the author of this book.
     */
    void setAuthor(String author);

    /**
     * Gets the author's xuid of this book.
     *
     * @return the author's xuid of this book.
     */
    String getXuid();

    /**
     * Sets the author's xuid of this book.
     *
     * @param xuid the author's xuid of this book.
     */
    void setXuid(String xuid);

    /**
     * Gets the written book generation of this book.
     *
     * @return the written book generation of this book.
     */
    WrittenBookGeneration getGeneration();

    /**
     * Sets the written book generation of this book.
     *
     * @param generation the written book generation of this book.
     */
    void setGeneration(WrittenBookGeneration generation);

    /**
     * Gets the total number of pages in the book.
     *
     * @return the total number of pages in the book.
     */
    @Range(from = 0, to = 50)
    int getPageCount();

    /**
     * Gets texts of all pages.
     *
     * @return texts of all pages.
     */
    @UnmodifiableView
    List<String> getPages();

    /**
     * Sets the pages of this book.
     *
     * @param pages the pages of this book.
     */
    void setPages(List<String> pages);
}
