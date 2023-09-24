package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHorseArmorStack extends ItemStack {
    ItemType<ItemGoldenHorseArmorStack> GOLDEN_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemGoldenHorseArmorStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_HORSE_ARMOR)
            .build();
}
