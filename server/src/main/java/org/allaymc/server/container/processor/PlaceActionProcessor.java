package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.PlaceAction;

/**
 * {@code PlaceActionProcessor} selects the {@code PLACE} action type for the shared
 * item-transfer implementation. Its inherited handler resolves endpoints, validates
 * placement and stack identities, applies the move, and returns changed-slot data for
 * request-level response aggregation.
 *
 * @author daoge_cmd
 */
public class PlaceActionProcessor extends TransferItemActionProcessor<PlaceAction> {
    /**
     * Returns the {@code PLACE} type dispatched to the shared transfer handler.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.PLACE;
    }
}
