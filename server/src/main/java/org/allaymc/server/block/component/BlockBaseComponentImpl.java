package org.allaymc.server.block.component;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
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
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    @Manager
    protected ComponentManager manager;

    @Getter
    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        manager.callEvent(new CBlockOnNeighborUpdateEvent(context.block(), context.neighbor(), context.face()));
    }

    @Override
    public boolean canRandomUpdate() {
        var event = new CBlockTryRandomUpdateEvent();
        manager.callEvent(event);
        return event.canRandomUpdate();
    }

    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (getBlockType().getItemType() != null) {
            return Set.of(getSilkTouchDrop(block));
        }

        return Set.of();
    }

    @Override
    public double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        if (blockState.getBlockType() != getBlockType()) {
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
        }

        if (usedItem.canInstantBreak(blockState)) {
            return 0;
        }

        var blockHardness = blockState.getBlockStateData().hardness();
        if (blockHardness == -1) {
            return Integer.MAX_VALUE;
        }

        var isCorrectTool = usedItem.isCorrectToolFor(blockState);
        var requiresCorrectToolForDrops = blockState.getBlockStateData().requiresCorrectToolForDrops();

        var baseTime = ((isCorrectTool || !requiresCorrectToolForDrops) ? 1.5d : 5d) * blockHardness;
        var speed = 1d / baseTime;

        double efficiency = 1d;
        if (isCorrectTool) {
            // Tool level (wooden, stone, iron, etc...) bonus
            efficiency = usedItem.getBreakTimeBonus(blockState);
            // Tool efficiency enchantment bonus
            efficiency += speedBonusByEfficiency(usedItem.getEnchantmentLevel(EnchantmentTypes.EFFICIENCY));
        }

        if (isSword(usedItem.getItemType())) { // Special case
            efficiency *= 1.5d;
        }

        speed *= efficiency;

        if (entity != null) {
            if (entity instanceof EntityLiving living) {
                if (living.hasEffect(EffectTypes.HASTE) || living.hasEffect(EffectTypes.CONDUIT_POWER)) {
                    var level = Math.max(living.getEffectLevel(EffectTypes.HASTE), living.getEffectLevel(EffectTypes.CONDUIT_POWER));
                    speed *= (0.2d * level + 1) * Math.pow(1.2d, level);
                }

                // Entity mining fatigue effect negative bonus
                if (living.hasEffect(EffectTypes.MINING_FATIGUE)) {
                    // speedMultiplier *= 0.3 ^ miningFatigueLevel
                    // damage *= 0.7 ^ miningFatigueLevel
                    // 0.3 + 0.7 = 0.21 ^ miningFatigueLevel
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

            // In water but no underwater speed mining effect
            if (entity.isEyesInWater() && !hasAquaAffinity) {
                speed /= 5d;
            }

            // In air
            if (entity instanceof EntityPhysicsComponent physicsComponent && !physicsComponent.isOnGround()) {
                speed /= 5d;
            }
        }

        return Math.ceil(1d / speed * 20d) / 20d;
    }

    protected double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) {
            return 0;
        }

        return (efficiencyLevel * efficiencyLevel) + 1;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
    }

    protected BlockState processBlockProperties(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        for (var propertyType : blockState.getBlockType().getProperties().values()) {
            var processor = InternalRegistries.BLOCK_PROPERTY_PROCESSORS.get(propertyType);
            if (processor != null) {
                blockState = processor.process(blockState, placeBlockPos, placementInfo);
            }
        }
        return blockState;
    }

    @Override
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnPlaceEvent(block, newBlockState, placementInfo));
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterPlacedEvent(oldBlock, newBlockState, placementInfo));
    }

    @Override
    public void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnReplaceEvent(block, newBlockState, placementInfo));
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterReplacedEvent(oldBlock, newBlockState, placementInfo));
    }

    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterNeighborLayerReplaceEvent(currentBlock, newBlockState, placementInfo));
    }

    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        manager.callEvent(new CBlockOnPunchEvent(block, blockFace, usedItem, entity));
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (!isDroppable(block, usedItem, entity)) {
            return;
        }

        var dropPos = MathUtils.center(block.getPosition());
        var dimension = block.getDimension();
        if (usedItem != null && usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
            // Silk Touch, directly drop the block itself
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

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return false;
        }

        return !block.getBlockStateData().requiresCorrectToolForDrops() || (usedItem != null && usedItem.isCorrectToolFor(block.getBlockState()));
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new CBlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
