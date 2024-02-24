package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHostArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHostArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.HOST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemHostArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
