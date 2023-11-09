package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowStickStack extends ItemStack {
  ItemType<ItemGlowStickStack> GLOW_STICK_TYPE = ItemTypeBuilder
          .builder(ItemGlowStickStack.class)
          .vanillaItem(VanillaItemId.GLOW_STICK)
          .build();
}
