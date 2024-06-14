package org.allaymc.server.entity.component.player;

import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.impl.*;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.entity.component.common.EntityContainerHolderComponentImpl;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl implements EntityPlayerContainerHolderComponent {

    @ComponentedObject
    private EntityPlayer player;

    public EntityPlayerContainerHolderComponentImpl() {
        super(new PlayerCursorContainer(),
                new PlayerCreatedOutputContainer(),
                new PlayerArmorContainer(),
                new PlayerOffhandContainer(),
                new CraftingGridContainer(),
                new CraftingTableContainer()
        );
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        addContainer(new PlayerInventoryContainer(player));
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !(boolean)player.getWorld().getGameRule(GameRule.KEEP_INVENTORY);
    }
}
