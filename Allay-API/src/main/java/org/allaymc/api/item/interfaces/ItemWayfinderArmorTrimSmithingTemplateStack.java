package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWayfinderArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemWayfinderArmorTrimSmithingTemplateStack> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemWayfinderArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
