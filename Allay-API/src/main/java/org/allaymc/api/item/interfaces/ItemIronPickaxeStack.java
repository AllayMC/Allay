package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronPickaxeStack extends ItemStack {
  ItemType<ItemIronPickaxeStack> IRON_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemIronPickaxeStack.class)
          .vanillaItem(VanillaItemId.IRON_PICKAXE)
          .build();
}
