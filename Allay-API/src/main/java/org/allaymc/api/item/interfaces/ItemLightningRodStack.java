package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightningRodStack extends ItemStack {
  ItemType<ItemLightningRodStack> LIGHTNING_ROD_TYPE = ItemTypeBuilder
          .builder(ItemLightningRodStack.class)
          .vanillaItem(VanillaItemId.LIGHTNING_ROD)
          .build();
}
