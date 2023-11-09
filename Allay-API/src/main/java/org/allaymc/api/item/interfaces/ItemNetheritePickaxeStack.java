package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheritePickaxeStack extends ItemStack {
  ItemType<ItemNetheritePickaxeStack> NETHERITE_PICKAXE_TYPE = ItemTypeBuilder
          .builder(ItemNetheritePickaxeStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_PICKAXE)
          .build();
}
