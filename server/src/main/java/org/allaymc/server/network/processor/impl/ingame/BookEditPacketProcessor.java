package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerBookEditEvent;
import org.allaymc.api.item.data.WrittenBookGeneration;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BookEditPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BookEditPacketProcessor extends PacketProcessor<BookEditPacket> {
    @Override
    public void handleSync(EntityPlayer player, BookEditPacket packet, long receiveTime) {
        if (player.getHandSlot() != packet.getInventorySlot()) {
            log.warn("Invalid inventory slot: {}, should be {}", packet.getInventorySlot(), player.getHandSlot());
            return;
        }

        if (!(player.getItemInHand() instanceof ItemWritableBookStack book)) {
            log.warn("Inventory slot {} does not contain a writable book", player.getHandSlot());
            return;
        }

        var page = packet.getPageNumber();
        if (page >= 50 || page < 0) {
            log.warn("Page number {} is out of bounds", page);
            return;
        }

        var text = packet.getText();
        if (text != null && text.getBytes().length > 256) {
            log.warn("Text can not be longer than 256 bytes");
            return;
        }

        var event = new PlayerBookEditEvent(player, book, packet.getAction());
        if (!event.call()) {
            return;
        }

        switch (packet.getAction()) {
            case REPLACE_PAGE -> book.setPage(page, text);
            case ADD_PAGE -> {
                if (book.getPageCount() >= 50) {
                    log.warn("Unable to add page beyond 50");
                    return;
                }

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
                    // We break here instead of returning an error because the client can be a page or two ahead in the UI then
                    // the actual pages representation server side. The client still sends the deletion indexes.
                    break;
                }

                book.removePage(page);
            }
            case SWAP_PAGES -> {
                var secondPage = packet.getSecondaryPageNumber();
                if (secondPage >= 50) {
                    log.warn("Second page number out of bounds");
                    return;
                }

                if (book.getPageText(page) == null || book.getPageText(secondPage) == null) {
                    // We break here instead of returning an error because the client can try to swap pages that don't exist.
                    // This happens as a result of the client being a page or two ahead in the UI then the actual pages
                    // representation server side. The client still sends the swap indexes.
                    break;
                }

                book.swapPage(page, secondPage);
            }
            case SIGN_BOOK -> {
                if (!packet.getXuid().equals(player.getLoginData().getXuid())) {
                    log.warn("Player xuid mismatch! Expected: {}, Actual: {}", player.getLoginData().getXuid(), packet.getXuid());
                    return;
                }

                var writtenBook = ItemTypes.WRITTEN_BOOK.createItemStack();
                writtenBook.setTitle(packet.getTitle());
                writtenBook.setAuthor(packet.getAuthor());
                writtenBook.setXuid(packet.getXuid());
                writtenBook.setPages(book.getPages());
                writtenBook.setGeneration(WrittenBookGeneration.ORIGINAL_GENERATION);
                player.setItemInHand(writtenBook);
            }
        }

        player.notifyItemInHandChange();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BOOK_EDIT;
    }
}
