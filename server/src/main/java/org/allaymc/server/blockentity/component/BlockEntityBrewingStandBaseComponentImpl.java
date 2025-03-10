package org.allaymc.server.blockentity.component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.container.impl.BrewingStandContainer;
import org.allaymc.api.eventbus.event.container.BrewingStandBrewEvent;
import org.allaymc.api.eventbus.event.container.BrewingStandConsumeFuelEvent;
import org.allaymc.api.eventbus.event.container.BrewingStandStartBrewEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.impl.PotionMixRecipe;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.Sound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;

/**
 * @author IWareQ
 */
@Getter
@Setter
public class BlockEntityBrewingStandBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBrewingStandBaseComponent {
    protected static final String TAG_COOK_TIME = "CookTime";
    protected static final String TAG_FUEL_AMOUNT = "FuelAmount";
    protected static final String TAG_FUEL_TOTAL = "FuelTotal";

    protected static final int MAX_BREW_TIME = 400;

    @Dependency
    @Setter(AccessLevel.NONE)
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    @ComponentObject
    protected BlockEntityBrewingStand thisBlockEntity;

    protected int brewTime;
    protected int fuelAmount;
    protected int fuelTotal;

    public BlockEntityBrewingStandBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        BrewingStandContainer container = containerHolderComponent.getContainer();
        for (int i = 1; i < 4; i++) {
            int finalI = i;
            container.addOnSlotChangeListener(finalI, item -> {
                var currentBlockState = getDimension().getBlockState(position);
                var values = currentBlockState.getBlockType().getProperties().values();
                //noinspection unchecked
                currentBlockState = currentBlockState.setPropertyValue(values.toArray(new BlockPropertyType[0])[finalI - 1], item != ItemAirStack.AIR_STACK);
                getDimension().setBlockState(
                        position, currentBlockState,
                        0, true, true, false
                );
            });
        }

        container.addOnSlotChangeListener(BrewingStandContainer.REAGENT_SLOT, item -> {
            brewTime = item == ItemAirStack.AIR_STACK ? 0 : MAX_BREW_TIME;
        });
    }

    @Override
    public void tick(long currentTick) {
        BrewingStandContainer container = containerHolderComponent.getContainer();
        tickBrewingStand(container);
        container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_FUEL_AMOUNT, fuelAmount);
        container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_FUEL_TOTAL, fuelTotal);
    }

    protected void tickBrewingStand(BrewingStandContainer container) {
        if (!checkFuel(container)) {
            return;
        }

        var reagent = container.getReagent();
        var a = findRecipe(container.getResult(0), reagent);
        var b = findRecipe(container.getResult(1), reagent);
        var c = findRecipe(container.getResult(2), reagent);

        if (a == null && b == null && c == null) {
            brewTime = MAX_BREW_TIME;
            container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_BREW_TIME, 0); // We should reset brew animation
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
            if (brewTime % 40 == 0) {
                container.sendContainerData(ContainerSetDataPacket.BREWING_STAND_BREW_TIME, brewTime);
            }

            brewTime--;
            return;
        }

        if (container.isEmpty(BrewingStandContainer.REAGENT_SLOT)) {
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

        for (int i = 0; i < event.getResult().length; i++) {
            var itemStack = event.getResult()[i];
            if (itemStack != null) {
                container.setResult(i, itemStack);
            }
        }

        if (reagent.getCount() > 1) {
            reagent.reduceCount(1);
            container.notifySlotChange(BrewingStandContainer.REAGENT_SLOT);
        } else {
            container.clearSlot(BrewingStandContainer.REAGENT_SLOT);
        }

        fuelAmount--;
        getDimension().addSound(position, Sound.RANDOM_POTION_BREWED);
    }

    protected boolean checkFuel(BrewingStandContainer container) {
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
            container.notifySlotChange(BrewingStandContainer.FUEL_SLOT);
        } else {
            container.clearSlot(BrewingStandContainer.FUEL_SLOT);
        }

        fuelAmount = 20;
        fuelTotal = fuelAmount;
        return true;
    }

    protected PotionMixRecipe findRecipe(ItemStack ingredient, ItemStack reagent) {
        return Registries.POTION_MIX_RECIPES.get(PotionMixRecipe.buildIdentifier(ingredient, reagent));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putShort(TAG_COOK_TIME, (short) brewTime)
                .putShort(TAG_FUEL_AMOUNT, (short) fuelAmount)
                .putShort(TAG_FUEL_TOTAL, (short) fuelTotal)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_COOK_TIME, value -> brewTime = value);
        nbt.listenForShort(TAG_FUEL_AMOUNT, value -> fuelAmount = value);
        nbt.listenForShort(TAG_FUEL_TOTAL, value -> fuelTotal = value);
    }
}
