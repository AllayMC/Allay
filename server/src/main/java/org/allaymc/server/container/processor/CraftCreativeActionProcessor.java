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
 * @author daoge_cmd
 */
@Slf4j
public class CraftCreativeActionProcessor implements ContainerActionProcessor<CraftCreativeAction> {
    @Override
    public ActionResponse handle(CraftCreativeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var entity = player.getControlledEntity();
        if (entity.getGameMode() != GameMode.CREATIVE && entity.getGameMode() != GameMode.SPECTATOR) {
            return error();
        }

        // NOTICE: 0 is not indexed by the client for items
        var item = Registries.CREATIVE_ITEMS.getEntryByIndex(action.getCreativeItemNetworkId() - 1).itemStack();
        if (item == null) {
            log.warn("Unknown creative item network id: {}", action.getCreativeItemNetworkId() - 1);
            return error();
        }

        item = item.copy(true);
        item.setCount(item.getItemType().getItemData().maxStackSize());
        entity.getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, item, false);
        // Taking items from the creative inventory does not require a response
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }
}
