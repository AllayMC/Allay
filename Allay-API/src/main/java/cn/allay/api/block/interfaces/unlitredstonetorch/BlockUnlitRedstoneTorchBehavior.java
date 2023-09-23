package cn.allay.api.block.interfaces.unlitredstonetorch;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockUnlitRedstoneTorchBehavior extends BlockBehavior {
    BlockType<BlockUnlitRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockUnlitRedstoneTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .build();
}
