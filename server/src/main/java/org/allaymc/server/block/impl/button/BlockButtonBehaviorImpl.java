package org.allaymc.server.block.impl.button;

import org.allaymc.api.block.interfaces.button.*;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockButtonBehaviorImpl extends BlockBehaviorImpl implements
        BlockAcaciaButtonBehavior, BlockBambooButtonBehavior, BlockBirchButtonBehavior, BlockCherryButtonBehavior,
        BlockCrimsonButtonBehavior, BlockDarkOakButtonBehavior, BlockJungleButtonBehavior, BlockMangroveButtonBehavior,
        BlockPolishedBlackstoneButtonBehavior, BlockSpruceButtonBehavior, BlockStoneButtonBehavior, BlockWarpedButtonBehavior,
        BlockWoodenButtonBehavior {
    public BlockButtonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
