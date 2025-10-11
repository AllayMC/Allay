package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWrittenBookBaseComponent;
import org.allaymc.api.item.data.WrittenBookGeneration;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class ItemWrittenBookBaseComponentImpl extends ItemBaseComponentImpl implements ItemWrittenBookBaseComponent {

    protected static final String TAG_PAGES = "pages";
    protected static final String TAG_TEXT = "text";
    protected static final String TAG_AUTHOR = "author";
    protected static final String TAG_XUID = "xuid";
    protected static final String TAG_TITLE = "title";
    protected static final String TAG_GENERATION = "generation";

    protected String title;
    protected String author;
    protected String xuid;
    protected WrittenBookGeneration generation;
    protected List<String> pages;

    public ItemWrittenBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.title = "";
        this.author = "";
        this.xuid = "";
        this.generation = WrittenBookGeneration.ORIGINAL_GENERATION;
        this.pages = new ArrayList<>();
    }

    @Override
    public @Range(from = 0, to = 50) int getPageCount() {
        return pages.size();
    }

    @Override
    public @UnmodifiableView List<String> getPages() {
        return Collections.unmodifiableList(pages);
    }

    @Override
    public void setPages(List<String> pages) {
        this.pages = new ArrayList<>(pages);
    }

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
