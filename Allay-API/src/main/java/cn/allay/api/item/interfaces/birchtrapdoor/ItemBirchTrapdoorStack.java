package cn.allay.api.item.interfaces.birchtrapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchTrapdoorStack extends ItemStack {
    ItemType<ItemBirchTrapdoorStack> BIRCH_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemBirchTrapdoorStack.class)
            .vanillaItem(VanillaItemId.BIRCH_TRAPDOOR)
            .build();
}
