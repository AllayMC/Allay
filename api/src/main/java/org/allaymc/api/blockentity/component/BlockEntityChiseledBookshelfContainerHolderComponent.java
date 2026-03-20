package org.allaymc.api.blockentity.component;

/**
 * @author daoge_cmd
 */
public interface BlockEntityChiseledBookshelfContainerHolderComponent extends BlockEntityContainerHolderComponent {
    /**
     * Gets the most recently interacted slot using the Bedrock chiseled bookshelf encoding.
     *
     * @return {@code 0} if the bookshelf has never been interacted with, otherwise {@code 1..6}
     * for the last interacted slot
     */
    int getLastInteractedSlot();
}
