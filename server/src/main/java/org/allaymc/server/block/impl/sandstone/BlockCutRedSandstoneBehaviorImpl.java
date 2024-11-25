package org.allaymc.server.block.impl.sandstone;

import org.allaymc.api.block.interfaces.sandstone.BlockCutRedSandstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCutRedSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockCutRedSandstoneBehavior {
    public BlockCutRedSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
