package org.allaymc.server.item.component.tool;

import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.common.ItemBaseComponent;
import org.allaymc.api.item.component.event.ItemAttackEntityEvent;
import org.allaymc.api.item.component.event.ItemBreakBlockEvent;
import org.allaymc.api.item.component.tool.ItemSwordComponent;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Allay Project 2024/6/23
 *
 * @author daoge_cmd
 */
public class ItemSwordComponentImpl implements ItemSwordComponent {
    @Dependency
    protected ItemBaseComponent baseComponent;

    @EventHandler
    protected void onBreakBlock(ItemBreakBlockEvent event) {
        var entity = event.getBreaker();
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) return;
        var hardness = event.getBlock().getBlockAttributes().hardness();
        if (hardness == 0) return;
        if (!baseComponent.willDamageItem()) return;
        baseComponent.increaseDurability(2);
    }

    @EventHandler
    protected void onAttackEntity(ItemAttackEntityEvent event) {
        if (!baseComponent.willDamageItem()) return;
        baseComponent.increaseDurability(1);
    }
}
