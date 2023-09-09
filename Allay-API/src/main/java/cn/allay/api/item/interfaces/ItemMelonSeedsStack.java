package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonSeedsStack extends ItemStack {
    ItemType<ItemMelonSeedsStack> MELON_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemMelonSeedsStack.class)
            .vanillaItem(VanillaItemId.MELON_SEEDS)
            .build();
}
