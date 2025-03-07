package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeOptionalAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * @author IWareQ
 */
@Slf4j
public class CraftRecipeOptionalActionProcessor implements ContainerActionProcessor<CraftRecipeOptionalAction> {
    public static final String FILTER_STRINGS_DATA_KEY = "filterStrings";

    @Override
    public ActionResponse handle(CraftRecipeOptionalAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(FullContainerType.ANVIL);
        if (container == null) {
            log.warn("Received a CraftRecipeOptionalAction without an opened container");
            return error();
        }

        var filterStrings = (String[]) dataPool.get(FILTER_STRINGS_DATA_KEY);
        if (filterStrings == null) {
            log.warn("filterStrings not found in the data pool");
            return error();
        }

        if (filterStrings.length < action.getFilteredStringIndex()) {
            log.warn("filterStrings length does not match action.getFilteredStringIndex()");
            return error();
        }

        var inputItem = container.getInput();
        if (inputItem.isAir()) {
            log.warn("Input item is empty");
            return error();
        }

//        var material = container.getMaterial();
        // TODO: when RepairCost parsed from endstone


        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL;
    }
}
