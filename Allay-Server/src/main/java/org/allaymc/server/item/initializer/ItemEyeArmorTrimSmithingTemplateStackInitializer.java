package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEyeArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEyeArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.EYE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemEyeArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.EYE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
