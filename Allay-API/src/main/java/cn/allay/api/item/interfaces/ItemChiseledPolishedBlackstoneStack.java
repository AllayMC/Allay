package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledPolishedBlackstoneStack extends ItemStack {
    ItemType<ItemChiseledPolishedBlackstoneStack> CHISELED_POLISHED_BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemChiseledPolishedBlackstoneStack.class)
            .vanillaItem(VanillaItemId.CHISELED_POLISHED_BLACKSTONE)
            .build();
}
