package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSplashPotionStack extends ItemStack {
    ItemType<ItemSplashPotionStack> SPLASH_POTION_TYPE = ItemTypeBuilder
            .builder(ItemSplashPotionStack.class)
            .vanillaItem(VanillaItemId.SPLASH_POTION)
            .build();
}
