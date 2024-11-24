package org.allaymc.server.block.impl.concrete;

import java.util.List;
import org.allaymc.api.block.interfaces.concrete.BlockBrownConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBrownConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockBrownConcreteBehavior {
    public BlockBrownConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
