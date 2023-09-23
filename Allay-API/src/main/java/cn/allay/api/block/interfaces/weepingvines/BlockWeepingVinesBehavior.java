package cn.allay.api.block.interfaces.weepingvines;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeepingVinesBehavior extends BlockBehavior {
    BlockType<BlockWeepingVinesBehavior> WEEPING_VINES_TYPE = BlockTypeBuilder
            .builder(BlockWeepingVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.WEEPING_VINES)
            .setProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
            .build();
}
