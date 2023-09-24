package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesBodyWithBerriesStack extends ItemStack {
    ItemType<ItemCaveVinesBodyWithBerriesStack> CAVE_VINES_BODY_WITH_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesBodyWithBerriesStack.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_BODY_WITH_BERRIES)
            .build();
}
