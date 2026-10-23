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
 * {@code BlockCauldronBaseComponentImpl} handles cauldron liquid transfers, item
 * coloring and cleaning, potion storage, weather filling, and entity contact
 * effects. The block state stores liquid kind and fill level, while the block
 * entity stores potion kind, potion identity, and custom color. A bottle accounts
 * for two of the six fill levels. {@code MAX_FILL_LEVEL} bounds capacity, while
 * {@code FILL_LEVEL_PER_BOTTLE} defines the amount added and removed by bottle
 * transfers; both constants use the same fill-level scale.
 *
 * <p>
 * Interaction handlers combine those two representations with the held item to
 * choose a transfer or transformation. Conflicting liquids or potion identities
 * can clear the contents with fizz, and emptying selected paths also clears stored
 * potion metadata. World, inventory, and metadata updates occur in order without
 * rollback; the handlers ignore item-insertion failures and block-write return
 * values.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockCauldronBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Maximum cauldron fill level, representing one full bucket.
     * {@code FILL_LEVEL_PER_BOTTLE} accounts for bottle transfers within this
     * six-level capacity.
     */
    public static final int MAX_FILL_LEVEL = 6;
    /**
     * Fill levels transferred by one bottle. Bottle extraction and potion
     * insertion subtract or add this amount while respecting
     * {@code MAX_FILL_LEVEL}.
     */
    public static final int FILL_LEVEL_PER_BOTTLE = 2;

    /**
     * {@code blockEntityHolderComponent} creates or resolves cauldron metadata for
     * successful potion and dye operations. Component initialization injects this
     * dependency before interaction handlers run.
     */
    @Dependency
    protected BlockBlockEntityHolderComponent<BlockEntityCauldron> blockEntityHolderComponent;

    /**
     * Creates a cauldron interaction component for the supplied block type.
     */
    public BlockCauldronBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches a player's cauldron interaction to the first applicable handler.
     * Missing interaction information or a missing player leaves the component
     * event unchanged. The handler order gives buckets precedence over bottles and
     * potions, followed by dyeing, item cleaning, and arrow tipping. Each
     * successful handler marks the event successful and stops further dispatch, so
     * overlapping item capabilities cannot perform multiple cauldron operations
     * for the same interaction.
     */
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

        // Choose one transfer from the captured state, then stop after its first
        // success so later handlers cannot consume or transform the same held item
        // again.
        if (handleBucketInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, isEmpty, isFull)) {
            event.setSuccess(true);
            return;
        }

        if (handleBottleInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        if (handlePotionInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isFull)) {
            event.setSuccess(true);
            return;
        }

        if (handleDyeInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        if (handleLeatherArmorInteraction(dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        if (handleBannerInteraction(dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        if (handleShulkerBoxInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
            return;
        }

        if (handleArrowInteraction(player, dimension, pos, clickedBlock, itemStack, liquid, fillLevel, isEmpty)) {
            event.setSuccess(true);
        }
    }

    /**
     * Transfers a full bucket into or out of the cauldron when the item and
     * captured liquid state permit it. Empty buckets require a full water, lava,
     * or powder-snow cauldron. Filled buckets replace compatible contents, while
     * conflicting lava mixtures clear the cauldron with fizz. Metadata clearing,
     * world writes, item consumption, and inventory insertion are separate
     * effects; a later failure does not restore earlier changes, and successful
     * handling returns {@code true}.
     */
    protected boolean handleBucketInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, boolean isEmpty, boolean isFull
    ) {
        var itemType = itemStack.getItemType();

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

        if (itemType == ItemTypes.WATER_BUCKET) {
            if (liquid == CauldronLiquid.LAVA && !isEmpty) {
                clearWithFizz(dimension, pos, clickedBlock);
            } else {
                clearPotionData(dimension, pos);
                setFillLevelAndLiquid(dimension, pos, clickedBlock, MAX_FILL_LEVEL, CauldronLiquid.WATER);
            }
            consumeBucket(player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_WATER);
            return true;
        }

        if (itemType == ItemTypes.LAVA_BUCKET) {
            if (!isEmpty && liquid != CauldronLiquid.LAVA) {
                clearWithFizz(dimension, pos, clickedBlock);
            } else {
                clearPotionData(dimension, pos);
                setFillLevelAndLiquid(dimension, pos, clickedBlock, MAX_FILL_LEVEL, CauldronLiquid.LAVA);
            }
            consumeBucket(player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_LAVA);
            return true;
        }

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

    /**
     * Extracts one bottle from a water cauldron containing at least two fill
     * levels. Stored potion identity and delivery kind select a normal, splash, or
     * lingering potion item, while absent potion data selects a water bottle.
     *
     * <p>
     * The level decreases before the glass bottle is consumed and its replacement
     * is inserted. Reaching zero also clears potion data. Inventory insertion
     * failure leaves the preceding level and consumption changes intact because
     * this transfer has no rollback.
     */
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
            resultItem = ItemTypes.POTION.createItemStack(1);
            if (resultItem instanceof ItemPotionComponent potionItem) {
                potionItem.setPotionType(PotionType.WATER);
            }
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_TAKE_WATER);
        }

        // One extracted bottle spends two fill levels; reaching zero clears the
        // stored potion identity so the next fill does not inherit the emptied
        // potion.
        int newLevel = fillLevel - FILL_LEVEL_PER_BOTTLE;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        player.tryConsumeItemInHand();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(resultItem);

        return true;
    }

    /**
     * Adds a potion bottle to an empty or nonfull water cauldron. The input's
     * potion identity and delivery kind must match existing stored potion data
     * when the cauldron already contains a potion; a mismatch clears the cauldron
     * with fizz and returns a glass bottle.
     *
     * <p>
     * An accepted addition raises the level by two up to capacity, then writes
     * potion identity, delivery kind, and display color into the resolved block
     * entity. Those metadata writes follow the block-state write and remain
     * separate from the final inventory replacement.
     */
    protected boolean handlePotionInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isFull
    ) {
        if (!(itemStack instanceof ItemPotionComponent potionItem)) return false;
        if (isFull) return false;

        if (fillLevel > 0 && liquid != CauldronLiquid.WATER) return false;

        var potionType = potionItem.getPotionType();

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
            // Both potion identity and delivery kind must agree before combining
            // contents; mixing either attribute discards the incompatible potion
            // instead of merging its metadata.
            if (blockEntity.getPotionType() != potionType || blockEntity.getCauldronPotionType() != cauldronPotionType) {
                clearWithFizz(dimension, pos, clickedBlock);
                giveBottle(player);
                return true;
            }
        }

        // Cap a two-level bottle addition at the six-level capacity.
        int newLevel = Math.min(fillLevel + FILL_LEVEL_PER_BOTTLE, MAX_FILL_LEVEL);

        if (fillLevel == 0) {
            setFillLevelAndLiquid(dimension, pos, clickedBlock, newLevel, CauldronLiquid.WATER);
        } else {
            setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);
        }

        var cauldronBlockEntity = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);
        // The liquid-level write precedes potion metadata updates. Resolve the
        // holder after that write so metadata is assigned to the cauldron entity
        // at the updated position.
        cauldronBlockEntity.setPotionType(potionType);
        cauldronBlockEntity.setCauldronPotionType(cauldronPotionType);

        cauldronBlockEntity.setCustomColor(potionType.getColor());

        giveBottle(player);
        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_FILL_POTION);

        return true;
    }

    /**
     * Mixes the held dye into a nonempty water cauldron without stored potion
     * data. The operation consumes the held dye through the player helper,
     * preserves the fill level, and plays the dye sound.
     */
    protected boolean handleDyeInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack, CauldronLiquid liquid, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemDyeComponent dyeItem)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && blockEntity.hasPotion()) return false;

        var cauldronBlockEntity = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);
        cauldronBlockEntity.mixColor(dyeItem.getDyeColor().getColor());

        player.tryConsumeItemInHand();
        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_ADD_DYE);

        return true;
    }

    /**
     * Dyes or cleans a mutable dyeable item using a nonempty water cauldron. A
     * stored custom color replaces the item's color; otherwise an already colored
     * item is cleared. Success consumes one fill level and clears potion data when
     * the cauldron becomes empty.
     */
    protected boolean handleLeatherArmorInteraction(
            Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        if (!(itemStack instanceof ItemDyeableComponent dyeableComponent)) {
            return false;
        }

        var blockEntity = getBlockEntityIfExists(dimension, pos);

        if (blockEntity != null && blockEntity.hasCustomColor()) {
            dyeableComponent.setCustomColor(blockEntity.getCustomColor());
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_DYE_ARMOR);
        } else {
            if (!dyeableComponent.hasCustomColor()) {
                return false;
            }
            dyeableComponent.clearCustomColor();
            dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_ARMOR);
        }

        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        return true;
    }

    /**
     * Removes the last banner pattern using one fill level of plain water. Empty
     * banners, potion contents, and custom-colored water are rejected before the
     * supplied banner item is modified in place.
     */
    protected boolean handleBannerInteraction(
            Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemBannerBaseComponent bannerItem)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        if (bannerItem.getPatternCount() == 0) return false;

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && (blockEntity.hasPotion() || blockEntity.hasCustomColor())) {
            return false;
        }

        bannerItem.removePattern(bannerItem.getPatternCount() - 1);

        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_BANNER);

        return true;
    }

    /**
     * Replaces a colored held shulker box with an undyed box using plain water.
     * The cauldron must contain at least one level and have neither potion data
     * nor a custom color. The replacement receives the stored items from the
     * original box; other original item metadata is not copied by this method. The
     * player's hand is replaced before the fill level decreases, and later
     * failures have no compensating hand update.
     */
    protected boolean handleShulkerBoxInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemShulkerBoxStack shulkerBox)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;
        if (fillLevel < 1) return false;

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && (blockEntity.hasPotion() || blockEntity.hasCustomColor())) {
            return false;
        }

        var itemType = itemStack.getItemType();
        if (itemType == ItemTypes.UNDYED_SHULKER_BOX) return false;

        // Changing the item type requires a new undyed stack. Transfer its stored
        // contents explicitly because creating the replacement alone would leave
        // those slots empty.
        var undyedBox = ItemTypes.UNDYED_SHULKER_BOX.createItemStack(1);
        undyedBox.setStoredItems(shulkerBox.getStoredItems());

        player.setItemInHand(undyedBox);

        int newLevel = fillLevel - 1;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_CLEAN_ARMOR);

        return true;
    }

    /**
     * Creates potion-tipped arrows from an untipped held stack and stored cauldron
     * potion data. Fill levels two or three permit sixteen arrows, four or five
     * permit thirty-two, and six permits sixty-four. The selected count is capped
     * by the held stack.
     *
     * <p>
     * Liquid cost rounds that count up in groups of sixteen, costs two levels per
     * group, and is capped by the available level. The partial-stack path
     * subtracts the selected count directly; the whole-selected-stack path calls
     * {@code tryConsumeItemInHand}, which consumes one item outside creative mode.
     * The created output count still equals the full selected count, and inventory
     * insertion failure is ignored.
     */
    protected boolean handleArrowInteraction(
            EntityPlayer player, Dimension dimension, Vector3ic pos,
            BlockState clickedBlock, ItemStack itemStack,
            CauldronLiquid liquid, int fillLevel, boolean isEmpty
    ) {
        if (!(itemStack instanceof ItemArrowStack arrowStack)) return false;
        if (isEmpty || liquid != CauldronLiquid.WATER) return false;

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity == null || !blockEntity.hasPotion()) return false;

        if (arrowStack.getPotionType() != null) return false;

        int arrowCount = itemStack.getCount();
        // The fill bands select output batch limits rather than a linear per-level
        // ratio, allowing a full cauldron to tip one sixty-four-arrow stack.
        int maxArrowsToTip = switch (fillLevel) {
            case 6 -> 64;
            case 4, 5 -> 32;
            case 2, 3 -> 16;
            default -> 0;
        };
        int arrowsToTip = Math.min(arrowCount, maxArrowsToTip);

        if (arrowsToTip <= 0) return false;

        // Round any partial sixteen-arrow batch up to two fill levels.
        int levelsToConsume = ((arrowsToTip + 15) / 16) * 2;
        levelsToConsume = Math.min(levelsToConsume, fillLevel);

        var potionType = blockEntity.getPotionType();

        var tippedArrows = ItemTypes.ARROW.createItemStack(arrowsToTip);
        if (tippedArrows instanceof ItemArrowStack tippedArrowStack) {
            tippedArrowStack.setPotionType(potionType);
        }

        if (arrowsToTip >= arrowCount) {
            player.tryConsumeItemInHand();
        } else {
            itemStack.setCount(arrowCount - arrowsToTip);
        }

        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(tippedArrows);

        int newLevel = fillLevel - levelsToConsume;
        setFillLevel(dimension, pos, clickedBlock, newLevel, liquid);

        if (newLevel == 0) {
            clearPotionData(dimension, pos);
        }

        dimension.addSound(MathUtils.center(pos), SimpleSound.CAULDRON_TAKE_POTION);

        return true;
    }

    /**
     * Attempts rain filling after shared random-update handling. Rain or thunder,
     * visible sky, compatible liquid, absent potion data, available capacity, and
     * the one-in-fifty roll must all permit the update. An empty cauldron becomes
     * water with one fill level.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var world = dimension.getWorld();
        var weather = world.getWeather();

        if (weather != Weather.RAIN && weather != Weather.THUNDER) return;

        if (!dimension.canPosSeeSky(pos.x(), pos.y() + 1, pos.z())) return;

        var blockState = block.getBlockState();
        var liquid = blockState.getPropertyValue(CAULDRON_LIQUID);
        var fillLevel = blockState.getPropertyValue(FILL_LEVEL);

        if (fillLevel > 0 && liquid != CauldronLiquid.WATER) return;

        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null && blockEntity.hasPotion()) return;

        // Sample rain collection once per fifty eligible random updates on
        // average.
        if (ThreadLocalRandom.current().nextInt(50) != 0) return;

        if (fillLevel >= MAX_FILL_LEVEL) return;

        if (fillLevel == 0) {
            setFillLevelAndLiquid(dimension, pos, blockState, 1, CauldronLiquid.WATER);
        } else {
            setFillLevel(dimension, pos, blockState, fillLevel + 1, liquid);
        }
    }

    /**
     * Returns whether this cauldron participates in random block updates.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Applies the nonempty cauldron's contact effect to living entities. Lava
     * ignites for fifteen seconds and attacks entities without fire resistance;
     * water and powder snow extinguish burning entities while consuming one level.
     * Water depletion also clears potion data.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        var liquid = blockState.getPropertyValue(CAULDRON_LIQUID);
        var fillLevel = blockState.getPropertyValue(FILL_LEVEL);

        if (fillLevel == 0) return;

        if (!(entity instanceof EntityLiving livingEntity)) return;

        switch (liquid) {
            case LAVA -> {
                // Convert the fifteen-second ignition duration into twenty-tick
                // seconds.
                livingEntity.setOnFireTicks(15 * 20); // 15 seconds in ticks
                if (!livingEntity.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                    // Cauldron lava applies four damage points per accepted
                    // contact attack.
                    livingEntity.attack(DamageContainer.lava(4));
                }
            }
            case WATER -> {
                if (livingEntity.isOnFire()) {
                    livingEntity.extinguish();
                    int newLevel = fillLevel - 1;
                    setFillLevel(dimension, pos, blockState, newLevel, liquid);
                    if (newLevel == 0) {
                        clearPotionData(dimension, pos);
                    }
                }
            }
            // Powder snow currently extinguishes fire and spends one fill level.
            // Add freezing damage here when a supported freezing-damage mechanism
            // is available.
            case POWDER_SNOW -> {
                if (livingEntity.isOnFire()) {
                    livingEntity.extinguish();
                    int newLevel = fillLevel - 1;
                    setFillLevel(dimension, pos, blockState, newLevel, liquid);
                }
            }
        }
    }

    /**
     * Returns whether this cauldron supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the fill level divided by two for comparator output.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        // Two fill levels form one comparator step; odd levels truncate downward.
        return block.getPropertyValue(FILL_LEVEL) / 2;
    }

    /**
     * Writes the supplied fill level and then refreshes comparator output. The
     * write retains every other property from {@code currentState}; {@code liquid}
     * is accepted but unused, and write failure is ignored.
     */
    protected void setFillLevel(Dimension dimension, Vector3ic pos,
                                BlockState currentState,
                                int newLevel, CauldronLiquid liquid) {
        var newState = currentState.setPropertyValue(FILL_LEVEL, newLevel);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);
        dimension.updateComparatorOutputLevel(pos);
    }

    /**
     * Writes the supplied level and liquid kind before refreshing comparator
     * output. The state derives from {@code currentState}, and this method ignores
     * the dimension write's success result.
     */
    protected void setFillLevelAndLiquid(Dimension dimension, Vector3ic pos,
                                         BlockState currentState,
                                         int newLevel, CauldronLiquid newLiquid) {
        var newState = currentState
                .setPropertyValue(FILL_LEVEL, newLevel)
                .setPropertyValue(CAULDRON_LIQUID, newLiquid);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);
        dimension.updateComparatorOutputLevel(pos);
    }

    /**
     * Resets the cauldron to empty water and clears its stored potion data. The
     * block-state write and comparator refresh precede metadata clearing, followed
     * by a centered fizz sound and smoke particles.
     */
    protected void clearWithFizz(Dimension dimension, Vector3ic pos,
                                 BlockState currentState) {
        setFillLevelAndLiquid(dimension, pos, currentState, 0, CauldronLiquid.WATER);
        clearPotionData(dimension, pos);
        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.FIZZ);
        double cx = pos.x() + 0.5;
        double cy = pos.y() + 0.5;
        double cz = pos.z() + 0.5;
        // Emit eight smoke particles for the incompatible-liquid fizz effect.
        for (int i = 0; i < 8; i++) {
            dimension.addParticle(cx, cy, cz, SimpleParticle.WHITE_SMOKE);
        }
    }

    /**
     * Clears stored potion data only when an existing cauldron entity is present.
     * The lookup does not create a block entity, so missing or differently typed
     * metadata remains untouched.
     */
    protected void clearPotionData(Dimension dimension, Vector3ic pos) {
        var blockEntity = getBlockEntityIfExists(dimension, pos);
        if (blockEntity != null) {
            blockEntity.clearPotionData();
        }
    }

    /**
     * Returns the existing cauldron block entity at the supplied position. A
     * missing or differently typed block entity produces {@code null}; this helper
     * performs no holder-driven entity creation.
     */
    protected BlockEntityCauldron getBlockEntityIfExists(Dimension dimension, Vector3ic pos) {
        var blockEntity = dimension.getBlockEntity(pos);
        if (blockEntity instanceof BlockEntityCauldron cauldron) {
            return cauldron;
        }
        return null;
    }

    /**
     * Consumes one held item through the player helper and offers an empty bucket
     * outside creative mode. The inventory insertion result is ignored, so a
     * rejected bucket is not dropped or restored.
     */
    protected void consumeBucket(EntityPlayer player) {
        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.BUCKET.createItemStack(1));
        }
    }

    /**
     * Consumes one held item through the player helper and offers an empty bottle
     * outside creative mode. The inventory insertion result is ignored, so a
     * rejected bottle is not dropped or restored.
     */
    protected void giveBottle(EntityPlayer player) {
        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.GLASS_BOTTLE.createItemStack(1));
        }
    }
}
