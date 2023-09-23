package cn.allay.api.item.interfaces.beehive;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeehiveStack extends ItemStack {
    ItemType<ItemBeehiveStack> BEEHIVE_TYPE = ItemTypeBuilder
            .builder(ItemBeehiveStack.class)
            .vanillaItem(VanillaItemId.BEEHIVE)
            .build();
}
