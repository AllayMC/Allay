package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherRoseStack extends ItemStack {
    ItemType<ItemWitherRoseStack> WITHER_ROSE_TYPE = ItemTypeBuilder
            .builder(ItemWitherRoseStack.class)
            .vanillaItem(VanillaItemId.WITHER_ROSE)
            .build();
}
