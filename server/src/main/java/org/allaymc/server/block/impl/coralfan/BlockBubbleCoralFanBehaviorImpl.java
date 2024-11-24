package org.allaymc.server.block.impl.coralfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralfan.BlockBubbleCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBubbleCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockBubbleCoralFanBehavior {
    public BlockBubbleCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
