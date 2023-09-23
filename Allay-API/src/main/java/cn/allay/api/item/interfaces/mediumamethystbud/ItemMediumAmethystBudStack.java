package cn.allay.api.item.interfaces.mediumamethystbud;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMediumAmethystBudStack extends ItemStack {
    ItemType<ItemMediumAmethystBudStack> MEDIUM_AMETHYST_BUD_TYPE = ItemTypeBuilder
            .builder(ItemMediumAmethystBudStack.class)
            .vanillaItem(VanillaItemId.MEDIUM_AMETHYST_BUD)
            .build();
}
