package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowLichenStack extends ItemStack {
  ItemType<ItemGlowLichenStack> GLOW_LICHEN_TYPE = ItemTypeBuilder
          .builder(ItemGlowLichenStack.class)
          .vanillaItem(VanillaItemId.GLOW_LICHEN)
          .build();
}
