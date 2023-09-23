package cn.allay.api.item.interfaces.driedkelp;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDriedKelpStack extends ItemStack {
    ItemType<ItemDriedKelpStack> DRIED_KELP_TYPE = ItemTypeBuilder
            .builder(ItemDriedKelpStack.class)
            .vanillaItem(VanillaItemId.DRIED_KELP)
            .build();
}
