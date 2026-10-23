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
 * {@code BlockEntityLecternBaseComponentImpl} stores a lectern's book, selected
 * page, and page count and updates comparators when ordinary mutators change
 * them. {@code setBook} retains the supplied mutable item and derives a count
 * from written or writable book capabilities; unsupported items remain retained
 * with zero pages.
 *
 * <p>
 * {@code setCurrentPage} clamps against the retained count rather than reading
 * the book again, so direct item mutations do not automatically change the
 * selection bound. NBT loading assigns accepted book data, page, and count
 * independently and bypasses that clamp. Explicit dropping clears all fields
 * through {@code setBook}, while block replacement clears only the item
 * reference after dropping because the block entity is being removed.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityLecternBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityLecternBaseComponent {

    /**
     * {@code TAG_BOOK} names the retained item compound. The lectern tag
     * constants describe book presence, selected page, and retained page count
     * during saving.
     */
    protected static final String TAG_BOOK = "book";
    /**
     * Persisted selected page index.
     */
    protected static final String TAG_PAGE = "page";
    /**
     * Persisted book presence flag.
     */
    protected static final String TAG_HAS_BOOK = "hasBook";
    /**
     * Persisted retained page count.
     */
    protected static final String TAG_TOTAL_PAGES = "totalPages";

    /**
     * {@code book} holds a shared mutable item, or {@code null} when empty.
     * {@code setBook} derives {@code totalPages} and resets
     * {@code currentPage}, but direct item mutation and NBT loading bypass that
     * derivation.
     */
    @Getter
    protected ItemStack book;

    /**
     * Retained zero-based page index.
     */
    @Getter
    protected int currentPage;

    /**
     * Retained book page count.
     */
    @Getter
    protected int totalPages;

    /**
     * Creates common lectern state with no book and zero page values.
     */
    public BlockEntityLecternBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Retains the supplied item, resets its page, and refreshes observers.
     * Written and writable books supply page count; all other items, including
     * {@code null}, produce zero pages. Comparator refresh precedes viewer
     * delivery, and later failures do not undo the retained item or page
     * fields.
     */
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
        getDimension().updateComparatorOutputLevel(getPosition());
        sendBlockEntityToViewers();
    }

    /**
     * Returns whether the lectern currently retains any non-{@code null} item.
     */
    @Override
    public boolean hasBook() {
        return book != null;
    }

    /**
     * Clamps a requested page using the retained page count and refreshes
     * observers. Negative requests select zero; ordinary positive counts bound
     * the result to one less than the count. Zero and negative counts normally
     * select zero, but {@code Integer.MIN_VALUE - 1} overflows to
     * {@code Integer.MAX_VALUE}, so that malformed stored count permits any
     * nonnegative page. The bound comes from {@code totalPages} rather than
     * another book read. Comparator refresh precedes viewer delivery after the
     * selected page has already been stored.
     */
    @Override
    public void setCurrentPage(int page) {
        // Empty page ranges still select zero rather than a negative last
        // index.
        this.currentPage = Math.max(0, Math.min(page, Math.max(0, totalPages - 1)));
        getDimension().updateComparatorOutputLevel(getPosition());
        sendBlockEntityToViewers();
    }

    /**
     * Requests the retained book's drop above the lectern, then clears through
     * {@code setBook}. An empty lectern returns without updates. After the
     * request returns, page fields reset and comparators and viewers refresh;
     * later entity admission is not awaited or checked.
     */
    @Override
    public void dropBook() {
        if (!hasBook()) {
            return;
        }

        var pos = getPosition();
        getDimension().dropItem(book, MathUtils.center(pos).add(0, 0.5, 0));
        setBook(null);
    }

    /**
     * Drops a retained book when its lectern block is replaced. The item
     * reference is cleared directly after the drop; page fields remain
     * untouched because this removal path does not use {@code setBook}.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (hasBook()) {
            var current = event.getCurrentBlock();
            var pos = current.getPosition();
            current.getDimension().dropItem(book, MathUtils.center(pos).add(0, 0.5, 0));
            this.book = null;
        }
    }

    /**
     * Builds common NBT with book presence and any occupied book fields. An
     * empty lectern writes only the {@code false} presence flag from this
     * component, omitting its book, selected-page, and page-count entries.
     */
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

    /**
     * Loads common state and independently applies accepted book and page
     * entries. Only written or writable book types replace the item; missing or
     * unsupported book data preserves it. Supplied page and count integers
     * bypass setter clamping.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_BOOK, bookNbt -> {
            var item = NBTIO.getAPI().fromItemStackNBT(bookNbt);
            // Unsupported stored items cannot provide lectern page content, so
            // leave the previous book in place while loading page metadata
            // independently below.
            if (item != null && (item.getItemType() == ItemTypes.WRITTEN_BOOK || item.getItemType() == ItemTypes.WRITABLE_BOOK)) {
                this.book = item;
            }
        });

        nbt.listenForInt(TAG_PAGE, page -> this.currentPage = page);
        nbt.listenForInt(TAG_TOTAL_PAGES, total -> this.totalPages = total);
    }
}
