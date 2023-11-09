package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenPickaxeStack extends ItemStack {
  ItemType<ItemWoodenPickaxeStack> WOODEN_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemWoodenPickaxeStack.class)
          .vanillaItem(VanillaItemId.WOODEN_PICKAXE)
          .build();
}
