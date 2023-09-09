package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinBruteSpawnEggStack extends ItemStack {
    ItemType<ItemPiglinBruteSpawnEggStack> PIGLIN_BRUTE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPiglinBruteSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG)
            .build();
}
