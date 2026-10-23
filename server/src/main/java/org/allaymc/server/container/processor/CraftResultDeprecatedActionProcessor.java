package org.allaymc.server.container.processor;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * {@code CraftResultDeprecatedActionProcessor} accepts the legacy crafting-result marker
 * without applying item changes. The enclosing request processor observes the action
 * type to suppress a subsequent successful destroy response, while this handler
 * contributes no response entry.
 *
 * @author daoge_cmd
 */
public class CraftResultDeprecatedActionProcessor implements ContainerActionProcessor<CraftResultsDeprecatedAction> {
    /**
     * Accepts the legacy result marker and returns {@code null}. This handler changes no
     * container or request data; the enclosing request processor separately uses the
     * marker's type to control destroy-response suppression.
     */
    @Override
    public ActionResponse handle(CraftResultsDeprecatedAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        return null;
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * CraftResultDeprecatedActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;
    }
}
