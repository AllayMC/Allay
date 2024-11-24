package org.allaymc.server.block.impl.wood;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.block.interfaces.log.*;
import org.allaymc.api.block.interfaces.wood.*;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWoodBehaviorImpl extends BlockBehaviorImpl implements BlockAcaciaWoodBehavior, BlockBirchWoodBehavior, BlockCherryWoodBehavior, BlockDarkOakWoodBehavior, BlockJungleWoodBehavior, BlockMangroveWoodBehavior, BlockOakWoodBehavior, BlockSpruceWoodBehavior, BlockStrippedAcaciaWoodBehavior, BlockStrippedBirchWoodBehavior, BlockStrippedCherryWoodBehavior, BlockStrippedDarkOakWoodBehavior, BlockStrippedJungleWoodBehavior, BlockStrippedMangroveWoodBehavior, BlockStrippedOakWoodBehavior, BlockStrippedSpruceWoodBehavior, BlockAcaciaLogBehavior, BlockBirchLogBehavior, BlockCherryLogBehavior, BlockDarkOakLogBehavior, BlockJungleLogBehavior, BlockMangroveLogBehavior, BlockOakLogBehavior, BlockSpruceLogBehavior, BlockStrippedAcaciaLogBehavior, BlockStrippedBirchLogBehavior, BlockStrippedCherryLogBehavior, BlockStrippedDarkOakLogBehavior, BlockStrippedJungleLogBehavior, BlockStrippedMangroveLogBehavior, BlockStrippedOakLogBehavior, BlockStrippedSpruceLogBehavior {
    public BlockWoodBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    protected BlockWoodBaseComponent getBaseComponent() {
        return (BlockWoodBaseComponent) super.getBaseComponent();
    }
}
