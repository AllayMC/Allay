package cn.allay.api.block.interfaces.tripwirehook;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTripwireHookBehavior extends BlockBehavior {
    BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
            .builder(BlockTripwireHookBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
}
