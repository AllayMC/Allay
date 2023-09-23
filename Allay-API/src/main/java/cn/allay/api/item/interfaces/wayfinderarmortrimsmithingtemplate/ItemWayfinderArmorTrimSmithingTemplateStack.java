package cn.allay.api.item.interfaces.wayfinderarmortrimsmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWayfinderArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemWayfinderArmorTrimSmithingTemplateStack> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemWayfinderArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
