package org.allaymc.server.block.impl.coralfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralfan.BlockBrainCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBrainCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockBrainCoralFanBehavior {
    public BlockBrainCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
