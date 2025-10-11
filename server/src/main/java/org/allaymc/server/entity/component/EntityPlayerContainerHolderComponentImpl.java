package org.allaymc.server.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerEnchantOptionsRequestEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.container.impl.*;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.allaymc.server.network.NetworkHelper;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;

/**
 * @author daoge_cmd
 */
public class EntityPlayerContainerHolderComponentImpl extends EntityContainerHolderComponentImpl {

    @ComponentObject
    private EntityPlayer thisPlayer;

    public EntityPlayerContainerHolderComponentImpl() {
        super(
                new PlayerCreatedOutputContainerImpl(),
                new CraftingGridContainerImpl(),
                new CraftingTableContainerImpl(),
                new BeaconContainerImpl(),
                new EnderChestContainerImpl(),
                new AnvilContainerImpl()
        );
        var enchantTableContainer = new EnchantTableContainerImpl();
        enchantTableContainer.addSlotChangeListener(EnchantTableContainerImpl.INPUT_SLOT, item -> {
            var blockPos = enchantTableContainer.getBlockPos();
            if (blockPos != null) {
                onEnchantTableContainerInputItemChange(item, new Position3i(blockPos, thisPlayer.getDimension()));
            }
        });
        addContainer(enchantTableContainer);
        // We shouldn't provide thisPlayer object directly
        // because at that time thisPlayer is null
        addContainer(new ArmorContainerImpl(() -> thisPlayer));
        addContainer(new InventoryContainerImpl(() -> thisPlayer));
        addContainer(new OffhandContainerImpl(() -> thisPlayer));
        addContainer(new PlayerCursorContainerImpl(() -> thisPlayer));
    }

    protected void onEnchantTableContainerInputItemChange(ItemStack item, Position3ic enchantTablePos) {
        var packet = new PlayerEnchantOptionsPacket();
        if (item != ItemAirStack.AIR_STACK) {
            var enchantOptions = EnchantmentOptionGenerator.generateEnchantOptions(enchantTablePos, item, thisPlayer.getEnchantmentSeed());
            var event = new PlayerEnchantOptionsRequestEvent(thisPlayer, enchantOptions.stream().map(Pair::right).toList());
            if (!event.call()) {
                return;
            }

            packet.getOptions().addAll(enchantOptions.stream().map(NetworkHelper::toNetwork).toList());
        }

        thisPlayer.sendPacket(packet);
    }

    @Override
    protected boolean canDropItemInContainers() {
        return !thisPlayer.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.KEEP_INVENTORY);
    }
}
