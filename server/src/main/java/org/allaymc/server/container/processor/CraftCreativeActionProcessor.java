package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * {@code CraftCreativeActionProcessor} stages a full creative catalog stack in the
 * player's created-output container. Creative and spectator players must have their
 * inventory open; later transfer actions move the staged stack, while this action
 * contributes no successful response entry.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftCreativeActionProcessor implements ContainerActionProcessor<CraftCreativeAction> {
    /**
     * Resolves the one-based creative item ID for an opened creative or spectator
     * inventory and stages a full-size copy with a new network ID. Rejected mode, absent
     * inventory, or a {@code null} catalog stack returns an error; success returns {@code null}
     * after the output assignment.
     */
    @Override
    public ActionResponse handle(CraftCreativeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var entity = player.getControlledEntity();
        if (entity.getGameMode() != GameMode.CREATIVE && entity.getGameMode() != GameMode.SPECTATOR) {
            return error();
        }

        if (player.getOpenedContainer(ContainerTypes.INVENTORY) == null) {
            log.warn("Player {} tried to take creative item without opening inventory", player.getOriginName());
            return error();
        }

        // Creative network IDs start at one, while the catalog index starts at zero;
        // subtract one before resolving the requested item entry.
        var item = Registries.CREATIVE_ITEMS.getEntryByIndex(action.creativeItemNetworkId() - 1).itemStack();
        if (item == null) {
            log.warn("Unknown creative item network id: {}", action.creativeItemNetworkId() - 1);
            return error();
        }

        item = item.copy(true);
        item.setCount(item.getItemType().getItemData().maxStackSize());
        entity.getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, item, false);
        // Later transfer actions report the destination slot; this staging action
        // contributes no slot response for the created-output container itself.
        return null;
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * CraftCreativeActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }
}
