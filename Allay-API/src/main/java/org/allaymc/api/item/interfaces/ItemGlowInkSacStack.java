package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowInkSacStack extends ItemStack {
  ItemType<ItemGlowInkSacStack> GLOW_INK_SAC_TYPE = ItemTypeBuilder
          .builder(ItemGlowInkSacStack.class)
          .vanillaItem(VanillaItemId.GLOW_INK_SAC)
          .build();
}
