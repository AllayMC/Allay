package cn.allay.api.block.interfaces.oxidizedcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperBehavior extends BlockBehavior {
    BlockType<BlockOxidizedCopperBehavior> OXIDIZED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER)
            .build();
}
