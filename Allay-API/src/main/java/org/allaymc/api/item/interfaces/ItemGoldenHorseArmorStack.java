package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
