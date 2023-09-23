package cn.allay.api.item.interfaces.irontrapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronTrapdoorStack extends ItemStack {
    ItemType<ItemIronTrapdoorStack> IRON_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemIronTrapdoorStack.class)
            .vanillaItem(VanillaItemId.IRON_TRAPDOOR)
            .build();
}
