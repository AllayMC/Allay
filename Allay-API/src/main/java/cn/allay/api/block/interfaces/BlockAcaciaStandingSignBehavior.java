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
public interface BlockAcaciaStandingSignBehavior extends BlockBehavior {
    BlockType<BlockAcaciaStandingSignBehavior> ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
}
