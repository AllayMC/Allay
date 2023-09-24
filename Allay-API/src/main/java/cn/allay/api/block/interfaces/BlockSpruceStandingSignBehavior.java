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
public interface BlockSpruceStandingSignBehavior extends BlockBehavior {
    BlockType<BlockSpruceStandingSignBehavior> SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockSpruceStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
}
