package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesHeadWithBerriesStack extends ItemStack {
    ItemType<ItemCaveVinesHeadWithBerriesStack> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesHeadWithBerriesStack.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_HEAD_WITH_BERRIES)
            .build();
}
