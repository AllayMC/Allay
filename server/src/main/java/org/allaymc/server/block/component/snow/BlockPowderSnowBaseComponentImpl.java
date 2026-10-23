package org.allaymc.server.block.component.snow;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;

import java.util.Set;

/**
 * {@code BlockPowderSnowBaseComponentImpl} implements powder-snow contact marking and
 * empty-bucket collection. Living entities without leather boots are marked as inside powder
 * snow; the living-entity tick owns freeze accumulation, thawing, and freezing damage instead
 * of incrementing the counter on every block intersection.
 *
 * <p>
 * Bucket interaction runs through a component event listener and honors the bucket-fill event
 * before removing the block. Acceptance writes air, emits the fill sound, consumes the held
 * bucket, and offers a powder-snow bucket to inventory. The insertion result is ignored, so
 * the callback does not undo removal or consumption when inventory insertion fails. Ordinary
 * drops remain empty, and powder-snow contact can reset fall damage.
 *
 * @author daoge_cmd
 */
public class BlockPowderSnowBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the powder snow behavior component for the supplied block type.
     */
    public BlockPowderSnowBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Marks a living entity as inside powder snow unless it wears leather boots. The living
     * tick consumes this flag once per tick, preventing multiple intersecting powder-snow
     * blocks from directly multiplying the freeze increment.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        if (wearsLeatherBoots(entity)) {
            return;
        }

        // Record contact rather than adding freeze duration here. tickFreeze increases
        // the counter at most once per living tick, up to its cap, regardless
        // of how many powder-snow blocks marked this same contact flag.
        living.setInPowderSnow(true);
    }

    /**
     * Returns whether powder-snow contact can clear accumulated entity fall damage.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * Returns no ordinary item drops because collection uses a bucket interaction.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    /**
     * Collects powder snow for an empty bucket through the component interaction event.
     * Missing interaction information, a missing player, or another item leaves the event
     * untouched. The bucket-fill event can cancel before the block and inventory effects
     * begin.
     *
     * <p>
     * Acceptance removes the block before emitting sound, consuming the held bucket, and
     * offering the filled bucket to inventory. The world-write and inventory-add results are
     * ignored, and no rollback joins these operations. The component event is then marked
     * successful so the ordinary block interaction reports handling.
     */
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

        if (!new PlayerBucketFillEvent(player, bucketStack, interactInfo).call()) {
            return;
        }

        var dimension = event.getDimension();
        var pos = interactInfo.clickedBlockPos();

        // Apply block removal before consuming and inserting buckets. The insertion result
        // is ignored, so this sequence does not restore the snow after inventory rejection.
        dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        dimension.addSound(pos, new BucketFillSound(BucketFillSound.Type.POWDER_SNOW));

        player.tryConsumeItemInHand();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.POWDER_SNOW_BUCKET.createItemStack(1));

        event.setSuccess(true);
    }

    /**
     * Returns whether the entity's armor container currently holds leather boots. Entities
     * without a container-holder component or armor container return {@code false}; this
     * helper reads the equipment rather than changing freeze state.
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
