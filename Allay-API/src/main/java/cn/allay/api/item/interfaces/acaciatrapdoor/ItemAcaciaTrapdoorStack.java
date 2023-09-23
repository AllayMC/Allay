package cn.allay.api.item.interfaces.acaciatrapdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaTrapdoorStack extends ItemStack {
    ItemType<ItemAcaciaTrapdoorStack> ACACIA_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaTrapdoorStack.class)
            .vanillaItem(VanillaItemId.ACACIA_TRAPDOOR)
            .build();
}
