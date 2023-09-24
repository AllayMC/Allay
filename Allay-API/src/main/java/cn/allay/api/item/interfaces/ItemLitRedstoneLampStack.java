package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitRedstoneLampStack extends ItemStack {
    ItemType<ItemLitRedstoneLampStack> LIT_REDSTONE_LAMP_TYPE = ItemTypeBuilder
            .builder(ItemLitRedstoneLampStack.class)
            .vanillaItem(VanillaItemId.LIT_REDSTONE_LAMP)
            .build();
}
