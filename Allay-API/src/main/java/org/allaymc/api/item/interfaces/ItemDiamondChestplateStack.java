package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondChestplateStack extends ItemStack {
  ItemType<ItemDiamondChestplateStack> DIAMOND_CHESTPLATE_TYPE = ItemTypeBuilder
          .builder(ItemDiamondChestplateStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_CHESTPLATE)
          .build();
}