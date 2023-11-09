package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
