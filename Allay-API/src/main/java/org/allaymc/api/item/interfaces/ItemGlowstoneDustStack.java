package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowstoneDustStack extends ItemStack {
  ItemType<ItemGlowstoneDustStack> GLOWSTONE_DUST_TYPE = ItemTypeBuilder
          .builder(ItemGlowstoneDustStack.class)
          .vanillaItem(VanillaItemId.GLOWSTONE_DUST)
          .build();
}
