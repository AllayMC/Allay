package org.allaymc.server.block.impl.head;

import java.util.List;
import org.allaymc.api.block.interfaces.head.BlockWitherSkeletonSkullBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWitherSkeletonSkullBehaviorImpl extends BlockBehaviorImpl implements BlockWitherSkeletonSkullBehavior {
    public BlockWitherSkeletonSkullBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
