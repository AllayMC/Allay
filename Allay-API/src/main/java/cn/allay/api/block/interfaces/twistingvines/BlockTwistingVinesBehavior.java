package cn.allay.api.block.interfaces.twistingvines;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTwistingVinesBehavior extends BlockBehavior {
    BlockType<BlockTwistingVinesBehavior> TWISTING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockTwistingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.TWISTING_VINES)
            .setProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
            .build();
}
