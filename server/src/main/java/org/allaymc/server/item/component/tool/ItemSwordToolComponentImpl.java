package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * {@code ItemSwordToolComponentImpl} applies sword durability costs instead of
 * the ordinary tool costs. Eligible block breaks request two damage points,
 * while attacks request one; both paths use the injected base component so
 * Unbreaking and item damage events continue to control the actual increase.
 *
 * @author daoge_cmd
 */
public class ItemSwordToolComponentImpl extends ItemToolComponentImpl implements ItemToolComponent {
    /**
     * {@code baseComponent} is the injected base component of the owning item.
     * Sword event handlers use it for durability requests rather than changing
     * the item's damage directly.
     */
    @Dependency
    protected ItemBaseComponent baseComponent;

    /**
     * Requests two durability points for an eligible sword block break.
     * Creative players, zero-hardness blocks, and blocks that refuse to damage the
     * item produce no request; {@code baseComponent} applies the remaining damage
     * rules and item damage event.
     */
    @EventHandler
    protected void onBreakBlock(CItemBreakBlockEvent event) {
        var entity = event.getBreaker();
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        var hardness = event.getBlock().getBlockStateData().hardness();
        if (hardness == 0) {
            return;
        }

        if (!event.getBlock().getBehavior().canDamageItem(itemStack)) {
            return;
        }

        baseComponent.tryIncreaseDamage(2);
    }

    /**
     * Requests one durability point when this sword receives a component attack
     * event. The handler has no game-mode check and delegates Unbreaking, event
     * cancellation, and the actual damage increase to {@code baseComponent}.
     */
    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        baseComponent.tryIncreaseDamage(1);
    }
}
