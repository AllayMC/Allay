package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.impl.*;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl implements EntityPlayerContainerHolderComponent {

    @ComponentedObject
    private EntityPlayer thisPlayer;

    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCreatedOutputContainer(),
                new CraftingGridContainer(),
                new CraftingTableContainer(),
                new EnchantTableContainer()
        );
        // We shouldn't provide thisPlayer object directly
        // because at that time thisPlayer is null
        addContainer(new PlayerArmorContainer(() -> thisPlayer));
        addContainer(new PlayerInventoryContainer(() -> thisPlayer));
        addContainer(new PlayerOffhandContainer(() -> thisPlayer));
        addContainer(new PlayerCursorContainer(() -> thisPlayer));
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !(boolean) thisPlayer.getWorld().getWorldData().getGameRule(GameRule.KEEP_INVENTORY);
    }
}
