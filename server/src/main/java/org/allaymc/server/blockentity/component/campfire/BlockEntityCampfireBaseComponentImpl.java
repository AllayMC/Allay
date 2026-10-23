package org.allaymc.server.blockentity.component.campfire;

import lombok.Getter;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCampfireBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCampfire;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.container.CampfireSmeltEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.recipe.FurnaceRecipeMatcher;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockEntityCampfireBaseComponentImpl} manages four campfire cooking
 * slots, their remaining ticks, and cached recipe matches. Slots use the exact
 * {@code ItemAirStack.AIR_STACK} reference for emptiness. Ordinary slot
 * assignment refreshes the matching recipe and resets its timer, while
 * accepted cooking requests an output drop and clears the ingredient before updating
 * observers.
 *
 * <p>
 * {@code tick} pauses all processing while extinguished and retries a completed
 * recipe on later ticks when {@code CampfireSmeltEvent} is cancelled. NBT
 * stores one-based item and timer tags but does not store recipe-cache entries.
 * Loading does not invalidate existing cache entries; on a fresh component the
 * next recipe discovery resets a loaded timer to {@code COOK_TIME}. Direct
 * array access and repeated loads therefore require callers to account for
 * cache state rather than assuming persistence reconstructs a complete cooking
 * session.
 *
 * @author daoge_cmd
 */
public class BlockEntityCampfireBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCampfireBaseComponent {

    /**
     * {@code TAG_ITEM} supplies the prefix for one-based item tags. The
     * campfire tag prefixes keep each serialized ingredient paired with its
     * remaining timer.
     */
    protected static final String TAG_ITEM = "Item";
    /**
     * Persisted slot timer prefix.
     */
    protected static final String TAG_ITEM_TIME = "ItemTime";

    /**
     * Injected owning campfire entity.
     */
    @ComponentObject
    protected BlockEntityCampfire thisCampfire;

    // items, cookTimes, and recipes share slot indices: each ingredient uses
    // the remaining timer and cached match at the same index until a clearing
    // path resets them.
    /**
     * {@code items} holds four mutable ingredient references. The generated
     * getter exposes the backing array directly, bypassing recipe and timer
     * synchronization when callers replace entries themselves.
     */
    @Getter
    protected ItemStack[] items;
    /**
     * Remaining cooking game ticks.
     */
    protected int[] cookTimes;
    /**
     * {@code recipes} caches matches for the corresponding ingredients. Slot
     * setters and clearing paths replace entries, but NBT loading leaves
     * existing cache entries unchanged.
     */
    protected FurnaceRecipe[] recipes;

    /**
     * Creates common campfire state and four initially empty cooking slots.
     * Each slot starts with the shared air-stack sentinel, zero remaining
     * ticks, and no cached recipe before initial NBT is loaded.
     */
    public BlockEntityCampfireBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.items = new ItemStack[4];
        this.cookTimes = new int[4];
        this.recipes = new FurnaceRecipe[4];
        for (int i = 0; i < 4; i++) {
            items[i] = ItemAirStack.AIR_STACK;
        }
    }

    /**
     * Returns the ordinary campfire recipe category used by this component.
     */
    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.CAMPFIRE;
    }

    /**
     * Processes ingredients only while the campfire block is lit. An occupied
     * slot without a cached match is matched again; failure drops its
     * ingredient, while success resets the timer to {@code COOK_TIME}. Positive
     * timers count down before completion is tested. At completion a
     * cancellable smelt event can replace the output; cancellation retains the
     * ingredient and nonpositive timer for another attempt on the next
     * processing tick. Completed or invalidated slots trigger one viewer update
     * after the slot loop.
     */
    @Override
    public void tick(long currentTick) {
        if (isExtinguished()) {
            return;
        }

        // Sample crackling independently each processing tick instead of using
        // a fixed cadence.
        if (ThreadLocalRandom.current().nextFloat() <= 0.016f) {
            getDimension().addSound(MathUtils.center(position), SimpleSound.CAMPFIRE_CRACKLE);
        }

        boolean changed = false;
        for (int slot = 0; slot < 4; slot++) {
            var item = items[slot];
            if (item == ItemAirStack.AIR_STACK) {
                cookTimes[slot] = 0;
                recipes[slot] = null;
                continue;
            }

            var recipe = recipes[slot];
            if (recipe == null) {
                recipe = matchRecipe(item);
                if (recipe == null) {
                    // An unmatched ingredient cannot complete cooking, so drop
                    // it and clear the slot instead of retaining an unusable
                    // occupied slot indefinitely.
                    dropItem(slot);
                    changed = true;
                    continue;
                }
                // Cache discovery starts a full cooking interval, including
                // after loading a saved timer into a slot whose recipe cache is
                // empty.
                cookTimes[slot] = COOK_TIME;
                recipes[slot] = recipe;
            }

            if (cookTimes[slot] > 0) {
                cookTimes[slot]--;
            }

            if (cookTimes[slot] <= 0) {
                // Keep the ingredient and expired counter when the event is
                // cancelled, allowing the same completed recipe to be offered
                // again on a later tick.
                var output = recipe.getOutput().copy();
                var event = new CampfireSmeltEvent(thisCampfire, item, output);
                if (event.call()) {
                    dropCookedItem(slot, event.getOutput());
                    changed = true;
                }
            }
        }

        if (changed) {
            sendBlockEntityToViewers();
        }
    }

    /**
     * Returns whether the campfire block currently marks itself as
     * extinguished.
     */
    protected boolean isExtinguished() {
        return getBlockState().getPropertyValue(BlockPropertyTypes.EXTINGUISHED);
    }

    /**
     * Finds an ingredient recipe for this component's selected campfire
     * category. The matcher does not fall back to ordinary furnace recipes, and
     * an unmatched ingredient returns {@code null} for the caller to handle.
     */
    protected FurnaceRecipe matchRecipe(ItemStack ingredient) {
        return FurnaceRecipeMatcher.match(ingredient, getFurnaceRecipeType(), false);
    }

    /**
     * Requests an ingredient drop before clearing its slot and cached cooking
     * state. The air-stack sentinel needs no request. A synchronous exception
     * prevents the following array clears, but entity admission after the
     * request returns is not awaited or checked. Comparator output is refreshed
     * after clearing.
     */
    protected void dropItem(int slot) {
        var item = items[slot];
        // Clear the slot only after the drop request returns; synchronous
        // exceptions prevent these assignments, while later queued entity
        // admission is not observed by this helper.
        if (item != ItemAirStack.AIR_STACK) {
            // Jitter ingredient drops within six tenths of the block's width.
            var pos = new Vector3d(
                    position.x() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3),
                    position.y() + 0.5,
                    position.z() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3)
            );
            getDimension().dropItem(item, pos);
        }
        items[slot] = ItemAirStack.AIR_STACK;
        cookTimes[slot] = 0;
        recipes[slot] = null;
        getDimension().updateComparatorOutputLevel(getPosition());
    }

    /**
     * Requests the accepted cooked output's drop before clearing the ingredient
     * slot. Remaining ticks and the cached recipe are cleared after the request
     * returns, then comparator output is refreshed. Later entity admission is
     * not observed; only a synchronous exception before return prevents those
     * clearing assignments.
     */
    protected void dropCookedItem(int slot, ItemStack output) {
        // Jitter the cooked-item drop within six tenths of the block's width.
        var pos = new Vector3d(
                position.x() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3),
                position.y() + 0.5,
                position.z() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3)
        );
        // Clear the ingredient only after the output request returns;
        // synchronous exceptions preserve its slot references, while later
        // entity admission does not control this clearing sequence.
        getDimension().dropItem(output, pos);
        items[slot] = ItemAirStack.AIR_STACK;
        cookTimes[slot] = 0;
        recipes[slot] = null;
        getDimension().updateComparatorOutputLevel(getPosition());
    }

    /**
     * Requests a drop for every retained ingredient when the campfire is
     * replaced. Each slot is cleared after its drop request returns, without
     * awaiting entity admission; timers and cached recipes remain untouched in
     * this removal path.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        for (int i = 0; i < 4; i++) {
            if (items[i] != ItemAirStack.AIR_STACK) {
                getDimension().dropItem(items[i], MathUtils.center(position));
                items[i] = ItemAirStack.AIR_STACK;
            }
        }
    }

    /**
     * Builds common NBT with one-based ingredient and remaining-time tags. The
     * array index zero becomes {@code "Item1"} and {@code "ItemTime1"}, and
     * later indices follow the same pairing through four. Empty sentinel slots
     * omit both tags, and cached recipes are not serialized. The returned map
     * therefore retains item and timer observations without independently
     * preserving the cached match needed to continue cooking without another
     * recipe-discovery reset.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        // Pair each zero-based slot with one-based item and timer tag suffixes
        // so separate NBT entries still identify the same ingredient during
        // loading.
        for (int i = 0; i < 4; i++) {
            int index = i + 1;
            if (items[i] != ItemAirStack.AIR_STACK) {
                builder.putCompound(TAG_ITEM + index, items[i].saveNBT());
                builder.putInt(TAG_ITEM_TIME + index, cookTimes[i]);
            }
        }
        return builder.build();
    }

    /**
     * Loads supplied one-based item and timer entries into their corresponding
     * slots. Missing entries preserve existing array values, and recipe-cache
     * entries remain unchanged even when an ingredient is replaced. A fresh
     * component has no cached matches, so its next processing tick discovers a
     * recipe and resets the loaded timer to {@code COOK_TIME}. Repeated loads
     * can instead retain a previous recipe match; this method does not validate
     * that match against newly decoded items.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Decode matching one-based suffixes into the same zero-based slot;
        // recipes remain cached independently and are not reconstructed or
        // invalidated by these entries.
        for (int i = 0; i < 4; i++) {
            int index = i + 1;
            final int slot = i;
            nbt.listenForCompound(TAG_ITEM + index, itemNbt -> {
                items[slot] = NBTIO.getAPI().fromItemStackNBT(itemNbt);
            });
            nbt.listenForInt(TAG_ITEM_TIME + index, time -> {
                cookTimes[slot] = time;
            });
        }
    }

    /**
     * Returns the shared ingredient reference for a valid slot index. Indices
     * outside zero through three return {@code ItemAirStack.AIR_STACK}; valid
     * results remain shared with ticking, saving, and item-dropping paths.
     */
    @Override
    public ItemStack getItemStack(int slot) {
        if (slot < 0 || slot >= 4) {
            return ItemAirStack.AIR_STACK;
        }
        return items[slot];
    }

    /**
     * Stores an ingredient and refreshes the slot's timer and cached recipe.
     * Invalid indices are ignored, and {@code null} selects the air-stack
     * sentinel. Occupied slots retain the supplied stack directly and start at
     * {@code COOK_TIME}, while empty slots clear timer and recipe together.
     * Recipe matching runs after the ingredient and timer assignments, so a
     * matching failure can leave earlier changes applied. The setter broadcasts
     * before refreshing comparator output and does not consume or copy the
     * caller's stack.
     */
    @Override
    public void setItemStack(int slot, ItemStack item) {
        if (slot < 0 || slot >= 4) {
            return;
        }
        items[slot] = item != null ? item : ItemAirStack.AIR_STACK;
        if (items[slot] != ItemAirStack.AIR_STACK) {
            cookTimes[slot] = COOK_TIME;
            recipes[slot] = matchRecipe(items[slot]);
        } else {
            cookTimes[slot] = 0;
            recipes[slot] = null;
        }
        sendBlockEntityToViewers();
        getDimension().updateComparatorOutputLevel(getPosition());
    }

    /**
     * Returns the remaining game ticks retained for a valid cooking slot.
     * Invalid indices return zero rather than throwing, and valid counters are
     * reported without clamping or consulting the cached recipe.
     */
    @Override
    public int getCookTime(int slot) {
        if (slot < 0 || slot >= 4) {
            return 0;
        }
        return cookTimes[slot];
    }

    /**
     * Stores a remaining-time counter without changing the ingredient or
     * recipe. Invalid indices are ignored; valid counters retain the supplied
     * integer without clamping, broadcasting, or refreshing comparator output.
     */
    @Override
    public void setCookTime(int slot, int cookTime) {
        if (slot < 0 || slot >= 4) {
            return;
        }
        cookTimes[slot] = cookTime;
    }

    /**
     * Checks whether current slot availability and recipe eligibility allow
     * insertion. {@code null} and the shared air-stack sentinel are rejected
     * before scanning. The first empty slot permits a recipe query for this
     * component's selected category, and a missing match returns {@code false}.
     * The query does not check item count separately, copy the item, or reserve
     * the observed capacity. Callers performing insertion must still apply
     * their mutation after the query rather than treating the result as an
     * occupied slot.
     */
    @Override
    public boolean canAcceptItemStack(ItemStack item) {
        if (item == null || item == ItemAirStack.AIR_STACK) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (items[i] == ItemAirStack.AIR_STACK) {
                return matchRecipe(item) != null;
            }
        }
        return false;
    }

    /**
     * Copies one item into the first empty slot after the acceptance query
     * succeeds. The query does not reserve a slot, so this method scans current
     * occupancy again before copying and assigning a stack with count one. The
     * supplied stack remains unchanged; the caller performs any inventory
     * consumption separately. {@code setItemStack} applies timer, cache,
     * viewer, and comparator changes, and a failure after assignment can leave
     * the copied ingredient stored without a successful return from this
     * method.
     */
    @Override
    public boolean tryAddItemStack(ItemStack item) {
        if (!canAcceptItemStack(item)) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (items[i] == ItemAirStack.AIR_STACK) {
                var singleItem = item.copy();
                singleItem.setCount(1);
                setItemStack(i, singleItem);
                return true;
            }
        }
        return false;
    }
}
