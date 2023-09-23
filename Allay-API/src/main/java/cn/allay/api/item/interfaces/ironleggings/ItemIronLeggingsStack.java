package cn.allay.api.item.interfaces.ironleggings;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronLeggingsStack extends ItemStack {
    ItemType<ItemIronLeggingsStack> IRON_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemIronLeggingsStack.class)
            .vanillaItem(VanillaItemId.IRON_LEGGINGS)
            .build();
}
