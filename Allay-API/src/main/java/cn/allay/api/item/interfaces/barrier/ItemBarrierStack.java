package cn.allay.api.item.interfaces.barrier;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrierStack extends ItemStack {
    ItemType<ItemBarrierStack> BARRIER_TYPE = ItemTypeBuilder
            .builder(ItemBarrierStack.class)
            .vanillaItem(VanillaItemId.BARRIER)
            .build();
}
