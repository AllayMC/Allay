package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
