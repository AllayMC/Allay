package org.allaymc.server.item.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class ItemStackImpl extends ComponentClass implements ItemStack {

    @Getter
    @Delegate
    private ItemBaseComponent baseComponent;

    public ItemStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    /**
     * Place the block as the given player, judging the placement's entity collisions against the
     * position the player declared in the interaction packet rather than the position the world
     * thread has applied, which lags a moving player by a tick and lets blocks slip into the
     * player's own body.
     */
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo, Vector3fc declaredPlayerPosition) {
        if (baseComponent instanceof ItemBaseComponentImpl impl) {
            return impl.placeBlock(dimension, placeBlockPos, placementInfo, declaredPlayerPosition);
        }
        return placeBlock(dimension, placeBlockPos, placementInfo);
    }
}
