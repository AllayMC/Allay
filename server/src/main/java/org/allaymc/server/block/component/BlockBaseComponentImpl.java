package org.allaymc.server.block.component;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.*;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.registry.InternalRegistries;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.item.ItemHelper.isSword;

/**
 * {@code BlockBaseComponentImpl} supplies the shared server lifecycle implementation
 * for block types and routes component hooks through an injected manager. Specialized
 * base components inherit ordinary placement, interaction dispatch, drop eligibility,
 * and mining calculations. Placement processes registered property handlers before
 * writing the state, while lifecycle callbacks expose the same context to companion
 * components through internal block events.
 *
 * <p>
 * Mining speed combines block hardness, tool suitability, enchantments, entity
 * effects, and environmental penalties before rounding the duration to game ticks.
 * The drop path asks the virtual {@code isDroppable} hook for eligibility before
 * selecting silk touch or ordinary items and experience. The default hook rejects
 * creative players and enforces tool requirements; subclasses can replace that
 * policy. These operations remain separate so subclasses can specialize drops or
 * contact behavior without replacing the common placement and component-event
 * integration.
 *
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {
    /**
     * {@code IDENTIFIER} names the default block base component when component
     * providers and dependency injection identify its implementation.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    /**
     * {@code manager} is injected before component events are dispatched and routes
     * lifecycle hooks to this behavior's companion components.
     */
    @Manager
    protected ComponentManager manager;

    /**
     * Block type owning this behavior.
     */
    @Getter
    protected BlockType<? extends BlockBehavior> blockType;

    /**
     * Creates the shared base behavior for the supplied block type.
     */
    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    /**
     * Dispatches a neighbor update to this behavior's component handlers.
     * {@code oldNeighborState} is accepted by the public hook but is not included in
     * the internal neighbor event constructed here.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        manager.callEvent(new CBlockOnNeighborUpdateEvent(block, neighbor, face));
    }

    /**
     * Returns whether component handlers request random block updates for this
     * behavior. The internal request event begins with {@code false}, and handlers
     * can replace that flag before this method returns.
     */
    @Override
    public boolean canRandomUpdate() {
        var event = new CBlockTryRandomUpdateEvent();
        manager.callEvent(event);
        return event.canRandomUpdate();
    }

    /**
     * Dispatches the supplied block's random update to companion component handlers.
     */
    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));
    }

    /**
     * Returns this block's silk touch item as its ordinary default drop. Block types
     * without an associated item return an empty set, and subclasses may override the
     * drop selection.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (getBlockType().getItemType() != null) {
            return Set.of(getSilkTouchDrop(block));
        }

        return Set.of();
    }

    /**
     * Calculates a tick-rounded breaking duration in seconds for this block type.
     * {@code usedItem} must be non-{@code null}; callers use an air stack for an
     * empty hand, while {@code entity} may be absent. A state belonging to another
     * block type throws {@code IllegalArgumentException}.
     *
     * <p>
     * Instant-breaking items return {@code 0} before hardness is considered. Hardness
     * {@code -1} returns {@code Integer.MAX_VALUE}; otherwise tool suitability
     * chooses the hardness multiplier and enables tool and efficiency bonuses.
     * Living-entity effects modify speed, and water without aqua affinity and
     * airborne mining each apply an additional penalty. The final ceiling rounds
     * upward to twentieths of a second.
     */
    @Override
    public double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        if (blockState.getBlockType() != getBlockType()) {
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
        }

        if (usedItem.canInstantBreak(blockState)) {
            return 0;
        }

        var blockHardness = blockState.getBlockStateData().hardness();
        // Negative-one hardness represents an unbreakable block. Return the sentinel
        // before speed modifiers so tool and entity bonuses cannot shorten its duration.
        if (blockHardness == -1) {
            return Integer.MAX_VALUE;
        }

        var isCorrectTool = usedItem.isCorrectToolFor(blockState);
        var requiresCorrectToolForDrops = blockState.getBlockStateData().requiresCorrectToolForDrops();

        // Correct tools and blocks without tool-gated drops use the faster hardness
        // multiplier; other tool choices retain the slower mining baseline.
        var baseTime = ((isCorrectTool || !requiresCorrectToolForDrops) ? 1.5d : 5d) * blockHardness;
        var speed = 1d / baseTime;

        double efficiency = 1d;
        if (isCorrectTool) {
            efficiency = usedItem.getBreakTimeBonus(blockState);
            efficiency += speedBonusByEfficiency(usedItem.getEnchantmentLevel(EnchantmentTypes.EFFICIENCY));
        }

        // The sword modifier also applies after the correct-tool branch, so sword
        // mining receives this factor regardless of tool suitability.
        if (isSword(usedItem.getItemType())) {
            efficiency *= 1.5d;
        }

        speed *= efficiency;

        if (entity != null) {
            if (entity instanceof EntityLiving living) {
                if (living.hasEffect(EffectTypes.HASTE) || living.hasEffect(EffectTypes.CONDUIT_POWER)) {
                    // Haste and conduit power contribute only their stronger level
                    // here, avoiding multiplication of two overlapping mining-speed
                    // effects.
                    var level = Math.max(living.getEffectLevel(EffectTypes.HASTE), living.getEffectLevel(EffectTypes.CONDUIT_POWER));
                    speed *= (0.2d * level + 1) * Math.pow(1.2d, level);
                }
                if (living.hasEffect(EffectTypes.MINING_FATIGUE)) {
                    // Mining fatigue applies an exponential penalty per effect level,
                    // compounding with the later water and airborne speed reductions.
                    speed *= Math.pow(0.21d, living.getEffectLevel(EffectTypes.MINING_FATIGUE));
                }
            }

            var hasAquaAffinity = false;
            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(ContainerTypes.ARMOR)) {
                    hasAquaAffinity = containerHolder
                            .getContainer(ContainerTypes.ARMOR)
                            .getHelmet()
                            .hasEnchantment(EnchantmentTypes.AQUA_AFFINITY);
                }
            }
            // Aqua affinity bypasses the underwater penalty, while the independent
            // airborne check still applies when the entity is off the ground.
            if (entity.isEyesInWater() && !hasAquaAffinity) {
                speed /= 5d;
            }
            if (entity instanceof EntityPhysicsComponent physicsComponent && !physicsComponent.isOnGround()) {
                speed /= 5d;
            }
        }

        // Round seconds upward to game ticks so the computed mining duration never
        // ends between ticks.
        return Math.ceil(1d / speed * 20d) / 20d;
    }

    /**
     * Returns the additive efficiency enchantment bonus added to correct-tool speed
     * in the mining calculation. Level {@code 0} contributes no bonus; other levels
     * contribute their squared value plus one.
     */
    protected double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) {
            return 0;
        }

        return (efficiencyLevel * efficiencyLevel) + 1;
    }

    /**
     * Processes placement properties and writes the resulting state to the dimension.
     * {@code placementInfo} may be {@code null}; the dimension's state-write result
     * reports whether the placement was accepted.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
    }

    /**
     * Applies registered property processors to the supplied state before placement.
     * Each processor receives the preceding processor's result, and properties
     * without a registered processor leave the evolving state unchanged.
     */
    protected BlockState processBlockProperties(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        for (var propertyType : blockState.getBlockType().getProperties().values()) {
            var processor = InternalRegistries.BLOCK_PROPERTY_PROCESSORS.get(propertyType);
            if (processor != null) {
                blockState = processor.process(blockState, placeBlockPos, placementInfo);
            }
        }
        return blockState;
    }

    /**
     * Dispatches the incoming block's preplacement hook to companion components. The
     * old block snapshot and optional {@code placementInfo} are forwarded before the
     * dimension at this position stores {@code newBlockState}.
     */
    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnPlaceEvent(block, newBlockState, placementInfo));
    }

    /**
     * Dispatches the incoming block's postplacement hook to companion components.
     * {@code oldBlock} retains the outgoing block snapshot, and {@code placementInfo}
     * may be {@code null} for writes without player context.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterPlacedEvent(oldBlock, newBlockState, placementInfo));
    }

    /**
     * Dispatches the outgoing block's prereplacement hook to companion components.
     * The dimension has not stored the incoming {@code newBlockState} yet, and
     * {@code placementInfo} may be {@code null} for unattributed writes.
     */
    @Override
    public void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnReplaceEvent(block, newBlockState, placementInfo));
    }

    /**
     * Dispatches the outgoing block's postreplacement hook to companion components.
     * {@code oldBlock} retains the previous snapshot after the new state is stored,
     * and {@code placementInfo} may be {@code null}.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterReplacedEvent(oldBlock, newBlockState, placementInfo));
    }

    /**
     * Dispatches another layer's replacement to this behavior's companion components.
     * {@code currentBlock} describes the changed layer's old snapshot and
     * {@code placementInfo} may be {@code null} when no player initiated the write.
     */
    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterNeighborLayerReplaceEvent(currentBlock, newBlockState, placementInfo));
    }

    /**
     * Dispatches a punch with its face, item, and entity to companion components.
     * {@code usedItem} and {@code entity} may be {@code null} when the associated
     * item or responsible entity is absent.
     */
    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        manager.callEvent(new CBlockOnPunchEvent(block, blockFace, usedItem, entity));
    }

    /**
     * Drops the items and experience selected by this behavior when drop eligibility
     * permits. A silk touch item selects {@code getSilkTouchDrop} and returns before
     * ordinary items or experience are emitted.
     *
     * <p>
     * Ordinary breaks call {@code getDrops} and then {@code getDropXpAmount}.
     * {@code usedItem} and {@code entity} may be {@code null}; the default
     * {@code isDroppable} implementation rejects creative players and enforces
     * required tools. Overrides can permit other drop policies.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (!isDroppable(block, usedItem, entity)) {
            return;
        }

        var dropPos = MathUtils.center(block.getPosition());
        var dimension = block.getDimension();
        if (usedItem != null && usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
            // Silk touch selects a single specialized block drop and skips ordinary
            // drops and experience, even when subclasses override those hooks.
            dimension.dropItem(getSilkTouchDrop(block), dropPos);
            return;
        }

        var drops = getDrops(block, usedItem, entity);
        for (var drop : drops) {
            dimension.dropItem(drop, dropPos);
        }

        var dropXpAmount = getDropXpAmount(block, usedItem, entity);
        if (dropXpAmount > 0) {
            dimension.dropXpOrb(dropPos, dropXpAmount);
        }
    }

    /**
     * Returns whether this break passes creative-mode and required-tool checks. A
     * {@code null} tool is accepted only for blocks without a correct-tool
     * requirement, and a {@code null} entity is allowed.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return false;
        }

        return !block.getBlockStateData().requiresCorrectToolForDrops() || (usedItem != null && usedItem.isCorrectToolFor(block.getBlockState()));
    }

    /**
     * Dispatches an item interaction and returns the internal event's success flag.
     * The event starts unsuccessful, so companion components must mark a handled
     * interaction before the result becomes {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new CBlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
