package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoastArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemCoastArmorTrimSmithingTemplateStack> COAST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemCoastArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
