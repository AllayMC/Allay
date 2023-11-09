package cn.allay.api.container.processor;

import cn.allay.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

/**
 * Allay Project 2023/7/29
 *
 * @author daoge_cmd
 */
public class CraftResultsDeprecatedActionProcessor implements ContainerActionProcessor<CraftResultsDeprecatedAction> {

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;
    }

    @Override
    public ActionResponse handle(CraftResultsDeprecatedAction action, EntityPlayer player) {
        return null;
    }
}
