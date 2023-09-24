package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledDeepslateStack extends ItemStack {
    ItemType<ItemChiseledDeepslateStack> CHISELED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemChiseledDeepslateStack.class)
            .vanillaItem(VanillaItemId.CHISELED_DEEPSLATE)
            .build();
}
