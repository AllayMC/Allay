package org.allaymc.server.item.component;

import com.google.common.base.Preconditions;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;
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
public class ItemWritableBookBaseComponentImpl extends ItemBaseComponentImpl implements ItemWritableBookBaseComponent {

    protected static final String TAG_PAGES = "pages";
    protected static final String TAG_TEXT = "text";

    protected List<String> pages;

    public ItemWritableBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
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
    public String getPageText(@Range(from = 0, to = 49) int page) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        return pages.get(page);
    }

    @Override
    public void removePage(@Range(from = 0, to = 49) int page) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        pages.remove(page);
    }

    @Override
    public void insertPage(@Range(from = 0, to = 49) int page, String text) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        Preconditions.checkArgument(page <= pages.size(), "unable to insert page at invalid position");
        Preconditions.checkArgument(text.getBytes().length <= 256, "text bytes length shouldn't be greater than 256");
        pages.add(page, text);
    }

    @Override
    public void setPage(@Range(from = 0, to = 49) int page, String text) {
        Preconditions.checkArgument(page >= 0 && page <= 49, "page should be in 0-49");
        Preconditions.checkArgument(text.getBytes().length <= 256, "text bytes length shouldn't be greater than 256");
        if (page >= pages.size()) {
            for (var i = pages.size(); i <= page; i++) {
                pages.add("");
            }
        }
        pages.set(page, text);
    }

    @Override
    public void swapPage(@Range(from = 0, to = 49) int page1, @Range(from = 0, to = 49) int page2) {
        Preconditions.checkArgument(page1 >= 0 && page1 <= 49, "page1 should be in 0-49");
        Preconditions.checkArgument(page2 >= 0 && page2 <= 49, "page2 should be in 0-49");
        var tmp = pages.get(page1);
        pages.set(page1, pages.get(page2));
        pages.set(page2, tmp);
    }

    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag().toBuilder()
                .putList(TAG_PAGES, NbtType.COMPOUND, this.pages.stream().map(page -> NbtMap.builder().putString(TAG_TEXT, page).build()).toList()).build();
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForList(TAG_PAGES, NbtType.COMPOUND, list -> this.pages = list.stream().map(nbt -> nbt.getString(TAG_TEXT)).collect(Collectors.toList()));
    }
}
