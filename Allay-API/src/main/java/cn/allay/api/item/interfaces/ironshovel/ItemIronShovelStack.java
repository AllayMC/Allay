package cn.allay.api.item.interfaces.ironshovel;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronShovelStack extends ItemStack {
    ItemType<ItemIronShovelStack> IRON_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemIronShovelStack.class)
            .vanillaItem(VanillaItemId.IRON_SHOVEL)
            .build();
}
