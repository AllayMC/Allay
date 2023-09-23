package cn.allay.api.item.interfaces.enderpearl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderPearlStack extends ItemStack {
    ItemType<ItemEnderPearlStack> ENDER_PEARL_TYPE = ItemTypeBuilder
            .builder(ItemEnderPearlStack.class)
            .vanillaItem(VanillaItemId.ENDER_PEARL)
            .build();
}
