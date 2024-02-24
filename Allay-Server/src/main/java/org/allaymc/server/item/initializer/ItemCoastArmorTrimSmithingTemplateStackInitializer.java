package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCoastArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoastArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.COAST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemCoastArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
