package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTideArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemTideArmorTrimSmithingTemplateStack> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemTideArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
