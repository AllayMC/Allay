package cn.allay.api.block.interfaces.scaffolding;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockScaffoldingBehavior extends BlockBehavior {
    BlockType<BlockScaffoldingBehavior> SCAFFOLDING_TYPE = BlockTypeBuilder
            .builder(BlockScaffoldingBehavior.class)
            .vanillaBlock(VanillaBlockId.SCAFFOLDING)
            .setProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
            .build();
}
