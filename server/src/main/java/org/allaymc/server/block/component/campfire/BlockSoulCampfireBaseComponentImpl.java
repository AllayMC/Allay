package org.allaymc.server.block.component.campfire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockSoulCampfireBaseComponentImpl extends BlockCampfireBaseComponentImpl {

    public BlockSoulCampfireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Soul campfire drops 1 soul soil when broken
        return Set.of(ItemTypes.SOUL_SOIL.createItemStack(1));
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (isExtinguished(block.getBlockState())) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            if (living.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                return;
            }
            // Soul campfire deals 2 damage per tick (1 heart)
            living.attack(DamageContainer.campfire(2f));
        }
    }
}
