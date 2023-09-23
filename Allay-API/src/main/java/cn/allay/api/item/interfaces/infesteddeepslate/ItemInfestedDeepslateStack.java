package cn.allay.api.item.interfaces.infesteddeepslate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfestedDeepslateStack extends ItemStack {
    ItemType<ItemInfestedDeepslateStack> INFESTED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemInfestedDeepslateStack.class)
            .vanillaItem(VanillaItemId.INFESTED_DEEPSLATE)
            .build();
}
