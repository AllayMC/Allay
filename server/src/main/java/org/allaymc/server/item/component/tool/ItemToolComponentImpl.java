package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * {@code ItemToolComponentImpl} applies ordinary tool durability costs to
 * component attack and block-break events. Block breaking skips creative
 * players, zero-hardness blocks, and blocks that reject item damage; attacks
 * request their cost directly, with Unbreaking and item damage events handled
 * by the owning stack supplied in each event. This component holds no mutable
 * per-stack state and may be shared as {@link #INSTANCE}.
 *
 * @author IWareQ, daoge_cmd
 */
public class ItemToolComponentImpl implements ItemToolComponent {

    /**
     * {@code INSTANCE} is the shared stateless singleton for ordinary tool
     * durability handling.
     */
    public static final ItemToolComponentImpl INSTANCE = new ItemToolComponentImpl();

    /**
     * Requests one durability point for an eligible block break.
     * Creative players, zero-hardness blocks, and blocks whose behavior rejects
     * damaging the event's stack produce no request; the stack can still reject
     * or modify an attempted damage increase.
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

        itemStack.tryIncreaseDamage(1);
    }

    /**
     * Requests two durability points when the component receives an attack event.
     * The handler performs no game-mode check; {@code itemStack.tryIncreaseDamage}
     * applies Unbreaking and the cancellable item damage event to the request.
     */
    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        event.getItemStack().tryIncreaseDamage(2);
    }
}
