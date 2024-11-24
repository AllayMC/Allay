package org.allaymc.server.block.impl.fencegate;

import java.util.List;
import org.allaymc.api.block.interfaces.fencegate.BlockMangroveFenceGateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockMangroveFenceGateBehaviorImpl extends BlockBehaviorImpl implements BlockMangroveFenceGateBehavior {
    public BlockMangroveFenceGateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
