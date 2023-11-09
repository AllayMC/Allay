package org.allaymc.api.item.interfaces.sapling;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSaplingStack extends ItemStack {
  ItemType<ItemBambooSaplingStack> BAMBOO_SAPLING_TYPE = ItemTypeBuilder
          .builder(ItemBambooSaplingStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_SAPLING)
          .build();
}
