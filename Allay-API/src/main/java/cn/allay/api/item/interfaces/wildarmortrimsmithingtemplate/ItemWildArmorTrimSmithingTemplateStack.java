package cn.allay.api.item.interfaces.wildarmortrimsmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWildArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemWildArmorTrimSmithingTemplateStack> WILD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemWildArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
