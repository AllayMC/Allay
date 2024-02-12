package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTrialSpawnerBehavior extends BlockBehavior {
  BlockType<BlockTrialSpawnerBehavior> TRIAL_SPAWNER_TYPE = BlockTypeBuilder
          .builder(BlockTrialSpawnerBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIAL_SPAWNER)
          .setProperties(VanillaBlockPropertyTypes.TRIAL_SPAWNER_STATE)
          .build();
}
