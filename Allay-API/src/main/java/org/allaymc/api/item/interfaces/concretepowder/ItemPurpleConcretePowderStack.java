package org.allaymc.api.item.interfaces.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleConcretePowderStack extends ItemStack {
  ItemType<ItemPurpleConcretePowderStack> PURPLE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemPurpleConcretePowderStack.class)
          .vanillaItem(VanillaItemId.PURPLE_CONCRETE_POWDER)
          .build();
}
