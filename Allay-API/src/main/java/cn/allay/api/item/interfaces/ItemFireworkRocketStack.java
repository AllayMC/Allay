package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkRocketStack extends ItemStack {
    ItemType<ItemFireworkRocketStack> FIREWORK_ROCKET_TYPE = ItemTypeBuilder
            .builder(ItemFireworkRocketStack.class)
            .vanillaItem(VanillaItemId.FIREWORK_ROCKET)
            .build();
}
