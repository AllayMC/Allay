package org.allaymc.server.block.impl.concrete;

import java.util.List;
import org.allaymc.api.block.interfaces.concrete.BlockLightGrayConcreteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightGrayConcreteBehaviorImpl extends BlockBehaviorImpl implements BlockLightGrayConcreteBehavior {
    public BlockLightGrayConcreteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
