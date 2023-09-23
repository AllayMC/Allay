package cn.allay.api.block.interfaces.sprucewallsign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceWallSignBehavior extends BlockBehavior {
    BlockType<BlockSpruceWallSignBehavior> SPRUCE_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockSpruceWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
