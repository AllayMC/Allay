package cn.allay.api.block.interfaces.reinforceddeepslate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockReinforcedDeepslateBehavior extends BlockBehavior {
    BlockType<BlockReinforcedDeepslateBehavior> REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockReinforcedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE)
            .build();
}
