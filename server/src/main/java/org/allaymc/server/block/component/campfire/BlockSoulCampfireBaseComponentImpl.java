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
 * {@code BlockSoulCampfireBaseComponentImpl} specializes campfire drops and contact damage for
 * soul campfires. Ordinary breaking yields soul soil, and lit contact attempts two points of
 * damage against living entities without fire resistance. Ignition, food handling,
 * extinguishing, and comparator behavior remain inherited from the campfire component.
 *
 * @author daoge_cmd
 */
public class BlockSoulCampfireBaseComponentImpl extends BlockCampfireBaseComponentImpl {

    /**
     * Creates the soul campfire behavior component for the supplied block type.
     */
    public BlockSoulCampfireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns one soul-soil item for ordinary soul-campfire breaking without silk touch.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.SOUL_SOIL.createItemStack(1));
    }

    /**
     * Attempts two points of campfire damage against a colliding living entity. The
     * extinguished state and fire-resistance effect suppress damage, while the attack path
     * retains the entity's ordinary damage acceptance rules.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (isExtinguished(block.getBlockState())) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            if (living.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                return;
            }
            living.attack(DamageContainer.campfire(2f));
        }
    }
}
