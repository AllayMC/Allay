package org.allaymc.server.block.impl.fence;

import java.util.List;
import org.allaymc.api.block.interfaces.fence.BlockSpruceFenceBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSpruceFenceBehaviorImpl extends BlockBehaviorImpl implements BlockSpruceFenceBehavior {
    public BlockSpruceFenceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
