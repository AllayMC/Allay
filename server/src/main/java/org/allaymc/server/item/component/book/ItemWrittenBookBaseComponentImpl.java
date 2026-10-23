package org.allaymc.server.item.component.book;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWrittenBookBaseComponent;
import org.allaymc.api.item.data.WrittenBookGeneration;
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
 * {@code ItemWrittenBookBaseComponentImpl} stores written-book metadata and an
 * ordered page list. Generated metadata setters retain supplied values
 * directly, while {@code setPages} copies its list and the extra-tag methods
 * load and save the signed-book representation.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class ItemWrittenBookBaseComponentImpl extends ItemBaseComponentImpl implements ItemWrittenBookBaseComponent {

    // These tags encode signed-book metadata and ordered page compounds in the
    // same extra-tag map used by the inherited stack serialization.
    /**
     * Ordered page-list tag.
     */
    protected static final String TAG_PAGES = "pages";
    /**
     * Page text tag.
     */
    protected static final String TAG_TEXT = "text";
    /**
     * Author name tag.
     */
    protected static final String TAG_AUTHOR = "author";
    /**
     * Author account tag.
     */
    protected static final String TAG_XUID = "xuid";
    /**
     * Book title tag.
     */
    protected static final String TAG_TITLE = "title";
    /**
     * Copying generation tag.
     */
    protected static final String TAG_GENERATION = "generation";

    /**
     * {@code title} holds the displayed book title. The generated setter
     * retains its supplied value directly, including {@code null}.
     */
    protected String title;
    /**
     * {@code author} holds the displayed author name. The generated setter
     * retains its supplied value directly, including {@code null}.
     */
    protected String author;
    /**
     * {@code xuid} holds the author account identifier. The generated setter
     * retains its supplied value directly, including {@code null}.
     */
    protected String xuid;
    /**
     * {@code generation} holds the copying generation serialized as an
     * ordinal. The generated setter accepts {@code null}, which prevents later
     * ordinal serialization.
     */
    protected WrittenBookGeneration generation;
    /**
     * {@code pages} holds mutable ordered page storage. {@code setPages}
     * copies its input, and {@code getPages} exposes an unmodifiable view of
     * the current list.
     */
    protected List<String> pages;

    /**
     * Creates an empty written book using the supplied stack initialization.
     * The constructor initializes blank title and author fields, an empty page
     * list, and {@code ORIGINAL_GENERATION} before component initialization
     * loads stored data.
     */
    public ItemWrittenBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.title = "";
        this.author = "";
        this.xuid = "";
        this.generation = WrittenBookGeneration.ORIGINAL_GENERATION;
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
     * Returns an unmodifiable view of the current page list. Later
     * {@code setPages} or page-tag loading replaces that list, so previously
     * returned views continue to show the earlier list contents.
     */
    @Override
    public @UnmodifiableView List<String> getPages() {
        return Collections.unmodifiableList(pages);
    }

    /**
     * Replaces the stored pages with a new list containing the supplied
     * strings. The copy accepts arbitrary length and {@code null} elements;
     * {@code null} for the list itself causes {@link NullPointerException}.
     */
    @Override
    public void setPages(List<String> pages) {
        this.pages = new ArrayList<>(pages);
    }

    /**
     * Serializes book metadata and ordered pages alongside inherited stack
     * data. {@code generation} must be non-{@code null} because this method
     * stores its ordinal, and page strings become nested page compounds.
     */
    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag().toBuilder()
                .putList(TAG_PAGES, NbtType.COMPOUND, this.pages.stream().map(page -> NbtMap.builder().putString(TAG_TEXT, page).build()).toList())
                .putString(TAG_AUTHOR, this.author)
                .putString(TAG_XUID, this.xuid)
                .putString(TAG_TITLE, this.title)
                .putInt(TAG_GENERATION, this.generation.ordinal())
                .build();
    }

    /**
     * Loads inherited stack data and applies each present book tag
     * independently. Missing tags retain their current values; an invalid
     * generation ordinal can fail after earlier book fields have already
     * changed.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForList(TAG_PAGES, NbtType.COMPOUND, list -> this.pages = list.stream().map(nbt -> nbt.getString(TAG_TEXT)).collect(Collectors.toList()));
        extraTag.listenForString(TAG_AUTHOR, author -> this.author = author);
        extraTag.listenForString(TAG_XUID, xuid -> this.xuid = xuid);
        extraTag.listenForString(TAG_TITLE, title -> this.title = title);
        extraTag.listenForInt(TAG_GENERATION, generation -> this.generation = WrittenBookGeneration.fromGeneration(generation));
    }
}
