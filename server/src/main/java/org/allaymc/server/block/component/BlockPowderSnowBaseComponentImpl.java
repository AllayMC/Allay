package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerBucketFillEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.sound.BucketFillSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;

import java.util.Set;

/**
 * Block component for powder snow.
 *
 * <p>Powder snow freezes living entities that walk into it. Freeze ticks increase by 1 each game
 * tick while an entity is inside the block, up to {@link EntityLivingComponent#MAX_FREEZE_TICKS}
 * (140 ticks / 7 seconds). Once fully frozen, the entity takes 1 HP of freezing damage every
 * 40 ticks. The freeze counter decreases at the same rate once the entity leaves the block.
 *
 * <p>Entities wearing leather boots are immune to the freeze effect.
 *
 * <p>An empty bucket used on this block collects it as a powder snow bucket.
 *
 * @author daoge_cmd
 */
public class BlockPowderSnowBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockPowderSnowBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        // Entities wearing leather boots are immune to freezing
        if (wearsLeatherBoots(entity)) {
            return;
        }

        // Only set the flag; tickFreeze() performs the actual increment once per tick,
        // so freeze rate is always exactly 1 tick regardless of how many powder snow
        // blocks the entity's AABB intersects.
        living.setInPowderSnow(true);
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Powder snow cannot be picked up by hand; only a bucket yields it
        return Set.of();
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();
        if (interactInfo == null) {
            return;
        }

        var player = interactInfo.player();
        if (player == null) {
            return;
        }

        var itemStack = event.getItemStack();
        if (!(itemStack instanceof ItemBucketStack bucketStack) || itemStack.getItemType() != ItemTypes.BUCKET) {
            return;
        }

        // Empty bucket picks up powder snow
        if (!new PlayerBucketFillEvent(player, bucketStack, interactInfo).call()) {
            return;
        }

        var dimension = event.getDimension();
        var pos = interactInfo.clickedBlockPos();

        dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        dimension.addSound(pos, new BucketFillSound(BucketFillSound.Type.POWDER_SNOW));

        player.tryConsumeItemInHand();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.POWDER_SNOW_BUCKET.createItemStack(1));

        event.setSuccess(true);
    }

    /**
     * Check whether the given entity is wearing leather boots.
     * Entities with leather boots are immune to powder snow freezing.
     *
     * @param entity the entity to check
     * @return {@code true} if the entity is wearing leather boots
     */
    public static boolean wearsLeatherBoots(Entity entity) {
        if (!(entity instanceof EntityContainerHolderComponent containerHolder)) {
            return false;
        }
        if (!containerHolder.hasContainer(ContainerTypes.ARMOR)) {
            return false;
        }
        return containerHolder.getContainer(ContainerTypes.ARMOR).getBoots().getItemType() == ItemTypes.LEATHER_BOOTS;
    }
}
