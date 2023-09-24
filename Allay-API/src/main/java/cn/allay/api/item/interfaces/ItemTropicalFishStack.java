package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishStack extends ItemStack {
    ItemType<ItemTropicalFishStack> TROPICAL_FISH_TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishStack.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH)
            .build();
}
