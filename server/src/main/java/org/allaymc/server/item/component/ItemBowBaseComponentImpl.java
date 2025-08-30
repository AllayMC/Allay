package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.PlayerOffhandContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.EntityShootBowEvent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.math.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3d;

/**
 * @author harryxi | daoge_cmd
 */
@Slf4j
public class ItemBowBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemBowBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return hasArrow(player);
    }

    @Override
    public void releaseItem(EntityPlayer player, long usedTime) {
        if (usedTime < 3) {
            return;
        }

        var creative = player.getGameType() == GameType.CREATIVE;
        var force = Math.min(usedTime * (usedTime + 40.0) / 1200.0, 1.0);
        var speed = force * 5;

        var infinity = getEnchantmentLevel(EnchantmentTypes.INFINITY) != 0;
        PotionType potionType = null;
        if (!creative) {
            var arrow = findArrow(player, infinity);
            if (arrow == null) {
                return;
            }

            potionType = arrow.getPotionType();
        }

        var powerLevel = getEnchantmentLevel(EnchantmentTypes.POWER);
        var punchLevel = getEnchantmentLevel(EnchantmentTypes.PUNCH);
        var flameLevel = getEnchantmentLevel(EnchantmentTypes.FLAME);

        var dimension = player.getDimension();
        var location = player.getLocation();
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());
        var playerMotion = new Vector3d(player.getMotion());
        if (player.isOnGround()) {
            playerMotion.setComponent(1, 0);
        }
        var arrow = EntityTypes.ARROW.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(shootPos)
                        .rot(-location.yaw(), -location.pitch())
                        .motion(MathUtils.getDirectionVector(location).mul(speed).add(playerMotion))
                        .build()
        );
        arrow.setShooter(player);
        arrow.setPotionType(potionType);
        if (infinity || creative) {
            arrow.setInfinite(true);
        }
        arrow.setPowerLevel(powerLevel);
        arrow.setPunchLevel(punchLevel);
        if (flameLevel != 0) {
            arrow.setOnFireTicks(20 * 5);
        }
        arrow.setCritical(force >= 1.0);

        var event = new EntityShootBowEvent(player, thisItemStack, arrow);
        if (!event.call()) {
            return;
        }

        dimension.getEntityService().addEntity(arrow);
        if (!creative) {
            tryIncreaseDamage(1);
        }
        player.getDimension().addLevelSoundEvent(shootPos, SoundEvent.BOW);
    }

    protected boolean hasArrow(EntityPlayer player) {
        // Find offhand arrow first
        Container container = player.getContainer(FullContainerType.OFFHAND);
        if (container.getItemStack(PlayerOffhandContainer.OFFHAND_SLOT) instanceof ItemArrowStack) {
            return true;
        }

        // Arrow is not in offhand, search in inventory again
        container = player.getContainer(FullContainerType.PLAYER_INVENTORY);
        for (var itemStack : container.getItemStacks()) {
            if (itemStack instanceof ItemArrowStack) {
                return true;
            }
        }

        return false;
    }

    protected ItemArrowStack findArrow(EntityPlayer player, boolean infinity) {
        Container container;
        int slot;
        ItemArrowStack arrow = null;

        // Find offhand arrow first
        container = player.getContainer(FullContainerType.OFFHAND);
        slot = PlayerOffhandContainer.OFFHAND_SLOT;
        if (container.getItemStack(slot) instanceof ItemArrowStack a) {
            arrow = a;
        }

        if (arrow == null) {
            // Arrow is not in offhand, search in inventory again
            container = player.getContainer(FullContainerType.PLAYER_INVENTORY);
            var itemStacks = container.getItemStacks();
            for (slot = 0; slot < itemStacks.size(); slot++) {
                var item = itemStacks.get(slot);
                if (item instanceof ItemArrowStack a) {
                    arrow = a;
                    break;
                }
            }
        }

        if (arrow == null) {
            // No arrow found
            return null;
        }

        // Bows with infinity enchantment do not consume normal types of arrows when shooting
        if (!infinity || arrow.getPotionType() != null) {
            if (arrow.getCount() == 1) {
                container.clearSlot(slot);
            } else {
                arrow.reduceCount(1);
                container.notifySlotChange(slot);
            }
        }

        return arrow;
    }
}
