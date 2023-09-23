package cn.allay.api.block.interfaces.litpumpkin;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitPumpkinBehavior extends BlockBehavior {
    BlockType<BlockLitPumpkinBehavior> LIT_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockLitPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
}
