package org.allaymc.server.block.impl.concrete;

import java.util.List;
import org.allaymc.api.block.interfaces.concrete.BlockOrangeConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOrangeConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockOrangeConcreteBehavior {
    public BlockOrangeConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
