package org.allaymc.server.block.impl.dirt;

import java.util.List;
import org.allaymc.api.block.interfaces.dirt.BlockDirtBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDirtBehaviorImpl extends BlockBehaviorImpl implements BlockDirtBehavior {
    public BlockDirtBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
