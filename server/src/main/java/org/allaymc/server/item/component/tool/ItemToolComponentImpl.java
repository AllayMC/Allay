package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * {@code ItemToolComponentImpl} applies ordinary tool durability costs to
 * component attack and block-break events. Block breaking skips creative
 * players, zero-hardness blocks, and blocks that reject item damage; attacks
 * request their cost directly, with Unbreaking and item damage events handled
 * by the owning stack.
 *
 * @author IWareQ, daoge_cmd
 */
public class ItemToolComponentImpl implements ItemToolComponent {

    /**
     * {@code IDENTIFIER} identifies ordinary tool behavior in component-provider
     * registration and lookup. Tool specializations inherit this identifier when
     * they replace the default durability event handlers.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_tool_component");

    /**
     * {@code itemStack} is the owning stack injected during component
     * initialization. Event handlers send durability requests to this stack so its
     * Unbreaking and item damage event rules still apply.
     */
    @ComponentObject
    protected ItemStack itemStack;

    /**
     * Requests one durability point for an eligible block break.
     * Creative players, zero-hardness blocks, and blocks whose behavior rejects
     * damaging {@code itemStack} produce no request; the stack can still reject
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
        itemStack.tryIncreaseDamage(2);
    }
}
