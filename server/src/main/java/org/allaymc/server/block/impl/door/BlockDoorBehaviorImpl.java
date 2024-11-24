package org.allaymc.server.block.impl.door;

import org.allaymc.api.block.interfaces.door.*;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDoorBehaviorImpl extends BlockBehaviorImpl implements
        BlockAcaciaDoorBehavior, BlockBambooDoorBehavior, BlockBirchDoorBehavior, BlockCherryDoorBehavior,
        BlockCopperDoorBehavior, BlockCrimsonDoorBehavior, BlockDarkOakDoorBehavior, BlockExposedCopperDoorBehavior,
        BlockIronDoorBehavior, BlockJungleDoorBehavior, BlockMangroveDoorBehavior, BlockOxidizedCopperDoorBehavior,
        BlockSpruceDoorBehavior, BlockWarpedDoorBehavior, BlockWaxedCopperDoorBehavior, BlockWaxedExposedCopperDoorBehavior,
        BlockWaxedOxidizedCopperDoorBehavior, BlockWaxedWeatheredCopperDoorBehavior, BlockWeatheredCopperDoorBehavior,
        BlockWoodenDoorBehavior {
    public BlockDoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
