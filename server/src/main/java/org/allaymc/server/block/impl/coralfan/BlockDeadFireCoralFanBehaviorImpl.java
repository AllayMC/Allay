package org.allaymc.server.block.impl.coralfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralfan.BlockDeadFireCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadFireCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockDeadFireCoralFanBehavior {
    public BlockDeadFireCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
