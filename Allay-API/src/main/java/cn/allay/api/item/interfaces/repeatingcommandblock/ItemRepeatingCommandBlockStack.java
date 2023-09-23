package cn.allay.api.item.interfaces.repeatingcommandblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeatingCommandBlockStack extends ItemStack {
    ItemType<ItemRepeatingCommandBlockStack> REPEATING_COMMAND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRepeatingCommandBlockStack.class)
            .vanillaItem(VanillaItemId.REPEATING_COMMAND_BLOCK)
            .build();
}
