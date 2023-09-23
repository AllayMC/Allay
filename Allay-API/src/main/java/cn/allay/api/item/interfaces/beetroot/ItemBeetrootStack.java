package cn.allay.api.item.interfaces.beetroot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootStack extends ItemStack {
    ItemType<ItemBeetrootStack> BEETROOT_TYPE = ItemTypeBuilder
            .builder(ItemBeetrootStack.class)
            .vanillaItem(VanillaItemId.BEETROOT)
            .build();
}
