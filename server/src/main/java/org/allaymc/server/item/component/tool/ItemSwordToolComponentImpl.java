package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * {@code ItemSwordToolComponentImpl} applies sword durability costs instead of
 * the ordinary tool costs. Eligible block breaks request two damage points,
 * while attacks request one; both paths delegate Unbreaking and item damage
 * event rules to the stack carried by the event. This component is stateless
 * and may be shared as {@link #INSTANCE}.
 *
 * @author daoge_cmd
 */
public class ItemSwordToolComponentImpl extends ItemToolComponentImpl implements ItemToolComponent {

    /**
     * {@code INSTANCE} is the shared stateless singleton for sword durability
     * handling.
     */
    public static final ItemSwordToolComponentImpl INSTANCE = new ItemSwordToolComponentImpl();

    /**
     * Requests two durability points for an eligible sword block break.
     * Creative players, zero-hardness blocks, and blocks that refuse to damage the
     * item produce no request; the event's stack applies the remaining damage
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

        var itemStack = event.getItemStack();
        if (!event.getBlock().getBehavior().canDamageItem(itemStack)) {
            return;
        }

        itemStack.tryIncreaseDamage(2);
    }

    /**
     * Requests one durability point when this sword receives a component attack
     * event. The handler has no game-mode check and delegates Unbreaking, event
     * cancellation, and the actual damage increase to the event's stack.
     */
    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        event.getItemStack().tryIncreaseDamage(1);
    }
}
