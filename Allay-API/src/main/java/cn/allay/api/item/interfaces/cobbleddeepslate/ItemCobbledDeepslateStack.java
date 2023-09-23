package cn.allay.api.item.interfaces.cobbleddeepslate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateStack extends ItemStack {
    ItemType<ItemCobbledDeepslateStack> COBBLED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE)
            .build();
}
