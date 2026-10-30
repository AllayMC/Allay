package org.allaymc.server.blockentity.component.furnace;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.container.interfaces.FurnaceContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.container.FurnaceConsumeFuelEvent;
import org.allaymc.api.eventbus.event.container.FurnaceSmeltEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.FurnaceContainerImpl;
import org.allaymc.server.item.recipe.FurnaceRecipeMatcher;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockEntityFurnaceBaseComponentImpl} processes furnace ingredients,
 * fuel, output, and stored experience for ordinary and specialized furnaces.
 * Subclasses select block variants, recipe categories, ideal speeds, and
 * crackle sounds. The processor caches a successful recipe by ingredient stack
 * identity and uses normal speed for a normal-furnace fallback recipe.
 *
 * <p>
 * Burning time decreases before ingredient and output checks, while cooking
 * progresses only when the cached recipe, output-slot checks, and fuel permit
 * it. Completion resets cooking progress and consumes the ingredient before
 * dispatching {@code FurnaceSmeltEvent}; cancellation does not restore that
 * input. Result writes and experience accounting follow the event and have no
 * rollback. Experience is released when the result becomes air or the block is
 * replaced. Counter persistence uses signed shorts for times and an integer for
 * experience, with container items contributed by the holder's save and load
 * handlers.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFurnaceBaseComponent {

    /**
     * {@code TAG_BURN_TIME}, {@code TAG_COOK_TIME}, {@code TAG_BURN_DURATION},
     * and {@code TAG_STORED_XP_INT} identify the furnace counters contributed
     * after base serialization. {@code TAG_BURN_TIME} stores remaining fuel
     * ticks.
     */
    public static final String TAG_BURN_TIME = "BurnTime";
    /**
     * Persisted accumulated cooking progress.
     */
    public static final String TAG_COOK_TIME = "CookTime";
    /**
     * Persisted initial fuel duration.
     */
    public static final String TAG_BURN_DURATION = "BurnDuration";
    /**
     * Persisted integer experience balance.
     */
    public static final String TAG_STORED_XP_INT = "StoredXPInt";

    /**
     * {@code MAX_COOK_TIME} defines the cooking-progress threshold for one
     * completed operation; recipe-dependent speed determines how many server
     * ticks reach it.
     */
    public static final int MAX_COOK_TIME = 200;

    /**
     * {@code containerHolderComponent} supplies the live ingredient, fuel, and
     * result inventory; processing and initialization require a
     * {@code FurnaceContainerImpl} container.
     */
    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * Injected furnace used as the source of processing events.
     */
    @ComponentObject
    protected BlockEntityFurnace thisBlockEntityFurnace;

    /**
     * {@code burnTime} stores remaining fuel ticks. {@code burnDuration}
     * records the initial fuel duration, while {@code cookTime} holds
     * speed-scaled cooking progress; invalid fuel resets all three without
     * changing experience or cache.
     */
    @Getter
    protected int burnTime;
    /**
     * Accumulated recipe-speed cooking progress.
     */
    @Getter
    protected int cookTime;
    /**
     * Initial consumed-fuel duration in server ticks.
     */
    @Getter
    protected int burnDuration;
    /**
     * {@code storedXP} retains integer experience until a release path drops
     * it; the generated setter accepts values directly, including nonpositive
     * balances.
     */
    @Getter
    @Setter
    protected int storedXP;

    /**
     * {@code currentIngredientStackUniqueId} records the last validated
     * ingredient stack identity. {@code currentIngredientStackUniqueId},
     * {@code currentRecipe}, and {@code currentRecipeInput} change together
     * only after successful {@code checkIngredient} validation. Failed
     * validation retains the prior cache, and the cached identity controls
     * whether ticking retries validation.
     */
    protected int currentIngredientStackUniqueId;
    /**
     * {@code currentRecipe} retains the matched registry recipe rather than a
     * copy; {@code getCurrentSpeed} requires this reference to be nonnull.
     */
    protected FurnaceRecipe currentRecipe;
    /**
     * Input retained with the last successfully cached furnace recipe.
     */
    protected FurnaceRecipeInput currentRecipeInput;

    /**
     * Creates furnace base state with no cached recipe or recipe input. The
     * cached ingredient identifier starts at {@code Integer.MAX_VALUE};
     * completion later loads persisted counters after component dependencies
     * and event handlers are connected.
     */
    public BlockEntityFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.currentIngredientStackUniqueId = Integer.MAX_VALUE;
        this.currentRecipe = null;
        this.currentRecipeInput = null;
    }

    /**
     * Loads base state and installs experience release on result-slot
     * notifications. The injected holder must supply a
     * {@code FurnaceContainerImpl}; a notification containing the shared air
     * stack triggers the stored-experience drop path, regardless of who cleared
     * the result.
     */
    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        FurnaceContainerImpl container = containerHolderComponent.getContainer();
        container.addSlotChangeListener(FurnaceContainer.RESULT_SLOT, item -> {
            if (item != ItemAirStack.AIR_STACK) return;
            tryDropStoredXP();
        });
    }

    /**
     * Returns {@code BlockTypes.FURNACE} as the ordinary furnace's inactive
     * block variant.
     */
    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.FURNACE;
    }

    /**
     * Returns {@code BlockTypes.LIT_FURNACE} as the ordinary furnace's burning
     * block variant.
     */
    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_FURNACE;
    }

    /**
     * Returns {@code FurnaceRecipe.Type.FURNACE} as this implementation's
     * preferred recipe category.
     */
    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.FURNACE;
    }

    /**
     * Returns one cooking-progress unit per tick for normal-furnace fallback
     * recipes.
     */
    @Override
    public float getNormalSpeed() {
        return 1;
    }

    /**
     * Returns one cooking-progress unit per tick for ordinary furnace recipes.
     */
    @Override
    public float getIdealSpeed() {
        return 1;
    }

    /**
     * Returns ideal speed for the preferred cached recipe category, otherwise
     * normal speed. {@code currentRecipe} must already be initialized by
     * successful ingredient validation; calling this method before that point
     * dereferences {@code null}.
     */
    @Override
    public float getCurrentSpeed() {
        return currentRecipe.getType() == getFurnaceRecipeType() ? getIdealSpeed() : getNormalSpeed();
    }

    /**
     * Adds processing counters after the base and component save data is built.
     * Times are narrowed to signed shorts without validation, while stored
     * experience retains its integer representation; this method does not
     * persist the returned map.
     */
    @Override
    public NbtMap saveNBT() {
        // Build inherited save data before adding local counters; narrowing
        // time values to signed shorts can lose values outside the persisted
        // representation.
        return super.saveNBT().toBuilder()
                .putShort(TAG_BURN_TIME, (short) burnTime)
                .putShort(TAG_COOK_TIME, (short) cookTime)
                .putShort(TAG_BURN_DURATION, (short) burnDuration)
                .putInt(TAG_STORED_XP_INT, storedXP)
                .build();
    }

    /**
     * Loads base state before applying present time and experience counter
     * tags. Missing or differently typed values preserve existing counters;
     * times use signed shorts and experience uses an integer, with no range
     * validation.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort(TAG_BURN_TIME, value -> burnTime = value);
        nbt.listenForShort(TAG_COOK_TIME, value -> cookTime = value);
        nbt.listenForShort(TAG_BURN_DURATION, value -> burnDuration = value);
        nbt.listenForInt(TAG_STORED_XP_INT, value -> storedXP = value);
    }

    /**
     * Changes the block at this furnace's position to its selected lighting
     * variant. A matching type requires no update; otherwise compatible
     * property values are copied from the current state before replacement.
     */
    @Override
    public void setLit(boolean lit) {
        var currentBlockState = getDimension().getBlockState(position);
        var newBlockType = lit ? getLitBlockType() : getUnlitBlockType();
        if (currentBlockState.getBlockType() == newBlockType) {
            return;
        }

        getDimension().setBlockState(
                position, newBlockType.copyPropertyValuesFrom(currentBlockState),
                0, true, true, false
        );
    }

    /**
     * Advances processing and sends current furnace counters to inventory
     * viewers. The previous burn counter controls lighting transitions after
     * processing; the supplied tick number is unused, and burning ticks can
     * also emit a randomized variant-specific crackle sound.
     */
    @Override
    public void tick(long currentTick) {
        var lastBurnTime = burnTime;
        tickFurnace();
        if (lastBurnTime == 1 && burnTime == 0) setLit(false);
        if (lastBurnTime == 0 && burnTime > 0) setLit(true);
        sendFurnaceContainerData();

        // The per-tick chance averages one crackle about every three seconds at
        // twenty ticks per second.
        if (burnTime > 0 && ThreadLocalRandom.current().nextFloat() <= 0.016f) {
            getDimension().addSound(MathUtils.center(position), getCrackleSound());
        }
    }

    /**
     * Returns the ordinary furnace crackle sound used during burning ticks.
     */
    protected SimpleSound getCrackleSound() {
        return SimpleSound.FURNACE_CRACKLE;
    }

    /**
     * Advances burning, validates the ingredient when its stack identity
     * changes, and attempts one cooking step. An empty ingredient resets
     * cooking progress; blocked output or unavailable fuel prevents progress
     * while existing fuel can still have burned down at the start of the call.
     *
     * <p>
     * Reaching {@code MAX_COOK_TIME} resets progress and consumes one
     * ingredient before the smelt event runs. Cancellation leaves those changes
     * applied. The accepted event exposes the cached recipe's shared output; an
     * empty result receives its copy, while a matching existing result grows by
     * one. A changed incompatible result is replaced with the output copy and
     * exits without awarding experience. Normal completion then
     * probabilistically rounds the output's experience multiplier into the
     * stored integer balance.
     */
    protected void tickFurnace() {
        if (burnTime > 0) burnTime--;

        FurnaceContainerImpl container = containerHolderComponent.getContainer();
        if (container.isEmpty(FurnaceContainer.INGREDIENT_SLOT)) {
            cookTime = 0;
            return;
        }

        var ingredient = container.getIngredient();
        if (ingredient.getUniqueId() != currentIngredientStackUniqueId && !checkIngredient(ingredient)) {
            return;
        }

        var output = currentRecipe.getOutput();
        var outputItemType = output.getItemType();
        if (
                (!container.isEmpty(FurnaceContainer.RESULT_SLOT) && outputItemType != container.getResult().getItemType()) ||
                container.getResult().isFull() ||
                !checkFuel()
        ) {
            return;
        }

        // Only whole progress units advance cooking; fractional speed
        // contributions are truncated on each tick rather than carried into
        // later ticks.
        cookTime += (int) getCurrentSpeed();

        // Complete cooking only after accumulated speed-scaled progress reaches
        // the shared threshold.
        if (cookTime < MAX_COOK_TIME) return;

        // Consume the ingredient before dispatching the smelt event;
        // cancellation later in this sequence does not restore input or the
        // completed cooking progress.
        cookTime = 0;
        if (ingredient.getCount() > 1) {
            ingredient.reduceCount(1);
            container.notifySlotChange(FurnaceContainer.INGREDIENT_SLOT);
        } else {
            container.clearSlot(FurnaceContainer.INGREDIENT_SLOT);
        }

        var event = new FurnaceSmeltEvent(thisBlockEntityFurnace, ingredient, output);
        if (!event.call()) return;

        var currentResult = container.getResult();
        if (currentResult == ItemAirStack.AIR_STACK) {
            container.setResult(output.copy());
        } else {
            if (currentResult.getItemType() != outputItemType) {
                log.warn("Furnace result slot already has different item! Previous: {}, New: {}", currentResult.getItemType().getIdentifier(), outputItemType.getIdentifier());
                // An event callback can change the result after the initial
                // capacity check; this path replaces the incompatible stack and
                // skips experience accounting.
                container.setResult(output.copy());
                return;
            }
            currentResult.increaseCount(1);
            container.notifySlotChange(FurnaceContainer.RESULT_SLOT);
        }

        // Preserve fractional experience in expectation: the whole part is
        // guaranteed, and the fractional part is the probability of one extra
        // point.
        var xp = output.getItemType().getItemData().furnaceXPMultiplier();
        int earned = (int) Math.floor(xp);
        var chance = xp - earned;
        if (chance > 0 && ThreadLocalRandom.current().nextFloat() < chance) {
            earned++;
        }
        storedXP += earned;
    }

    /**
     * Matches and validates an ingredient before replacing the furnace recipe
     * cache. No match or a failed recipe predicate returns {@code false} and
     * leaves the previous cached identifier, recipe, and input untouched. A
     * predicate disagreement also emits a warning for diagnosis.
     *
     * <p>
     * Successful validation assigns all three cache values from this ingredient
     * and its selected recipe. Ticking compares the ingredient's unique stack
     * identifier with the cached identifier, so an unchanged identity avoids
     * repeating the registry lookup on later ticks. The cached recipe remains
     * the registry object rather than an independent copy.
     */
    protected boolean checkIngredient(ItemStack ingredient) {
        var furnaceRecipe = matchFurnaceRecipe(ingredient);
        if (furnaceRecipe == null) return false;

        var furnaceInput = new FurnaceRecipeInput(ingredient, getFurnaceRecipeType());
        if (!furnaceRecipe.match(furnaceInput)) {
            log.warn("Furnace recipe does not match input! Recipe: {}, Input: {}", furnaceRecipe.getIdentifier(), ingredient.getItemType().getIdentifier());
            return false;
        }

        currentIngredientStackUniqueId = ingredient.getUniqueId();
        currentRecipe = furnaceRecipe;
        currentRecipeInput = furnaceInput;
        return true;
    }

    /**
     * Drops a positive stored experience balance above the furnace and then
     * clears it. Nonpositive balances produce no orb and remain unchanged. The
     * drop uses the current integer balance as one orb amount rather than
     * splitting it into standard experience-orb sizes.
     *
     * <p>
     * {@code storedXP} resets only after the dimension's drop call returns. A
     * failed call leaves the recorded balance available to a later release
     * attempt, but any world side effects already performed by that call have
     * no compensating rollback. Callers serialize this operation with changes
     * to the stored balance.
     */
    protected void tryDropStoredXP() {
        if (storedXP < 1) return;

        var pos = new Vector3d(
                position.x() + 0.5,
                position.y() + 1.5,
                position.z() + 0.5
        );
        // Clear the recorded balance only after the dimension's drop call
        // returns; a failed call retains the counter without undoing completed
        // world effects.
        getDimension().dropXpOrb(pos, storedXP);
        storedXP = 0;
    }

    /**
     * Releases stored furnace experience when the block replacement event is
     * handled. This path is independent of the inventory holder's item-drop
     * policy and delegates nonpositive balances and reset ordering to
     * {@code tryDropStoredXP}.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        tryDropStoredXP();
    }

    /**
     * Returns {@code false} so ordinary block-entity viewing suppresses furnace
     * NBT delivery.
     */
    @Override
    public boolean sendToClient() {
        return false;
    }

    /**
     * Sends cooking, burning, and experience counters to the current
     * container's viewers. Cooking progress is divided by the variant's ideal
     * speed before integer conversion; burn counters and stored experience are
     * sent unchanged.
     */
    protected void sendFurnaceContainerData() {
        var container = containerHolderComponent.getContainer();
        // Normalize stored progress by the variant's ideal speed for container
        // data; blast furnaces and smokers send half the stored cooking
        // progress.
        container.sendContainerData(ContainerSetDataPacket.FURNACE_TICK_COUNT, (int) (cookTime / getIdealSpeed()));
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_TIME, burnTime);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_DURATION, burnDuration);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_STORED_XP, storedXP);
    }

    /**
     * Returns whether existing burning time or newly consumed fuel permits
     * cooking. Positive burning time succeeds immediately. Otherwise invalid
     * fuel resets all burn and cooking counters, while a cancelled consumption
     * event returns {@code false} before normal fuel-slot mutation.
     *
     * <p>
     * Accepted lava-bucket fuel becomes an empty bucket; other fuel loses one
     * item and sends a slot notification. The original fuel type then supplies
     * the truncated integer burn duration assigned to both burn counters. Slot
     * notifications precede those assignments, so a listener failure can leave
     * consumed fuel without replenished burning time.
     */
    protected boolean checkFuel() {
        if (burnTime > 0) return true;

        FurnaceContainerImpl container = containerHolderComponent.getContainer();

        var fuel = container.getFuel();
        if (!isFuel(fuel)) {
            resetToNoFuelState();
            return false;
        }

        var event = new FurnaceConsumeFuelEvent(thisBlockEntityFurnace, fuel);
        if (!event.call()) return false;

        // Retain the empty bucket when lava supplies fuel; all slot
        // notifications finish before this operation assigns the new burning
        // counters.
        if (fuel.getItemType() == ItemTypes.LAVA_BUCKET) {
            container.setFuel(ItemTypes.BUCKET.createItemStack(1));
        } else {
            if (fuel.getCount() > 1) {
                fuel.reduceCount(1);
                container.notifySlotChange(FurnaceContainer.FUEL_SLOT);
            } else {
                container.clearSlot(FurnaceContainer.FUEL_SLOT);
            }
        }

        // Truncate the configured fuel duration to whole server ticks.
        burnDuration = (int) fuel.getItemType().getItemData().furnaceBurnDuration();
        burnTime = burnDuration;

        return true;
    }

    /**
     * Clears remaining burn time, cooking progress, and recorded burn duration
     * together. Invalid fuel triggers this reset after burning expires; stored
     * experience and the last successful recipe cache remain unchanged.
     */
    protected void resetToNoFuelState() {
        burnTime = 0;
        cookTime = 0;
        burnDuration = 0;
    }

    /**
     * Finds the preferred recipe for this ingredient with normal-furnace
     * fallback enabled. The matcher prefers concrete-item descriptors over item
     * tags and returns {@code null} when neither the variant nor its fallback
     * matches.
     */
    protected FurnaceRecipe matchFurnaceRecipe(ItemStack ingredient) {
        return FurnaceRecipeMatcher.match(ingredient, getFurnaceRecipeType(), true);
    }

    /**
     * Returns whether the item's configured furnace burn duration is positive.
     */
    protected boolean isFuel(ItemStack itemStack) {
        return itemStack.getItemType().getItemData().furnaceBurnDuration() > 0;
    }
}
