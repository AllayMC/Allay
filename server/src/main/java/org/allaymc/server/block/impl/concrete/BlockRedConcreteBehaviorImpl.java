package org.allaymc.server.block.impl.concrete;

import java.util.List;
import org.allaymc.api.block.interfaces.concrete.BlockRedConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockRedConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockRedConcreteBehavior {
    public BlockRedConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
