package org.allaymc.server.entity.component.player;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.impl.CraftingGridContainer;
import org.allaymc.api.container.impl.CraftingTableContainer;
import org.allaymc.api.container.impl.PlayerArmorContainer;
import org.allaymc.api.container.impl.PlayerCreatedOutputContainer;
import org.allaymc.api.container.impl.PlayerCursorContainer;
import org.allaymc.api.container.impl.PlayerInventoryContainer;
import org.allaymc.api.container.impl.PlayerOffhandContainer;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.entity.component.common.EntityContainerHolderComponentImpl;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl implements EntityPlayerContainerHolderComponent {

    @ComponentedObject
    private EntityPlayer player;

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

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        addContainer(new PlayerInventoryContainer(player));
    }
}
