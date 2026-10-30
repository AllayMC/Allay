package org.allaymc.server.blockentity.component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.container.BrewingStandBrewEvent;
import org.allaymc.api.eventbus.event.container.BrewingStandConsumeFuelEvent;
import org.allaymc.api.eventbus.event.container.BrewingStandStartBrewEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.PotionRecipe;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.BrewingStandContainerImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;

/**
 * {@code BlockEntityBrewingStandBaseComponentImpl} processes brewing recipes
 * across three bottle slots using a reagent countdown and fuel counters. Recipe
 * availability is reevaluated each tick, and event callbacks can cancel fuel
 * consumption, brewing start, or completion. Reagent slot notifications reset
 * the countdown independently of bottle occupancy changes.
 *
 * <p>
 * Fuel is checked before recipe availability, so replenishment can occur even
 * when no bottle currently has a recipe. Completion applies nonnull event
 * results before consuming the reagent and charging one fuel unit. These
 * changes are incremental rather than transactional; a failing listener can
 * leave earlier bottle replacements applied. Counter NBT uses signed shorts,
 * while container persistence is contributed by the holder.
 *
 * <p>
 * {@code fuelAmount} and {@code fuelTotal} start together when blaze powder
 * replenishes fuel. Brewing completion decreases only {@code fuelAmount};
 * {@code fuelTotal} remains the display total until replenishment.
 *
 * @author IWareQ
 */
@Getter
@Setter
public class BlockEntityBrewingStandBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBrewingStandBaseComponent {
    /**
     * {@code TAG_COOK_TIME}, {@code TAG_FUEL_AMOUNT}, and
     * {@code TAG_FUEL_TOTAL} identify signed-short brewing counters shared by
     * the save and load paths. {@code TAG_COOK_TIME} stores the remaining
     * brewing countdown.
     */
    protected static final String TAG_COOK_TIME = "CookTime";
    /**
     * Persisted remaining brew-operation counter.
     */
    protected static final String TAG_FUEL_AMOUNT = "FuelAmount";
    /**
     * Persisted total fuel-display counter.
     */
    protected static final String TAG_FUEL_TOTAL = "FuelTotal";

    /**
     * {@code MAX_BREW_TIME} defines the standard countdown in server ticks and
     * the sentinel at which a brewing-start event is offered.
     */
    protected static final int MAX_BREW_TIME = 400;

    /**
     * {@code containerHolderComponent} supplies the shared brewing container;
     * initialization and ticking require its container to be a
     * {@code BrewingStandContainerImpl}.
     */
    @Dependency
    @Setter(AccessLevel.NONE)
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * Injected brewing stand used as the source of brewing events.
     */
    @ComponentObject
    protected BlockEntityBrewingStand thisBlockEntity;

    /**
     * {@code brewTime} stores the remaining server-tick countdown; reagent
     * notifications reset it, and the generated setter stores supplied values
     * directly.
     */
    protected int brewTime;
    /**
     * {@code fuelAmount} stores remaining brew operations; completion charges
     * one unit, while a nonpositive value requests fuel replenishment on
     * processing.
     */
    protected int fuelAmount;
    /**
     * {@code fuelTotal} supplies the client fuel-display total independently of
     * remaining fuel; successful replenishment sets both counters to the same
     * amount.
     */
    protected int fuelTotal;

    /**
     * Creates brewing-stand base state using the supplied initialization
     * information.
     */
    public BlockEntityBrewingStandBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads base state and installs bottle-occupancy and reagent-reset
     * callbacks. The injected holder must supply the standard brewing
     * container; callbacks remain on it if the holder's container reference is
     * later replaced. Occupancy callbacks choose properties by map iteration
     * position rather than explicit bottle-property names, so their bottle
     * association depends on an order the block-type builder does not
     * guarantee.
     */
    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        BrewingStandContainerImpl container = containerHolderComponent.getContainer();
        for (int i = 1; i < 4; i++) {
            int finalI = i;
            container.addSlotChangeListener(finalI, item -> {
                var currentBlockState = getDimension().getBlockState(position);
                // Bottle slots select properties by map iteration position.
                // AllayBlockType.Builder.setProperties does not preserve the
                // supplied order, so this code has no explicit mapping to the
                // A, B, or C property names.
                var values = currentBlockState.getBlockType().getProperties().values();
                //noinspection unchecked
                currentBlockState = currentBlockState.setPropertyValue(values.toArray(new BlockPropertyType[0])[finalI - 1], item != ItemAirStack.AIR_STACK);
                getDimension().setBlockState(
                        position, currentBlockState,
                        0, true, true, false
                );
            });
        }

        container.addSlotChangeListener(BrewingStandContainerImpl.REAGENT_SLOT, item -> {
            brewTime = item == ItemAirStack.AIR_STACK ? 0 : MAX_BREW_TIME;
        });
    }

    /**
     * Advances brewing once and sends the current fuel counters to container
     * viewers. The supplied tick number is unused; countdown progress depends
     * on calls to this method and fuel data is sent after processing completes.
     */
    @Override
    public void tick(long currentTick) {
        BrewingStandContainerImpl container = containerHolderComponent.getContainer();
        tickBrewingStand(container);
        container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_FUEL_AMOUNT, fuelAmount);
        container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_FUEL_TOTAL, fuelTotal);
    }

    /**
     * Advances brewing from the current fuel, reagent, and three bottle
     * recipes. Fuel replenishment runs first, even when all recipe lookups
     * subsequently fail. A missing recipe set resets the countdown and clears
     * the displayed brewing animation; an accepted start event can replace the
     * countdown.
     *
     * <p>
     * Positive time decreases without completing until a later call observes a
     * nonpositive value. Completion offers copied recipe outputs to the brewing
     * event, applies its nonnull results in order, then consumes one reagent
     * and one fuel unit. Cancellation skips that completion sequence, while
     * failures during its incremental writes do not roll back earlier results.
     */
    protected void tickBrewingStand(BrewingStandContainerImpl container) {
        if (!checkFuel(container)) {
            return;
        }

        var reagent = container.getReagent();
        var a = findRecipe(container.getResult(0), reagent);
        var b = findRecipe(container.getResult(1), reagent);
        var c = findRecipe(container.getResult(2), reagent);

        if (a == null && b == null && c == null) {
            // Keep the start sentinel ready for a later valid recipe while
            // sending zero to stop the current client brewing animation.
            brewTime = MAX_BREW_TIME;
            container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_BREW_TIME, 0);
            return;
        }

        if (brewTime == MAX_BREW_TIME) {
            var event = new BrewingStandStartBrewEvent(thisBlockEntity, brewTime);
            if (!event.call()) {
                return;
            }

            brewTime = event.getBrewingTime();
        }

        if (brewTime > 0) {
            // Send countdown updates every forty ticks while fuel data
            // continues each tick.
            if (brewTime % 40 == 0) {
                container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_BREW_TIME, brewTime);
            }

            brewTime--;
            return;
        }

        if (container.isEmpty(BrewingStandContainerImpl.REAGENT_SLOT)) {
            return;
        }

        var result = new ItemStack[3];
        if (a != null) result[0] = a.getOutput().copy();
        if (b != null) result[1] = b.getOutput().copy();
        if (c != null) result[2] = c.getOutput().copy();

        var event = new BrewingStandBrewEvent(thisBlockEntity, result);
        if (!event.call()) {
            return;
        }

        // Apply the event's result references before charging reagent or fuel;
        // a later slot failure leaves earlier bottle changes in place.
        for (int i = 0; i < event.getResult().length; i++) {
            var itemStack = event.getResult()[i];
            if (itemStack != null) {
                container.setResult(i, itemStack);
            }
        }

        if (reagent.getCount() > 1) {
            reagent.reduceCount(1);
            container.notifySlotChange(BrewingStandContainerImpl.REAGENT_SLOT);
        } else {
            container.clearSlot(BrewingStandContainerImpl.REAGENT_SLOT);
        }

        fuelAmount--;
        getDimension().addSound(position, SimpleSound.POTION_BREWED);
    }

    /**
     * Returns whether stored brewing fuel is available or can be replenished.
     * Existing positive fuel needs no inventory change; otherwise only blaze
     * powder can supply fuel after an accepted consumption event.
     *
     * <p>
     * Successful replenishment consumes one powder before assigning twenty brew
     * operations to both fuel counters. Cancellation leaves normal fuel
     * consumption pending, and a slot callback failure can interrupt the
     * sequence after inventory mutation but before either counter is assigned.
     * Callers run this check before evaluating bottle recipes.
     */
    protected boolean checkFuel(BrewingStandContainerImpl container) {
        if (fuelAmount > 0) {
            return true;
        }

        var fuel = container.getFuel();
        if (fuel.getItemType() != ItemTypes.BLAZE_POWDER) {
            return false;
        }

        var event = new BrewingStandConsumeFuelEvent(thisBlockEntity, fuel);
        if (!event.call()) {
            return false;
        }

        if (fuel.getCount() > 1) {
            fuel.reduceCount(1);
            container.notifySlotChange(BrewingStandContainerImpl.FUEL_SLOT);
        } else {
            container.clearSlot(BrewingStandContainerImpl.FUEL_SLOT);
        }

        // One accepted blaze-powder consumption supplies twenty brew
        // operations; the total counter starts at that same amount for the fuel
        // display.
        fuelAmount = 20;
        fuelTotal = fuelAmount;
        return true;
    }

    /**
     * Looks up the potion recipe identifier for the supplied bottle and
     * reagent. A missing registry entry returns {@code null}; a present entry
     * must be a {@code PotionRecipe} because this helper casts it directly.
     */
    protected PotionRecipe findRecipe(ItemStack ingredient, ItemStack reagent) {
        return (PotionRecipe) Registries.RECIPES.get(PotionRecipe.buildIdentifier(ingredient, reagent));
    }

    /**
     * Adds brewing counters to the completed base and component save data. Each
     * counter is narrowed to a signed short without range validation, so values
     * outside that representation do not round-trip unchanged.
     */
    @Override
    public NbtMap saveNBT() {
        // Serialize the inherited save first, then narrow local counters to the
        // signed-short representation consumed by the corresponding NBT load
        // listeners.
        return super.saveNBT()
                .toBuilder()
                .putShort(TAG_COOK_TIME, (short) brewTime)
                .putShort(TAG_FUEL_AMOUNT, (short) fuelAmount)
                .putShort(TAG_FUEL_TOTAL, (short) fuelTotal)
                .build();
    }

    /**
     * Loads base state before applying present signed-short brewing counters.
     * Missing or differently typed counter tags preserve existing values;
     * assignments occur incrementally and do not validate countdown or fuel
     * ranges.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_COOK_TIME, value -> brewTime = value);
        nbt.listenForShort(TAG_FUEL_AMOUNT, value -> fuelAmount = value);
        nbt.listenForShort(TAG_FUEL_TOTAL, value -> fuelTotal = value);
    }
}
