package cn.allay.api.item.interfaces.deepslatebrickdoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickDoubleSlabStack extends ItemStack {
    ItemType<ItemDeepslateBrickDoubleSlabStack> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBrickDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_DOUBLE_SLAB)
            .build();
}
