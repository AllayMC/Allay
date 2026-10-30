package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TakeAction;

/**
 * {@code TakeActionProcessor} selects the {@code TAKE} action type for the shared
 * item-transfer implementation. Its inherited handler resolves endpoints, validates
 * placement and stack identities, applies the move, and returns changed-slot data for
 * request-level response aggregation.
 *
 * @author daoge_cmd
 */
public class TakeActionProcessor extends TransferItemActionProcessor<TakeAction> {
    /**
     * Returns the {@code TAKE} type dispatched to the shared transfer handler.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.TAKE;
    }
}
