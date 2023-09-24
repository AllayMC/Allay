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
public interface BlockCarvedPumpkinBehavior extends BlockBehavior {
    BlockType<BlockCarvedPumpkinBehavior> CARVED_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockCarvedPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
}
