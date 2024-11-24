package org.allaymc.server.block.impl.concrete;

import java.util.List;
import org.allaymc.api.block.interfaces.concrete.BlockPinkConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPinkConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockPinkConcreteBehavior {
    public BlockPinkConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
