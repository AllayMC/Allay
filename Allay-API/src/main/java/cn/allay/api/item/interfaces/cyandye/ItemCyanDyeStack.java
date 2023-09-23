package cn.allay.api.item.interfaces.cyandye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanDyeStack extends ItemStack {
    ItemType<ItemCyanDyeStack> CYAN_DYE_TYPE = ItemTypeBuilder
            .builder(ItemCyanDyeStack.class)
            .vanillaItem(VanillaItemId.CYAN_DYE)
            .build();
}
