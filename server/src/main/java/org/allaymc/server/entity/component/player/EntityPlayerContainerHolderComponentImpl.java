package org.allaymc.server.entity.component.player;

import org.allaymc.api.container.impl.*;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerEnchantOptionsRequestEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityContainerHolderComponentImpl;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl implements EntityPlayerContainerHolderComponent {

    @ComponentObject
    private EntityPlayer thisPlayer;

    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCreatedOutputContainer(),
                new CraftingGridContainer(),
                new CraftingTableContainer()
        );
        var enchantTableContainer = new EnchantTableContainer();
        enchantTableContainer.addOnSlotChangeListener(EnchantTableContainer.INPUT_SLOT, item -> {
            var blockPos = enchantTableContainer.getBlockPos();
            if (blockPos != null) {
                onEnchantTableContainerInputItemChange(item, new Position3i(blockPos, thisPlayer.getDimension()));
            }
        });
        addContainer(enchantTableContainer);
        // We shouldn't provide thisPlayer object directly
        // because at that time thisPlayer is null
        addContainer(new PlayerArmorContainer(() -> thisPlayer));
        addContainer(new PlayerInventoryContainer(() -> thisPlayer));
        addContainer(new PlayerOffhandContainer(() -> thisPlayer));
        addContainer(new PlayerCursorContainer(() -> thisPlayer));
    }

    protected void onEnchantTableContainerInputItemChange(ItemStack item, Position3ic enchantTablePos) {
        var pk = new PlayerEnchantOptionsPacket();
        if (item != ItemAirStack.AIR_STACK) {
            var enchantOptions = EnchantmentOptionGenerator.generateEnchantOptions(enchantTablePos, item, thisPlayer.getEnchantmentSeed());
            var event = new PlayerEnchantOptionsRequestEvent(thisPlayer, enchantOptions);
            if (!event.call()) return;

            pk.getOptions().addAll(event.getEnchantOptions());
        }

        thisPlayer.sendPacket(pk);
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !thisPlayer.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.KEEP_INVENTORY);
    }
}
