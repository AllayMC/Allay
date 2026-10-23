package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerBucketEmptyEvent;
import org.allaymc.api.eventbus.event.player.PlayerBucketFillEvent;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.sound.BucketEmptySound;
import org.allaymc.api.world.sound.BucketFillSound;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code ItemBucketComponentImpl} resolves a bucket's liquid and captured entity
 * and handles player filling or emptying interactions. Suppliers defer the
 * content-type lookups, while missing suppliers represent air or no captured
 * entity. Cauldron interactions remain available to the cauldron block behavior.
 *
 * <p>
 * Filling recognizes source water and lava after the fill event is allowed.
 * Emptying chooses a clicked block's secondary liquid layer before considering
 * the placement position. After the empty event is allowed, entity creation,
 * sound, and inventory changes proceed even if no liquid placement branch
 * accepted the target. The handler performs these changes in sequence without
 * rollback, so its handled result is not a transactional placement guarantee.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemBucketComponentImpl implements ItemBucketComponent {

    /**
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_bucket_component");

    /**
     * {@code liquidType} resolves liquid contents on demand. A missing supplier makes
     * {@code getLiquidType} return {@code BlockTypes.AIR} instead of performing a lookup.
     */
    protected final Supplier<BlockType<?>> liquidType;
    /**
     * {@code entityType} resolves captured-entity contents on demand. A missing
     * supplier makes {@code getEntityType} return {@code null}, independently of the
     * liquid supplier.
     */
    protected final Supplier<EntityType<?>> entityType;

    /**
     * Injected component-owning bucket item stack.
     */
    @ComponentObject
    protected ItemBucketStack thisBucketStack;

    /**
     * Creates a bucket capability retaining its liquid and entity suppliers.
     * A {@code null} liquid supplier represents air, and a {@code null} entity
     * supplier represents no captured entity; non-{@code null} suppliers resolve on
     * each query.
     */
    public ItemBucketComponentImpl(Supplier<BlockType<?>> liquidTypeSupplier, Supplier<EntityType<?>> entityTypeSupplier) {
        this.liquidType = liquidTypeSupplier;
        this.entityType = entityTypeSupplier;
    }

    /**
     * Creates deferred content-type lookups from liquid and optional entity IDs.
     * {@code liquidId} must be present to form its method reference; a {@code null}
     * {@code entityId} selects no captured entity.
     */
    public ItemBucketComponentImpl(BlockId liquidId, EntityId entityId) {
        this(liquidId::getBlockType, entityId == null ? null : entityId::getEntityType);
    }

    /**
     * Returns this bucket's lazily resolved liquid block type.
     * A missing supplier yields {@code BlockTypes.AIR}; a present supplier's result
     * is returned directly without substituting for a {@code null} result.
     */
    @Override
    public BlockType<?> getLiquidType() {
        return liquidType == null ? BlockTypes.AIR : liquidType.get();
    }

    /**
     * Returns this bucket's lazily resolved captured-entity type, if any.
     * A missing supplier yields {@code null}; a present supplier is evaluated on
     * every call without caching its result.
     */
    @Override
    public EntityType<?> getEntityType() {
        return entityType == null ? null : entityType.get();
    }

    /**
     * Handles filling an empty bucket or emptying its configured contents.
     * The event must carry player interaction information. Cauldrons are left to
     * their block behavior, and cancelled player bucket events stop this handler
     * before its world and inventory updates.
     *
     * <p>
     * Filling accepts source-depth liquid behavior, including implementations without
     * the depth property, then replaces recognized water or lava with air and adds
     * the filled bucket. Emptying prefers a secondary liquid layer in the clicked
     * block, then tests the placement position. Entity spawning and consumption can
     * still proceed when no liquid placement branch succeeds; no rollback joins
     * these steps into one atomic operation.
     */
    @EventHandler
    protected void onUseItemOnBlock(CItemUseOnBlockEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        var dimension = player.getDimension();
        var clickedBlockState = interactInfo.getClickedBlock();

        // Cauldron block behavior owns its liquid levels and bucket exchange,
        // so leave this item event unhandled to allow that interaction path.
        if (clickedBlockState.getBehavior() instanceof BlockCauldronBehavior) {
            return;
        }

        if (isEmpty()) {
            if (!(clickedBlockState.getBehavior() instanceof BlockLiquidBehavior)) {
                return;
            }
            try {
                if (clickedBlockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) != 0) {
                    return;
                }
            } catch (IllegalArgumentException ignore) {
                // A liquid behavior without LIQUID_DEPTH still reaches the fill
                // event; only recognized water or lava types perform the exchange.
            }

            if (!new PlayerBucketFillEvent(player, thisBucketStack, interactInfo).call()) {
                return;
            }

            var blockType = clickedBlockState.getBlockType();
            if (blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER) {
                player.tryConsumeItemInHand();
                player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.WATER_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickedBlockPos(), BlockTypes.AIR.getDefaultState());
                dimension.addSound(interactInfo.clickedBlockPos(), new BucketFillSound(BucketFillSound.Type.WATER));
            } else if (blockType == BlockTypes.LAVA || blockType == BlockTypes.FLOWING_LAVA) {
                player.tryConsumeItemInHand();
                player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.LAVA_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickedBlockPos(), BlockTypes.AIR.getDefaultState());
                dimension.addSound(interactInfo.clickedBlockPos(), new BucketFillSound(BucketFillSound.Type.LAVA));
            }
            event.setCanBeUsed(true);
            return;
        }

        if (!new PlayerBucketEmptyEvent(player, thisBucketStack, interactInfo).call()) {
            return;
        }

        Vector3ic liquidPlacedPos = event.getPlaceBlockPos();
        if (clickedBlockState.getBlockStateData().canContainLiquidSource()) {
            dimension.setBlockState(interactInfo.clickedBlockPos(), getLiquidType().getDefaultState(), 1);
            liquidPlacedPos = interactInfo.clickedBlockPos();
        } else {
            var blockOnPlacePos = dimension.getBlockState(event.getPlaceBlockPos());
            if (blockOnPlacePos.getBlockType() == BlockTypes.AIR || blockOnPlacePos.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 0);
            } else if (blockOnPlacePos.getBlockStateData().canContainLiquidSource()) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 1);
            }
        }

        var entity = getEntityType();
        if (entity != null) {
            var entityInstance = entity.createEntity(EntityInitInfo.builder().dimension(dimension).pos(MathUtils.center(liquidPlacedPos)).build());
            dimension.getEntityManager().addEntity(entityInstance);
        }

        dimension.addSound(liquidPlacedPos, new BucketEmptySound(getEmptySoundType()));

        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            // Filled bucket items are single-stack entries, so replace the
            // consumed held stack with the empty bucket for noncreative players.
            player.setItemInHand(ItemTypes.BUCKET.createItemStack(1));
        }
        event.setCanBeUsed(true);
    }

    /**
     * Selects the bucket-empty sound from the currently resolved contents.
     * An entity type takes precedence over lava and powder snow; all remaining
     * liquid types use the water-empty sound as the fallback.
     */
    protected BucketEmptySound.Type getEmptySoundType() {
        if (getEntityType() != null) {
            return BucketEmptySound.Type.FISH;
        }

        var liquidType = getLiquidType();
        if (liquidType == BlockTypes.LAVA) {
            return BucketEmptySound.Type.LAVA;
        }
        if (liquidType == BlockTypes.POWDER_SNOW) {
            return BucketEmptySound.Type.POWDER_SNOW;
        }
        // Contents without a dedicated lava, snow, or captured-entity sound use
        // the water sound so an allowed emptying still produces feedback.
        return BucketEmptySound.Type.WATER;
    }
}
