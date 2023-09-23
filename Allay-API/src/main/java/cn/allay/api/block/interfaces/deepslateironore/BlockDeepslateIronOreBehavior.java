package cn.allay.api.block.interfaces.deepslateironore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateIronOreBehavior extends BlockBehavior {
    BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateIronOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE)
            .build();
}
