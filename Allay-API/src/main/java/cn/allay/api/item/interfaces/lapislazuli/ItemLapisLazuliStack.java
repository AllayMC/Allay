package cn.allay.api.item.interfaces.lapislazuli;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLapisLazuliStack extends ItemStack {
    ItemType<ItemLapisLazuliStack> LAPIS_LAZULI_TYPE = ItemTypeBuilder
            .builder(ItemLapisLazuliStack.class)
            .vanillaItem(VanillaItemId.LAPIS_LAZULI)
            .build();
}
