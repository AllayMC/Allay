package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDuneArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDuneArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemDuneArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
