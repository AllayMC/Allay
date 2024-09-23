package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.tool.ItemSwordComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * @author daoge_cmd
 */
public class ItemSwordComponentImpl implements ItemSwordComponent {
    @Dependency
    protected ItemBaseComponent baseComponent;

    @EventHandler
    protected void onBreakBlock(CItemBreakBlockEvent event) {
        var entity = event.getBreaker();
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) return;

        var hardness = event.getBlock().getBlockStateData().hardness();
        if (hardness == 0) return;

        if (!baseComponent.canBeDamagedThisTime()) return;
        baseComponent.reduceDurability(2);
    }

    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        if (!baseComponent.canBeDamagedThisTime()) return;
        baseComponent.reduceDurability(1);
    }
}