package org.allaymc.server.block.impl.fence;

import java.util.List;
import org.allaymc.api.block.interfaces.fence.BlockOakFenceBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOakFenceBehaviorImpl extends BlockBehaviorImpl implements BlockOakFenceBehavior {
    public BlockOakFenceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
