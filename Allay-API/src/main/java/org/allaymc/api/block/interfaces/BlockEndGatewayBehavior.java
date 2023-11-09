package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEndGatewayBehavior extends BlockBehavior {
  BlockType<BlockEndGatewayBehavior> END_GATEWAY_TYPE = BlockTypeBuilder
          .builder(BlockEndGatewayBehavior.class)
          .vanillaBlock(VanillaBlockId.END_GATEWAY)
          .build();
}
