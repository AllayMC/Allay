package cn.allay.api.item.interfaces.enchantedgoldenapple;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantedGoldenAppleStack extends ItemStack {
    ItemType<ItemEnchantedGoldenAppleStack> ENCHANTED_GOLDEN_APPLE_TYPE = ItemTypeBuilder
            .builder(ItemEnchantedGoldenAppleStack.class)
            .vanillaItem(VanillaItemId.ENCHANTED_GOLDEN_APPLE)
            .build();
}
