package cn.allay.api.item.interfaces.wanderingtraderspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWanderingTraderSpawnEggStack extends ItemStack {
    ItemType<ItemWanderingTraderSpawnEggStack> WANDERING_TRADER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWanderingTraderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG)
            .build();
}
