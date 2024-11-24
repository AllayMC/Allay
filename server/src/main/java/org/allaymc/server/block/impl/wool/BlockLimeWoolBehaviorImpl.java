package org.allaymc.server.block.impl.wool;

import java.util.List;
import org.allaymc.api.block.interfaces.wool.BlockLimeWoolBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLimeWoolBehaviorImpl extends BlockBehaviorImpl implements BlockLimeWoolBehavior {
    public BlockLimeWoolBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
