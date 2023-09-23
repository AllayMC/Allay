package cn.allay.api.block.interfaces.smallamethystbud;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmallAmethystBudBehavior extends BlockBehavior {
    BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockSmallAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
