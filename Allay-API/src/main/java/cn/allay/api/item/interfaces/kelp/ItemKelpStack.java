package cn.allay.api.item.interfaces.kelp;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemKelpStack extends ItemStack {
    ItemType<ItemKelpStack> KELP_TYPE = ItemTypeBuilder
            .builder(ItemKelpStack.class)
            .vanillaItem(VanillaItemId.KELP)
            .build();
}
