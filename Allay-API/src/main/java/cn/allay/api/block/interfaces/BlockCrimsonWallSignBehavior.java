package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonWallSignBehavior extends BlockBehavior {
    BlockType<BlockCrimsonWallSignBehavior> CRIMSON_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
