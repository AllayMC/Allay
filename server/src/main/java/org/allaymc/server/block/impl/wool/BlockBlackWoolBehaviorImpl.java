package org.allaymc.server.block.impl.wool;

import java.util.List;
import org.allaymc.api.block.interfaces.wool.BlockBlackWoolBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBlackWoolBehaviorImpl extends BlockBehaviorImpl implements BlockBlackWoolBehavior {
    public BlockBlackWoolBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
