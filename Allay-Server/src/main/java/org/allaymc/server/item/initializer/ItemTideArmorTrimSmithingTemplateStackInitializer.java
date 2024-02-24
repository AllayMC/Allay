package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTideArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTideArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemTideArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
