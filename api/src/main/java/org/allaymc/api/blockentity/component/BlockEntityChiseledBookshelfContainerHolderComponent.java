package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityChiseledBookshelfContainerHolderComponent} exposes a
 * chiseled bookshelf's inventory and last-interaction slot. The slot uses
 * Bedrock's one-based interaction encoding, distinct from the zero-based
 * container indices; zero identifies the state before any recorded slot
 * interaction.
 *
 * @author daoge_cmd
 */
public interface BlockEntityChiseledBookshelfContainerHolderComponent extends BlockEntityContainerHolderComponent {
    /**
     * Returns the last interacted slot using Bedrock's bookshelf encoding. The
     * result is {@code 0} before an interaction has been recorded, or
     * {@code 1..6} for the corresponding zero-based container slot plus one.
     */
    int getLastInteractedSlot();
}
