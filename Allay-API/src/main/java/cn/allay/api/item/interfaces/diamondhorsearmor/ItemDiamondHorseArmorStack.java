package cn.allay.api.item.interfaces.diamondhorsearmor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHorseArmorStack extends ItemStack {
    ItemType<ItemDiamondHorseArmorStack> DIAMOND_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemDiamondHorseArmorStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_HORSE_ARMOR)
            .build();
}
