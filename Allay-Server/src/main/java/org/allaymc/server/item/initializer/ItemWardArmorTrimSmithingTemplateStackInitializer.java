package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWardArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWardArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.WARD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemWardArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
