package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHostArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemHostArmorTrimSmithingTemplateStack> HOST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemHostArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
