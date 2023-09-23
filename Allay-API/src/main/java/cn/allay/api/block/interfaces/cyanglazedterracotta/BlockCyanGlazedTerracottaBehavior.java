package cn.allay.api.block.interfaces.cyanglazedterracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanGlazedTerracottaBehavior extends BlockBehavior {
    BlockType<BlockCyanGlazedTerracottaBehavior> CYAN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockCyanGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
