package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityLecternBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.api.item.interfaces.ItemWrittenBookStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Implementation of lectern block entity component.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityLecternBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityLecternBaseComponent {

    protected static final String TAG_BOOK = "book";
    protected static final String TAG_PAGE = "page";
    protected static final String TAG_HAS_BOOK = "hasBook";
    protected static final String TAG_TOTAL_PAGES = "totalPages";

    @Getter
    protected ItemStack book;

    @Getter
    protected int currentPage;

    @Getter
    protected int totalPages;

    public BlockEntityLecternBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void setBook(ItemStack book) {
        this.book = book;
        this.currentPage = 0;
        if (book instanceof ItemWrittenBookStack writtenBook) {
            this.totalPages = writtenBook.getPageCount();
        } else if (book instanceof ItemWritableBookStack writableBook) {
            this.totalPages = writableBook.getPageCount();
        } else {
            this.totalPages = 0;
        }
        // Update comparator output
        getDimension().updateComparatorOutputLevel(getPosition());
        sendBlockEntityToViewers();
    }

    @Override
    public boolean hasBook() {
        return book != null;
    }

    @Override
    public void setCurrentPage(int page) {
        this.currentPage = Math.max(0, Math.min(page, Math.max(0, totalPages - 1)));
        // Update comparator output
        getDimension().updateComparatorOutputLevel(getPosition());
        sendBlockEntityToViewers();
    }

    @Override
    public void dropBook() {
        if (!hasBook()) {
            return;
        }

        var pos = getPosition();
        getDimension().dropItem(book, MathUtils.center(pos).add(0, 0.5, 0));
        setBook(null);
    }

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (hasBook()) {
            var current = event.getCurrentBlock();
            var pos = current.getPosition();
            current.getDimension().dropItem(book, MathUtils.center(pos).add(0, 0.5, 0));
            this.book = null;
        }
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putBoolean(TAG_HAS_BOOK, hasBook());

        if (hasBook()) {
            builder.putCompound(TAG_BOOK, book.saveNBT());
            builder.putInt(TAG_PAGE, currentPage);
            builder.putInt(TAG_TOTAL_PAGES, totalPages);
        }

        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_BOOK, bookNbt -> {
            var item = NBTIO.getAPI().fromItemStackNBT(bookNbt);
            if (item != null && (item.getItemType() == ItemTypes.WRITTEN_BOOK || item.getItemType() == ItemTypes.WRITABLE_BOOK)) {
                this.book = item;
            }
        });

        nbt.listenForInt(TAG_PAGE, page -> this.currentPage = page);
        nbt.listenForInt(TAG_TOTAL_PAGES, total -> this.totalPages = total);
    }
}
