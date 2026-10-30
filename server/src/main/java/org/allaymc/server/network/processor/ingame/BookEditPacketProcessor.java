package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.player.PlayerBookEditEvent;
import org.allaymc.api.item.data.WrittenBookGeneration;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

/**
 * {@code BookEditPacketProcessor} applies edits or signing to a writable book in
 * the controlled entity's selected hand slot. The primary page index must be between zero and
 * forty-nine, and nonnull text is limited by its default-charset byte length. A
 * cancellable book-edit event runs before the selected edit action is applied.
 *
 * <p>
 * Adding a page near the current end can extend the book with empty text, while
 * delete and swap branches treat a {@code null} page-text result as a no-op. This does
 * not guarantee recovery for every absent page: the standard book
 * implementation indexes its page list directly and may throw before returning
 * {@code null}. Signing checks the login XUID and replaces the writable book with a
 * newly created written book. Successful actions and handled no-ops notify the
 * hand, while propagated failures do not complete that notification.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BookEditPacketProcessor extends PacketProcessor<BookEditPacket> {
    /**
     * Validates the hand slot, writable-book type, primary page range, and
     * nonnull text length before firing the edit event. Cancellation prevents
     * the selected action, while accepted signing additionally requires the
     * packet XUID to match login data.
     *
     * <p>
     * Near-end additions create empty pages; {@code null} page-text results suppress
     * deletion or swapping while still reaching the final hand notification.
     * The processor does not catch book access failures, including invalid
     * secondary indexes or missing list entries in the standard implementation.
     * Signing copies book pages into a new written-book stack and replaces the
     * hand only after its fields are assigned.
     */
    @Override
    public void handleSync(Player player, BookEditPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        if (entity.getHandSlot() != packet.getInventorySlot()) {
            log.warn("Invalid inventory slot: {}, should be {}", packet.getInventorySlot(), entity.getHandSlot());
            return;
        }

        if (!(entity.getItemInHand() instanceof ItemWritableBookStack book)) {
            log.warn("Inventory slot {} does not contain a writable book", entity.getHandSlot());
            return;
        }

        var page = packet.getPageNumber();
        // Writable-book page indexes stay within the fifty-page limit.
        if (page >= 50 || page < 0) {
            log.warn("Page number {} is out of bounds", page);
            return;
        }

        var text = packet.getText();
        // Limit encoded bytes in the default charset, not characters.
        if (text != null && text.getBytes().length > 256) {
            log.warn("Text can not be longer than 256 bytes");
            return;
        }

        var event = new PlayerBookEditEvent(
                entity, book,
                switch (packet.getAction()) {
                    case REPLACE_PAGE -> PlayerBookEditEvent.Action.REPLACE_PAGE;
                    case ADD_PAGE -> PlayerBookEditEvent.Action.ADD_PAGE;
                    case DELETE_PAGE -> PlayerBookEditEvent.Action.DELETE_PAGE;
                    case SWAP_PAGES -> PlayerBookEditEvent.Action.SWAP_PAGES;
                    case SIGN_BOOK -> PlayerBookEditEvent.Action.SIGN_BOOK;
                }
        );
        // Give listeners the edit action before changing page contents or
        // replacing the hand with a signed book, so cancellation suppresses
        // those changes.
        if (!event.call()) {
            return;
        }

        switch (packet.getAction()) {
            case REPLACE_PAGE -> book.setPage(page, text);
            case ADD_PAGE -> {
                // Keep additions within the writable book's total-page limit.
                if (book.getPageCount() >= 50) {
                    log.warn("Unable to add page beyond 50");
                    return;
                }

                // Near-end page additions extend with empty text, accommodating
                // requested indexes up to two positions beyond the current page
                // count without inserting supplied text.
                if (page >= book.getPageCount() && page <= book.getPageCount() + 2) {
                    book.setPage(page, "");
                    break;
                }

                if (book.getPageText(page) == null) {
                    log.warn("Unable to insert page at {}", page);
                    return;
                }

                book.insertPage(page, text);
            }
            case DELETE_PAGE -> {
                if (book.getPageText(page) == null) {
                    // A null page result makes deletion a no-op while still
                    // reaching hand notification; this branch does not catch
                    // failures from a book implementation that throws for a
                    // missing page.
                    break;
                }

                book.removePage(page);
            }
            case SWAP_PAGES -> {
                var secondPage = packet.getSecondaryPageNumber();
                // The secondary index shares the writable book's page range.
                if (secondPage >= 50) {
                    log.warn("Second page number out of bounds");
                    return;
                }

                if (book.getPageText(page) == null || book.getPageText(secondPage) == null) {
                    // A null result on either side leaves page order unchanged
                    // and still notifies the hand; out-of-range getter failures
                    // propagate before this no-op can be selected.
                    break;
                }

                book.swapPage(page, secondPage);
            }
            case SIGN_BOOK -> {
                if (!packet.getXuid().equals(player.getLoginData().getXuid())) {
                    log.warn("Player xuid mismatch! Expected: {}, Actual: {}", player.getLoginData().getXuid(), packet.getXuid());
                    return;
                }

                // Build the signed representation before replacing the writable
                // stack, preserving its page contents while adding the
                // validated author identity and generation.
                var writtenBook = ItemTypes.WRITTEN_BOOK.createItemStack();
                writtenBook.setTitle(packet.getTitle());
                writtenBook.setAuthor(packet.getAuthor());
                writtenBook.setXuid(packet.getXuid());
                writtenBook.setPages(book.getPages());
                writtenBook.setGeneration(WrittenBookGeneration.ORIGINAL_GENERATION);
                entity.setItemInHand(writtenBook);
            }
        }

        entity.notifyItemInHandChange();
    }

    /**
     * Returns the book edit packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BOOK_EDIT;
    }
}
