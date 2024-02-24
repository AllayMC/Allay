package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSnoutArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnoutArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemSnoutArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
