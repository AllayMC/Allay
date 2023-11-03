package cn.allay.api.container.processor;

import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.item.registry.CreativeItemRegistry;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftCreativeActionProcessor implements ContainerActionProcessor<CraftCreativeAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }

    @Override
    public ActionResponse handle(CraftCreativeAction action, EntityPlayer player) {
        var item = CreativeItemRegistry.getRegistry().get(action.getCreativeItemNetworkId() - 1);
        if (item == null) {
            log.warn("Unknown creative item network id: {}", action.getCreativeItemNetworkId() - 1);
            return error();
        }
        item = item.copy(true);
        item.setCount(item.getItemAttributes().maxStackSize());
        player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(0, item);
        //从创造物品栏拿东西不需要响应
        return null;
    }
}
