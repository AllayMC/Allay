package cn.allay.api.block.interfaces.pumpkin;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPumpkinBehavior extends BlockBehavior {
    BlockType<BlockPumpkinBehavior> PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
}
