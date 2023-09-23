package cn.allay.api.item.interfaces.bambootrapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooTrapdoorStack extends ItemStack {
    ItemType<ItemBambooTrapdoorStack> BAMBOO_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemBambooTrapdoorStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_TRAPDOOR)
            .build();
}
