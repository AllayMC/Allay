package org.allaymc.server.blockentity.component.furnace;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.impl.FurnaceContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.input.FurnaceInput;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFurnaceBaseComponent {

    public static final int MAX_COOK_TIME = 200;

    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    @Getter
    protected short burnTime; // unit: gt
    @Getter
    protected short cookTime; // unit: gt
    @Getter
    protected short burnDuration; // unit: gt
    // TODO: give player xp
    @Getter
    protected int storedXPInt;

    public BlockEntityFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.FURNACE;
    }

    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_FURNACE;
    }

    @Override
    public String getFurnaceRecipeTag() {
        return FurnaceRecipe.FURNACE_TAG;
    }

    @Override
    public float getSpeedNormal() {
        return 1;
    }

    @Override
    public float getSpeedWhenFurnaceTypeMostSuitable() {
        return 1;
    }

    protected float getSpeed(FurnaceRecipe furnaceRecipe, FurnaceInput furnaceInput) {
        return furnaceRecipe.isFurnaceTypeMostSuitable(furnaceInput) ? getSpeedWhenFurnaceTypeMostSuitable() : getSpeedNormal();
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putShort("BurnTime", burnTime)
                .putShort("CookTime", cookTime)
                .putShort("BurnDuration", burnDuration)
                .putInt("StoredXPInt", storedXPInt);

        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort("BurnTime", value -> burnTime = value);
        nbt.listenForShort("CookTime", value -> cookTime = value);
        nbt.listenForShort("BurnDuration", value -> burnDuration = value);
        nbt.listenForInt("StoredXPInt", value -> storedXPInt = value);
    }

    @Override
    public void setLit(boolean lit) {
        var currentBlockState = getDimension().getBlockState(position);
        if (currentBlockState.getBlockType() == (lit ? getLitBlockType() : getUnlitBlockType())) {
            return;
        }
        var propertyValues = currentBlockState.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue <?, ?, ?>[]::new);
        getDimension().setBlockState(
                position, lit ? getLitBlockType().ofState(propertyValues) : getUnlitBlockType().ofState(propertyValues),
                0, true, true, false
        );
    }

    @Override
    public void tick() {
        if (burnTime > 0) {
            burnTime--;
        }

        FurnaceContainer container = containerHolderComponent.getContainer();
        if (container.isEmpty(FurnaceContainer.INGREDIENT_SLOT)) {
            cookTime = 0;
            sendFurnaceDataToContainerViewers();
            return;
        }

        var ingredient = container.getIngredient();
        var furnaceRecipe = matchFurnaceRecipe(ingredient);
        if (furnaceRecipe == null) {
            return;
        }

        var furnaceInput = new FurnaceInput(ingredient, getFurnaceRecipeTag());
        if (!furnaceRecipe.match(furnaceInput)) {
            log.warn("Furnace recipe does not match input! Recipe: {}, Input: {}", furnaceRecipe.getIdentifier(), ingredient.getItemType().getIdentifier());
            return;
        }

        if (!container.isEmpty(FurnaceContainer.RESULT_SLOT) && furnaceRecipe.getOutput().getItemType() != container.getResult().getItemType()) {
            // Output slot already have a different item, so we can't cook
            return;
        }

        if (container.getResult().getCount() == container.getResult().getItemData().maxStackSize()) {
            // Output slot is full
            return;
        }

        if (!checkFuel()) {
            sendFurnaceDataToContainerViewers();
            return;
        }

        cookTime += (short) getSpeed(furnaceRecipe, furnaceInput);

        if (cookTime < MAX_COOK_TIME) {
            sendFurnaceDataToContainerViewers();
            // Not finished
            return;
        }

        // TODO: Event
        var previousResult = container.getResult();
        if (previousResult == Container.EMPTY_SLOT_PLACE_HOLDER) {
            container.setResult(furnaceRecipe.getOutput());
        } else {
            if (previousResult.getItemType() != furnaceRecipe.getOutput().getItemType()) {
                log.warn("Furnace result slot already has different item! Previous: {}, New: {}", previousResult.getItemType().getIdentifier(), furnaceRecipe.getOutput().getItemType().getIdentifier());
                container.setResult(furnaceRecipe.getOutput());
                return;
            }
            previousResult.setCount(previousResult.getCount() + 1);
            container.onSlotChange(FurnaceContainer.RESULT_SLOT);
        }

        cookTime = 0;
        storedXPInt += (int) furnaceRecipe.getOutput().getItemData().furnaceXPMultiplier();

        sendFurnaceDataToContainerViewers();
    }

    protected void sendFurnaceDataToContainerViewers() {
        var container = containerHolderComponent.getContainer();
        // NOTICE: This is not an error, ask mojang for the reason why you should "/ getSpeedWhenFurnaceTypeMostSuitable()"
        container.sendContainerData(ContainerSetDataPacket.FURNACE_TICK_COUNT, (int) (cookTime / getSpeedWhenFurnaceTypeMostSuitable()));
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_TIME, burnTime);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_DURATION, burnDuration);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_STORED_XP, storedXPInt);
    }

    protected boolean checkFuel() {
        if (burnTime > 0) {
            return true;
        }

        FurnaceContainer container = containerHolderComponent.getContainer();
        var fuel = container.getFuel();

        if (fuel == Container.EMPTY_SLOT_PLACE_HOLDER) {
            resetToNoFuelState();
            return false;
        }

        if (!isFuel(container.getFuel())) {
            resetToNoFuelState();
            return false;
        }

        // TODO: Event
        if (fuel.getCount() > 1) {
            fuel.setCount(fuel.getCount() - 1);
            container.onSlotChange(FurnaceContainer.FUEL_SLOT);
        } else {
            container.clearSlot(FurnaceContainer.FUEL_SLOT);
        }

        burnDuration = (short) fuel.getItemData().furnaceBurnDuration();
        burnTime = burnDuration;
        setLit(true);

        return true;
    }

    protected void resetToNoFuelState() {
        setLit(false);
        burnTime = 0;
        cookTime = 0;
        burnDuration = 0;
    }

    protected FurnaceRecipe matchFurnaceRecipe(ItemStack ingredient) {
        var furnaceRecipe = Registries.FURNACE_RECIPES.get(FurnaceRecipe.buildFurnaceRecipeIdentifier(ingredient.getItemType(), getFurnaceRecipeTag()));
        if (furnaceRecipe != null) return furnaceRecipe;
        // BlastFurnace/Smoker can also use normal furnace recipe
        return Registries.FURNACE_RECIPES.get(FurnaceRecipe.buildFurnaceRecipeIdentifier(ingredient.getItemType(), FurnaceRecipe.FURNACE_TAG));
    }

    protected boolean isFuel(ItemStack itemStack) {
        return itemStack.getItemData().furnaceBurnDuration() > 0;
    }
}
