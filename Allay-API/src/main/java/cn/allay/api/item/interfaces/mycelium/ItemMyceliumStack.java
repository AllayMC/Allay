package cn.allay.api.item.interfaces.mycelium;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMyceliumStack extends ItemStack {
    ItemType<ItemMyceliumStack> MYCELIUM_TYPE = ItemTypeBuilder
            .builder(ItemMyceliumStack.class)
            .vanillaItem(VanillaItemId.MYCELIUM)
            .build();
}
