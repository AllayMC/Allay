package cn.allay.api.item.interfaces.reddye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedDyeStack extends ItemStack {
    ItemType<ItemRedDyeStack> RED_DYE_TYPE = ItemTypeBuilder
            .builder(ItemRedDyeStack.class)
            .vanillaItem(VanillaItemId.RED_DYE)
            .build();
}
