package cn.allay.api.block.interfaces.mangrovestandingsign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveStandingSignBehavior extends BlockBehavior {
    BlockType<BlockMangroveStandingSignBehavior> MANGROVE_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockMangroveStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
}
