package org.allaymc.server.block.impl.sand;

import java.util.List;
import org.allaymc.api.block.interfaces.sand.BlockSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSandBehaviorImpl extends BlockBehaviorImpl implements BlockSandBehavior {
    public BlockSandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
