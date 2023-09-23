package cn.allay.api.item.interfaces.rabbithide;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitHideStack extends ItemStack {
    ItemType<ItemRabbitHideStack> RABBIT_HIDE_TYPE = ItemTypeBuilder
            .builder(ItemRabbitHideStack.class)
            .vanillaItem(VanillaItemId.RABBIT_HIDE)
            .build();
}
