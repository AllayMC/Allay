package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateCopperOreStack extends ItemStack {
  ItemType<ItemDeepslateCopperOreStack> DEEPSLATE_COPPER_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateCopperOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_COPPER_ORE)
          .build();
}
