package org.allaymc.server.block.impl.stairs;

import org.allaymc.api.block.interfaces.stairs.*;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStairsBehaviorImpl extends BlockBehaviorImpl implements
        BlockAcaciaStairsBehavior, BlockAndesiteStairsBehavior, BlockBambooMosaicStairsBehavior, BlockBambooStairsBehavior, BlockBirchStairsBehavior,
        BlockBlackstoneStairsBehavior, BlockBrickStairsBehavior, BlockCherryStairsBehavior, BlockCobbledDeepslateStairsBehavior, BlockCrimsonStairsBehavior,
        BlockCutCopperStairsBehavior, BlockDarkOakStairsBehavior, BlockDarkPrismarineStairsBehavior, BlockDeepslateBrickStairsBehavior, BlockDeepslateTileStairsBehavior,
        BlockDioriteStairsBehavior, BlockEndBrickStairsBehavior, BlockExposedCutCopperStairsBehavior, BlockGraniteStairsBehavior, BlockJungleStairsBehavior,
        BlockMangroveStairsBehavior, BlockMossyCobblestoneStairsBehavior, BlockMossyStoneBrickStairsBehavior, BlockMudBrickStairsBehavior, BlockNetherBrickStairsBehavior,
        BlockNormalStoneStairsBehavior, BlockOakStairsBehavior, BlockOxidizedCutCopperStairsBehavior, BlockPolishedAndesiteStairsBehavior, BlockPolishedBlackstoneBrickStairsBehavior,
        BlockPolishedBlackstoneStairsBehavior, BlockPolishedDeepslateStairsBehavior, BlockPolishedDioriteStairsBehavior, BlockPolishedGraniteStairsBehavior,
        BlockPolishedTuffStairsBehavior, BlockPrismarineBricksStairsBehavior, BlockPrismarineStairsBehavior, BlockPurpurStairsBehavior, BlockQuartzStairsBehavior,
        BlockRedNetherBrickStairsBehavior, BlockRedSandstoneStairsBehavior, BlockSandstoneStairsBehavior, BlockSmoothQuartzStairsBehavior, BlockSmoothRedSandstoneStairsBehavior,
        BlockSmoothSandstoneStairsBehavior, BlockSpruceStairsBehavior, BlockStoneBrickStairsBehavior, BlockStoneStairsBehavior, BlockTuffBrickStairsBehavior,
        BlockTuffStairsBehavior, BlockWarpedStairsBehavior, BlockWaxedCutCopperStairsBehavior, BlockWaxedExposedCutCopperStairsBehavior, BlockWaxedOxidizedCutCopperStairsBehavior,
        BlockWaxedWeatheredCutCopperStairsBehavior, BlockWeatheredCutCopperStairsBehavior {
    public BlockStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
