package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSprucePlanksStack extends ItemStack {
  ItemType<ItemSprucePlanksStack> SPRUCE_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemSprucePlanksStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_PLANKS)
          .build();
}
