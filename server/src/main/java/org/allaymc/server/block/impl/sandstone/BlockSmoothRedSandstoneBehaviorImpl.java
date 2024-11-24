package org.allaymc.server.block.impl.sandstone;

import java.util.List;
import org.allaymc.api.block.interfaces.sandstone.BlockSmoothRedSandstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSmoothRedSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockSmoothRedSandstoneBehavior {
    public BlockSmoothRedSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
