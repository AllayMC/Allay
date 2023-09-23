package cn.allay.api.block.interfaces.purpleglazedterracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleGlazedTerracottaBehavior extends BlockBehavior {
    BlockType<BlockPurpleGlazedTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPurpleGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
