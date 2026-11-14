package org.allaymc.server.item.component.book;

import com.google.common.base.Preconditions;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code ItemWritableBookBaseComponentImpl} stores and edits ordered
 * writable-book pages using zero-based indices. Edits validate the permitted
 * index range and encoded text size, while page replacement fills gaps with
 * empty text and loading restores the nested page-list representation.
 *
 * @author daoge_cmd
 */
public class ItemWritableBookBaseComponentImpl extends ItemBaseComponentImpl implements ItemWritableBookBaseComponent {

    // TAG_PAGES names the ordered page compound list, and TAG_TEXT names the text
    // stored inside each compound during this component's loading and saving.
    /**
     * Ordered page-list tag.
     */
    protected static final String TAG_PAGES = "pages";
    /**
     * Page text tag.
     */
    protected static final String TAG_TEXT = "text";

    /**
     * {@code pages} holds the current mutable page list. {@code getPages()}
     * exposes an unmodifiable view of this list, while {@code loadExtraTag}
     * can replace the backing list.
     */
    protected List<String> pages;

    /**
     * Creates writable-book behavior with an initially empty mutable page
     * list. The constructor passes ordinary stack initialization to its
     * superclass; component initialization later loads any stored page tag.
     */
    public ItemWritableBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.pages = new ArrayList<>();
    }


    /**
     * Returns the number of pages currently stored in {@code pages}.
     */
    @Override
    public @Range(from = 0, to = 50) int getPageCount() {
        return pages.size();
    }

    /**
     * Returns an unmodifiable view of the current backing page list. In-place
     * edits remain visible, while a subsequent load that replaces
     * {@code pages} leaves earlier views attached to their previous list.
     */
    @Override
    public @UnmodifiableView List<String> getPages() {
        return Collections.unmodifiableList(pages);
    }

    /**
     * Returns the stored text at an existing zero-based page index.
     * {@code page} outside {@code 0} through {@code 49} causes
     * {@link IllegalArgumentException}; an allowed index beyond the stored
     * list causes {@link IndexOutOfBoundsException}.
     */
    @Override
    public String getPageText(@Range(from = 0, to = 49) int page) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        return pages.get(page);
    }

    /**
     * Removes a stored page and shifts later pages toward the beginning.
     * {@code page} must identify an existing position from {@code 0} through
     * {@code 49}; validation and list-index failures leave the list unchanged.
     */
    @Override
    public void removePage(@Range(from = 0, to = 49) int page) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        pages.remove(page);
    }

    /**
     * Inserts text at an existing position or the current end. {@code page}
     * must be from {@code 0} through {@code 49} and at most the current page
     * count; non-{@code null} {@code text} may occupy at most 256
     * default-charset bytes.
     */
    @Override
    public void insertPage(@Range(from = 0, to = 49) int page, String text) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        Preconditions.checkArgument(page <= pages.size(), "unable to insert page at invalid position");
        // The default charset counts encoded bytes, so multibyte text can reach this
        // limit with fewer characters than a single-byte spelling.
        Preconditions.checkArgument(text.getBytes().length <= 256, "text bytes length shouldn't be greater than 256");
        pages.add(page, text);
    }

    /**
     * Replaces a page, extending the list with empty strings when necessary.
     * {@code page} must be from {@code 0} through {@code 49}, and
     * non-{@code null} {@code text} may occupy at most 256 default-charset
     * bytes.
     */
    @Override
    public void setPage(@Range(from = 0, to = 49) int page, String text) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        // The default charset counts encoded bytes, so multibyte text can reach this
        // limit with fewer characters than a single-byte spelling.
        Preconditions.checkArgument(text.getBytes().length <= 256, "text bytes length shouldn't be greater than 256");
        if (page >= pages.size()) {
            for (var i = pages.size(); i <= page; i++) {
                pages.add("");
            }
        }
        pages.set(page, text);
    }

    /**
     * Exchanges text at two existing page positions without changing page
     * count. Both indices must be from {@code 0} through {@code 49}; an index
     * beyond the stored list fails before either page is replaced.
     */
    @Override
    public void swapPage(@Range(from = 0, to = 49) int page1, @Range(from = 0, to = 49) int page2) {
        Preconditions.checkArgument(page1 >= 0 && page1 <= 49, "page1 should be in 0-49");
        Preconditions.checkArgument(page2 >= 0 && page2 <= 49, "page2 should be in 0-49");
        var tmp = pages.get(page1);
        pages.set(page1, pages.get(page2));
        pages.set(page2, tmp);
    }

    /**
     * Serializes the current ordered pages alongside inherited stack extra
     * data. Each page becomes a compound containing {@code TAG_TEXT},
     * preserving list order for later loading by this component.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        // Like BDS, an empty book has no pages tag.
        if (!this.pages.isEmpty()) {
            builder.putList(TAG_PAGES, NbtType.COMPOUND, this.pages.stream().map(page -> NbtMap.builder().putString(TAG_TEXT, page).build()).toList());
        }
        return builder.build();
    }

    /**
     * Loads inherited stack data and replaces pages when the page-list tag
     * exists. Missing page data leaves the current list intact; loaded page
     * counts and text sizes are not revalidated against editing limits.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForList(TAG_PAGES, NbtType.COMPOUND, list -> this.pages = list.stream().map(nbt -> nbt.getString(TAG_TEXT)).collect(Collectors.toList()));
    }
}
