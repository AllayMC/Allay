package cn.allay.api.block.interfaces.lightningrod;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightningRodBehavior extends BlockBehavior {
    BlockType<BlockLightningRodBehavior> LIGHTNING_ROD_TYPE = BlockTypeBuilder
            .builder(BlockLightningRodBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
