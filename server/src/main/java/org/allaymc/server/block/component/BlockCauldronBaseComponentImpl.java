package org.allaymc.server.block.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.enums.CauldronLiquid;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent.CauldronPotionType;
import org.allaymc.api.blockentity.interfaces.BlockEntityCauldron;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.item.interfaces.ItemLingeringPotionStack;
import org.allaymc.api.item.interfaces.ItemShulkerBoxStack;
import org.allaymc.api.item.interfaces.ItemSplashPotionStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.CAULDRON_LIQUID;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.FILL_LEVEL;

/**
 * Implementation of cauldron block behavior.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockCauldronBaseComponentImpl extends BlockBaseComponentImpl {

    public static final int MAX_FILL_LEVEL = 6;
    public static final int FILL_LEVEL_PER_BOTTLE = 2;

    @Dependency
    protected BlockBlockEntityHolderComponent<BlockEntityCauldron> blockEntityHolderComponent;

    public BlockCauldronBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();
        if (interactInfo == null) return;

        var player = interactInfo.player();
        if (player == null) return;

        var dimension = event.getDimension();
        var pos = interactInfo.clickedBlockPos();
        var clickedBlock = dimension.getBlockState(pos);
        var itemStack = event.getItemStack();

        var liquid = clickedBlock.getPropertyValue(CAULDRON_LIQUID);
        var fillLevel = clickedBlock.getPropertyValue(FILL_LEVEL);
        boolean isEmpty = fillLevel == 0;
        boolean isFull = fillLevel == MAX_FILL_LEVEL;

        // Handle bucket interactions
        if (handleBucketInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, isEmpty, isFull)) {
            event.setSuccess(true);
            return;
        }

        // Handle glass bottle interactions
        if (handleBottleInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        // Handle potion interactions
        if (handlePotionInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isFull)) {
            event.setSuccess(true);
            return;
        }

        // Handle dye interactions (water only)
        if (handleDyeInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        // Handle leather armor interactions (water only)
        if (handleLeatherArmorInteraction(dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        // Handle banner cleaning (water only)
        if (handleBannerInteraction(dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        // Handle shulker box cleaning (water only)
        if (handleShulkerBoxInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        // Handle arrow tipping (potion cauldron only)
        if (handleArrowInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
        }
    }

    protected boolean handleBucketInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, boolean isEmpty, boolean isFull
    ) {
        var itemType = itemStack.getItemType();

        // Empty bucket - pick up liquid
        if (itemType == ItemTypes.BUCKET && isFull) {
            ItemStack filledBucket;
            switch (liquid) {
                case WATER -> filledBucket = ItemTypes.WATER_BUCKET.createItemStack(1);
                case LAVA -> filledBucket = ItemTypes.LAVA_BUCKET.createItemStack(1);
                case POWDER_SNOW -> filledBucket = ItemTypes.POWDER_SNOW_BUCKET.createItemStack(1);
                default -> {
                    return false;
                }
            }

            // Clear potion data if present
            clearPotionData(dimension, pos);

            player.tryConsumeItemInHand();
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(filledBucket);

            setFillLevel(dimension, pos, clickedBlock, 0, liquid);
            var takeSound = switch (liquid) {
                case LAVA -> SimpleSound.CAULDRON_TAKE_LAVA;
                case POWDER_SNOW -> SimpleSound.CAULDRON_TAKE_POWDER_SNOW;
                default -> SimpleSound.CAULDRON_TAKE_WATER;
            };
            dimension.addSound(MathUtils.center(pos), takeSound);

            return true;
        }

        // Water bucket - fill cauldron
        if (itemType == ItemTypes.WATER_BUCKET) {
            if (liquid == CauldronLiquid.LAVA && !isEmpty) {
                // Water into lava causes fizz and empties
                clearWithFizz(dimension, pos, clickedBlock);
            } else {
                clearPotionData(dimension, pos);
                setFillLevelAndLiquid(dimension, pos, clickedBlock, MAX_FILL_LEVEL, CauldronLiquid.WATER);
            }
            consumeBucket(player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_WATER);
            return true;
        }

        // Lava bucket - fill cauldron (only if empty or already lava)
        if (itemType == ItemTypes.LAVA_BUCKET) {
            if (!isEmpty && liquid != CauldronLiquid.LAVA) {
                // Lava into non-lava causes fizz and empties
                clearWithFizz(dimension, pos, clickedBlock);
            } else {
                clearPotionData(dimension, pos);
                setFillLevelAndLiquid(dimension, pos, clickedBlock, MAX_FILL_LEVEL, CauldronLiquid.LAVA);
            }
            consumeBucket(player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_LAVA);
            return true;
        }

        // Powder snow bucket - fill cauldron
        if (itemType == ItemTypes.POWDER_SNOW_BUCKET) {
            if (liquid == CauldronLiquid.LAVA && !isEmpty) {
                clearWithFizz(dimension, pos, clickedBlock);
            } else {
                clearPotionData(dimension, pos);
                setFillLevelAndLiquid(dimension, pos, clickedBlock, MAX_FILL_LEVEL, CauldronLiquid.POWDER_SNOW);
            }
            consumeBucket(player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_POWDER_SNOW);
            return true;
        }

        return false;
    }

    protected boolean handleBottleInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (itemStack.getItemType() != ItemTypes.GLASS_BOTTLE) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < FILL_LEVEL_PER_BOTTLE) return false;

        ItemStack resultItem;
        var blockEntity = getBlockEntityIfExists(dimension, pos);

        if (blockEntity != null && blockEntity.hasPotion()) {
            // Get potion from cauldron
            var cauldronPotionType = blockEntity.getCauldronPotionType();
            var storedPotionType = blockEntity.getPotionType();

            resultItem = switch (cauldronPotionType) {
                case SPLASH -> ItemTypes.SPLASH_POTION.createItemStack(1);
                case LINGERING -> ItemTypes.LINGERING_POTION.createItemStack(1);
                default -> ItemTypes.POTION.createItemStack(1);
            };

            if (resultItem instanceof ItemPotionComponent potionItem) {
                potionItem.setPotionType(storedPotionType);
            }

            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_TAKE_POTION);
        } else {
            // Get water bottle
            resultItem = ItemTypes.POTION.createItemStack(1);
            if (resultItem instanceof ItemPotionComponent potionItem) {
                potionItem.setPotionType(PotionType.WATER);
            }
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_TAKE_WATER);
        }

        int newLevel = fillLevel - FILL_LEVEL_PER_BOTTLE;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        player.tryConsumeItemInHand();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(resultItem);

        return true;
    }

    protected boolean handlePotionInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isFull
    ) {
        if (!(itemStack instanceof ItemPotionComponent potionItem)) return false;
        if (isFull) return false;

        // Can only add potion to water cauldron or empty cauldron
        if (fillLevel > 0 && liquid != CauldronLiquid.WATER) return false;

        var potionType = potionItem.getPotionType();

        // Determine cauldron potion type
        CauldronPotionType cauldronPotionType;
        if (itemStack instanceof ItemLingeringPotionStack) {
            cauldronPotionType = CauldronPotionType.LINGERING;
        } else if (itemStack instanceof ItemSplashPotionStack) {
            cauldronPotionType = CauldronPotionType.SPLASH;
        } else {
            cauldronPotionType = CauldronPotionType.NORMAL;
        }

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (fillLevel > 0 && blockEntity != null && blockEntity.hasPotion()) {
            // Check if potion type matches
            if (blockEntity.getPotionType() != potionType || blockEntity.getCauldronPotionType() != cauldronPotionType) {
                // Different potion, causes explosion (clear cauldron)
                clearWithFizz(dimension, pos, clickedBlock);
                giveBottle(player);
                return true;
            }
        }

        // Add potion to cauldron
        int newLevel = Math.min(fillLevel + FILL_LEVEL_PER_BOTTLE, MAX_FILL_LEVEL);

        if (fillLevel == 0) {
            setFillLevelAndLiquid(dimension, pos, clickedBlock, newLevel, CauldronLiquid.WATER);
        } else {
            setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);
        }

        // Set potion data in block entity
        var cauldronBlockEntity = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);
        cauldronBlockEntity.setPotionType(potionType);
        cauldronBlockEntity.setCauldronPotionType(cauldronPotionType);

        // Set custom color from potion
        cauldronBlockEntity.setCustomColor(potionType.getColor());

        giveBottle(player);
        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_POTION);

        return true;
    }

    protected boolean handleDyeInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack, CauldronLiquid liquid, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemDyeComponent dyeItem)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;

        // Check if there's a potion - can't dye potion water
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && blockEntity.hasPotion()) return false;

        // Get or create block entity and mix color
        var cauldronBlockEntity = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);
        cauldronBlockEntity.mixColor(dyeItem.getDyeColor().getColor());

        player.tryConsumeItemInHand();
        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_ADD_DYE);

        return true;
    }

    protected boolean handleLeatherArmorInteraction(
            Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        // Check if item has dyeable component
        if (!(itemStack instanceof ItemDyeableComponent dyeableComponent)) {
            return false;
        }

        var blockEntity = getBlockEntityIfExists(dimension, pos);

        if (blockEntity != null && blockEntity.hasCustomColor()) {
            // Dye the armor with colored water
            dyeableComponent.setCustomColor(blockEntity.getCustomColor());
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_DYE_ARMOR);
        } else {
            // Clean the armor - only if armor has color to clean
            if (!dyeableComponent.hasCustomColor()) {
                return false;
            }
            dyeableComponent.clearCustomColor();
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_ARMOR);
        }

        // Armor dyeing/cleaning consumes 1 level
        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        return true;
    }

    protected boolean handleBannerInteraction(
            Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemBannerBaseComponent bannerItem)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        // Check if banner has patterns to clean
        if (bannerItem.getPatternCount() == 0) return false;

        // Check that water is not dyed or has potion
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && (blockEntity.hasPotion() || blockEntity.hasCustomColor())) {
            return false;
        }

        // Remove the last pattern
        bannerItem.removePattern(bannerItem.getPatternCount() - 1);

        // Banner cleaning consumes 1 level
        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_BANNER);

        return true;
    }

    protected boolean handleShulkerBoxInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemShulkerBoxStack)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        // Check that water is not dyed or has potion
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && (blockEntity.hasPotion() || blockEntity.hasCustomColor())) {
            return false;
        }

        // Check if it's a dyed shulker box (not undyed)
        var itemType = itemStack.getItemType();
        if (itemType == ItemTypes.UNDYED_SHULKER_BOX) return false;

        // Convert to undyed shulker box
        var undyedBox = ItemTypes.UNDYED_SHULKER_BOX.createItemStack(1);
        // TODO: Copy stored items when ItemStuffStorableComponent is fully implemented

        player.setItemInHand(undyedBox);

        // Shulker box cleaning consumes 1 level
        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        // No specific sound for shulker box cleaning
        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_ARMOR);

        return true;
    }

    protected boolean handleArrowInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemArrowStack arrowStack)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;

        // Check if cauldron contains a potion
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity == null || !blockEntity.hasPotion()) return false;

        // Arrow must not already be tipped
        if (arrowStack.getPotionType() != null) return false;

        int arrowCount = itemStack.getCount();
        // Calculate how many arrows can be tipped based on fill level
        // 2 levels = 16 arrows, 4 levels = 32 arrows, 6 levels = 64 arrows (full stack)
        int maxArrowsToTip = switch (fillLevel) {
            case 6 -> 64;
            case 4, 5 -> 32;
            case 2, 3 -> 16;
            default -> 0;
        };
        int arrowsToTip = Math.min(arrowCount, maxArrowsToTip);

        if (arrowsToTip <= 0) return false;

        // Calculate levels to consume (2 levels per 16 arrows, rounded up)
        int levelsToConsume = ((arrowsToTip + 15) / 16) * 2;
        levelsToConsume = Math.min(levelsToConsume, fillLevel);

        // Get potion type from block entity
        var potionType = blockEntity.getPotionType();

        // Create tipped arrows
        var tippedArrows = ItemTypes.ARROW.createItemStack(arrowsToTip);
        if (tippedArrows instanceof ItemArrowStack tippedArrowStack) {
            tippedArrowStack.setPotionType(potionType);
        }

        // Consume arrows from player's hand
        if (arrowsToTip >= arrowCount) {
            player.tryConsumeItemInHand();
        } else {
            itemStack.setCount(arrowCount - arrowsToTip);
        }

        // Give tipped arrows to player
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(tippedArrows);

        // Reduce fill level
        int newLevel = fillLevel - levelsToConsume;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_TAKE_POTION);

        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var world = dimension.getWorld();
        var weather = world.getWeather();

        // Only collect rain in rain or thunder weather
        if (weather != Weather.RAIN && weather != Weather.THUNDER) return;

        // Check if block is exposed to sky
        if (!dimension.canPosSeeSky(pos.x(), pos.y() + 1, pos.z())) return;

        var blockState = block.getBlockState();
        var liquid = blockState.getPropertyValue(CAULDRON_LIQUID);
        var fillLevel = blockState.getPropertyValue(FILL_LEVEL);

        // Can only collect water into empty or water cauldrons
        if (fillLevel > 0 && liquid != CauldronLiquid.WATER) return;

        // Check for potion - rain can't mix with potions
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && blockEntity.hasPotion()) return;

        // Random chance to collect rain (approximately 1/50 per random tick)
        if (ThreadLocalRandom.current().nextInt(50) != 0) return;

        // Already full
        if (fillLevel >= MAX_FILL_LEVEL) return;

        // Increase water level
        if (fillLevel == 0) {
            setFillLevelAndLiquid(dimension, pos, blockState, 1, CauldronLiquid.WATER);
        } else {
            setFillLevel(dimension, pos, blockState, fillLevel + 1, liquid);
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        var liquid = blockState.getPropertyValue(CAULDRON_LIQUID);
        var fillLevel = blockState.getPropertyValue(FILL_LEVEL);

        if (fillLevel == 0) return;

        // Only living entities can be affected by cauldron effects
        if (!(entity instanceof EntityLiving livingEntity)) return;

        switch (liquid) {
            case LAVA -> {
                // Lava cauldron sets entities on fire and damages them (doesn't consume lava)
                livingEntity.setOnFireTicks(15 * 20); // 15 seconds in ticks
                if (!livingEntity.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                    livingEntity.attack(DamageContainer.lava(4));
                }
            }
            case WATER -> {
                // Water cauldron extinguishes fire and consumes 1 level
                if (livingEntity.isOnFire()) {
                    livingEntity.extinguish();
                    int newLevel = fillLevel - 1;
                    setFillLevel(dimension, pos, blockState, newLevel, liquid);
                    if (newLevel == 0) {
                        clearPotionData(dimension, pos);
                    }
                }
            }
            case POWDER_SNOW -> {
                // Powder snow cauldron extinguishes fire and consumes 1 level
                // TODO: Implement freezing damage when supported
                if (livingEntity.isOnFire()) {
                    livingEntity.extinguish();
                    int newLevel = fillLevel - 1;
                    setFillLevel(dimension, pos, blockState, newLevel, liquid);
                }
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        // Vanilla outputs 0-3 (empty to full), fillLevel is 0-6
        return block.getPropertyValue(FILL_LEVEL) / 2;
    }

    protected void setFillLevel(Dimension dimension, Vector3ic pos,
                                BlockState currentState,
                                int newLevel, CauldronLiquid liquid) {
        var newState = currentState.setPropertyValue(FILL_LEVEL, newLevel);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);
        dimension.updateComparatorOutputLevel(pos);
    }

    protected void setFillLevelAndLiquid(Dimension dimension, Vector3ic pos,
                                         BlockState currentState,
                                         int newLevel, CauldronLiquid newLiquid) {
        var newState = currentState
                .setPropertyValue(FILL_LEVEL, newLevel)
                .setPropertyValue(CAULDRON_LIQUID, newLiquid);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);
        dimension.updateComparatorOutputLevel(pos);
    }

    protected void clearWithFizz(Dimension dimension, Vector3ic pos,
                                 BlockState currentState) {
        setFillLevelAndLiquid(dimension, pos, currentState, 0, CauldronLiquid.WATER);
        clearPotionData(dimension, pos);
        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.FIZZ);
        // Add smoke particles
        double cx = pos.x() + 0.5;
        double cy = pos.y() + 0.5;
        double cz = pos.z() + 0.5;
        for (int i = 0; i < 8; i++) {
            dimension.addParticle(cx, cy, cz, SimpleParticle.WHITE_SMOKE);
        }
    }

    protected void clearPotionData(Dimension dimension, Vector3ic pos) {
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null) {
            blockEntity.clearPotionData();
        }
    }

    protected BlockEntityCauldron getBlockEntityIfExists(Dimension dimension, Vector3ic pos) {
        var blockEntity = dimension.getBlockEntity(pos);
        if (blockEntity instanceof BlockEntityCauldron cauldron) {
            return cauldron;
        }
        return null;
    }

    protected void consumeBucket(EntityPlayer player) {
        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.BUCKET.createItemStack(1));
        }
    }

    protected void giveBottle(EntityPlayer player) {
        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.GLASS_BOTTLE.createItemStack(1));
        }
    }
}
