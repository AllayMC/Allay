package org.allaymc.server.block.impl.fencegate;

import java.util.List;
import org.allaymc.api.block.interfaces.fencegate.BlockBambooFenceGateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBambooFenceGateBehaviorImpl extends BlockBehaviorImpl implements BlockBambooFenceGateBehavior {
    public BlockBambooFenceGateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
