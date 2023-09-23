package cn.allay.api.item.interfaces.ironhoe;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHoeStack extends ItemStack {
    ItemType<ItemIronHoeStack> IRON_HOE_TYPE = ItemTypeBuilder
            .builder(ItemIronHoeStack.class)
            .vanillaItem(VanillaItemId.IRON_HOE)
            .build();
}
