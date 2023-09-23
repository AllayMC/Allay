package cn.allay.api.item.interfaces.composter;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemComposterStack extends ItemStack {
    ItemType<ItemComposterStack> COMPOSTER_TYPE = ItemTypeBuilder
            .builder(ItemComposterStack.class)
            .vanillaItem(VanillaItemId.COMPOSTER)
            .build();
}
