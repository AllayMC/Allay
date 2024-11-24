package org.allaymc.server.block.impl.sandstone;

import java.util.List;
import org.allaymc.api.block.interfaces.sandstone.BlockRedSandstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockRedSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockRedSandstoneBehavior {
    public BlockRedSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
