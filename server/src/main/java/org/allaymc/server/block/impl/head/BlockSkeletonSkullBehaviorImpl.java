package org.allaymc.server.block.impl.head;

import java.util.List;
import org.allaymc.api.block.interfaces.head.BlockSkeletonSkullBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSkeletonSkullBehaviorImpl extends BlockBehaviorImpl implements BlockSkeletonSkullBehavior {
    public BlockSkeletonSkullBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
