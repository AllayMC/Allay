package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.tool.ItemToolComponent;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * @author IWareQ, daoge_cmd
 */
public class ItemToolComponentImpl implements ItemToolComponent {
    @Dependency
    protected ItemBaseComponent baseComponent;
    @ComponentObject
    protected ItemStack itemStack;

    @EventHandler
    protected void onBreakBlock(CItemBreakBlockEvent event) {
        var entity = event.getBreaker();
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) {
            return;
        }

        var hardness = event.getBlock().getBlockStateData().hardness();
        if (hardness == 0) {
            return;
        }

        if (!event.getBlock().getBehavior().canDamageItem(itemStack)) {
            return;
        }

        baseComponent.tryReduceDurability(1);
    }

    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        baseComponent.tryReduceDurability(2);
    }
}
