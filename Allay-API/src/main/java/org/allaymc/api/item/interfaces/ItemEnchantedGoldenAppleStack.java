package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
