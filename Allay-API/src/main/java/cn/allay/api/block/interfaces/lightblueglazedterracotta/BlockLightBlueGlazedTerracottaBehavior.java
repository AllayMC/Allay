package cn.allay.api.block.interfaces.lightblueglazedterracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueGlazedTerracottaBehavior extends BlockBehavior {
    BlockType<BlockLightBlueGlazedTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
