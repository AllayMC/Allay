package cn.allay.api.item.interfaces.fireworkstar;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkStarStack extends ItemStack {
    ItemType<ItemFireworkStarStack> FIREWORK_STAR_TYPE = ItemTypeBuilder
            .builder(ItemFireworkStarStack.class)
            .vanillaItem(VanillaItemId.FIREWORK_STAR)
            .build();
}
