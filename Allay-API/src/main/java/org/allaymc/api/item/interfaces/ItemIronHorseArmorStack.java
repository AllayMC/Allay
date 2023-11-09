package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHorseArmorStack extends ItemStack {
  ItemType<ItemIronHorseArmorStack> IRON_HORSE_ARMOR_TYPE = ItemTypeBuilder
          .builder(ItemIronHorseArmorStack.class)
          .vanillaItem(VanillaItemId.IRON_HORSE_ARMOR)
          .build();
}
