package cn.allay.api.item.interfaces.coastarmortrimsmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoastArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemCoastArmorTrimSmithingTemplateStack> COAST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemCoastArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
