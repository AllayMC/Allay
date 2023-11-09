package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowBerriesStack extends ItemStack {
  ItemType<ItemGlowBerriesStack> GLOW_BERRIES_TYPE = ItemTypeBuilder
          .builder(ItemGlowBerriesStack.class)
          .vanillaItem(VanillaItemId.GLOW_BERRIES)
          .build();
}
