package org.allaymc.server.entity.component.player;

import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.OnInitFinish;
import org.allaymc.api.container.impl.*;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
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
    private EntityPlayer thisPlayer;

    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCursorContainer(),
                new PlayerCreatedOutputContainer(),
                new PlayerArmorContainer(),
                new PlayerOffhandContainer(),
                new CraftingGridContainer(),
                new CraftingTableContainer()
        );
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        addContainer(new PlayerInventoryContainer(thisPlayer));
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !(boolean) thisPlayer.getWorld().getWorldData().getGameRule(GameRule.KEEP_INVENTORY);
    }
}
