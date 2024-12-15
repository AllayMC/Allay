package org.allaymc.server.blockentity.component.furnace;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.container.impl.FurnaceContainer;
import org.allaymc.api.eventbus.event.container.FurnaceConsumeFuelEvent;
import org.allaymc.api.eventbus.event.container.FurnaceSmeltEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.input.FurnaceInput;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;
import org.joml.Vector3f;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFurnaceBaseComponent {

    public static final int MAX_COOK_TIME = 200;

    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    @ComponentObject
    protected BlockEntityFurnace thisBlockEntityFurnace;

    @Getter
    protected int burnTime; // unit: gt
    @Getter
    protected int cookTime; // unit: gt
    @Getter
    protected int burnDuration; // unit: gt
    @Getter
    @Setter
    protected float storedXP;

    protected int currentIngredientStackNetworkId = Integer.MAX_VALUE;
    protected FurnaceRecipe currentFurnaceRecipe = null;
    protected FurnaceInput currentFurnaceInput = null;

    public BlockEntityFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        FurnaceContainer container = containerHolderComponent.getContainer();
        container.addOnSlotChangeListener(FurnaceContainer.RESULT_SLOT, item -> {
            if (item != ItemAirStack.AIR_STACK) return;
            tryDropStoredXP();
        });
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
    public float getNormalSpeed() {
        return 1;
    }

    @Override
    public float getIdealSpeed() {
        return 1;
    }

    @Override
    public float getCurrentSpeed() {
        return currentFurnaceRecipe.isFurnaceTypeMostSuitable(currentFurnaceInput) ? getIdealSpeed() : getNormalSpeed();
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putShort("BurnTime", (short) burnTime)
                .putShort("CookTime", (short) cookTime)
                .putShort("BurnDuration", (short) burnDuration)
                .putFloat("StoredXP", storedXP)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort("BurnTime", value -> burnTime = value);
        nbt.listenForShort("CookTime", value -> cookTime = value);
        nbt.listenForShort("BurnDuration", value -> burnDuration = value);
        nbt.listenForFloat("StoredXP", value -> storedXP = value);
    }

    @Override
    public void setLit(boolean lit) {
        var currentBlockState = getDimension().getBlockState(position);
        var newBlockType = lit ? getLitBlockType() : getUnlitBlockType();
        if (currentBlockState.getBlockType() == newBlockType) return;

        var propertyValues = currentBlockState.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue<?, ?, ?>[]::new);
        getDimension().setBlockState(
                position, newBlockType.ofState(propertyValues),
                0, true, true, false
        );
    }

    @Override
    public void tick(long currentTick) {
        var lastBurnTime = burnTime;
        tickFurnace();
        if (lastBurnTime == 1 && burnTime == 0) setLit(false);
        if (lastBurnTime == 0 && burnTime > 0) setLit(true);
        sendFurnaceContainerData();
    }

    protected void tickFurnace() {
        if (burnTime > 0) burnTime--;

        FurnaceContainer container = containerHolderComponent.getContainer();
        if (container.isEmpty(FurnaceContainer.INGREDIENT_SLOT)) {
            cookTime = 0;
            return;
        }

        var ingredient = container.getIngredient();
        if (ingredient.getStackNetworkId() != currentIngredientStackNetworkId && !checkIngredient(ingredient)) {
            return;
        }

        var output = currentFurnaceRecipe.getOutput();
        var outputItemType = output.getItemType();
        if (
            // Output slot already have a different item, so we can't cook
                (!container.isEmpty(FurnaceContainer.RESULT_SLOT) && outputItemType != container.getResult().getItemType()) ||
                // Output slot is full
                container.getResult().isFull() ||
                // No fuel available
                !checkFuel()
        ) {
            return;
        }

        cookTime += (int) getCurrentSpeed();

        // Not finished
        if (cookTime < MAX_COOK_TIME) return;

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
                container.setResult(output.copy());
                return;
            }
            currentResult.increaseCount(1);
            container.notifySlotChange(FurnaceContainer.RESULT_SLOT);
        }

        storedXP += output.getItemData().furnaceXPMultiplier();
    }

    protected boolean checkIngredient(ItemStack ingredient) {
        var furnaceRecipe = matchFurnaceRecipe(ingredient);
        if (furnaceRecipe == null) return false;

        var furnaceInput = new FurnaceInput(ingredient, getFurnaceRecipeTag());
        if (!furnaceRecipe.match(furnaceInput)) {
            log.warn("Furnace recipe does not match input! Recipe: {}, Input: {}", furnaceRecipe.getIdentifier(), ingredient.getItemType().getIdentifier());
            return false;
        }

        currentIngredientStackNetworkId = ingredient.getStackNetworkId();
        currentFurnaceRecipe = furnaceRecipe;
        currentFurnaceInput = furnaceInput;
        return true;
    }

    protected void tryDropStoredXP() {
        if (storedXP < 1) return;

        var pos = new Vector3f(
                position.x() + 0.5f,
                position.y() + 1.5f,
                position.z() + 0.5f
        );
        getDimension().dropXpOrb(pos, (int) storedXP);
        storedXP = 0;
    }

    @Override
    public void onReplace(CBlockOnReplaceEvent event) {
        super.onReplace(event);
        tryDropStoredXP();
    }

    @Override
    public boolean sendToClient() {
        return false;
    }

    protected void sendFurnaceContainerData() {
        var container = containerHolderComponent.getContainer();
        if (container.getViewers().isEmpty()) return;
        // NOTICE: This is not an error, ask mojang for the reason why you should "/ getIdealSpeed()"
        container.sendContainerData(ContainerSetDataPacket.FURNACE_TICK_COUNT, (int) (cookTime / getIdealSpeed()));
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_TIME, burnTime);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_DURATION, burnDuration);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_STORED_XP, (int) storedXP);
    }

    protected boolean checkFuel() {
        if (burnTime > 0) return true;

        FurnaceContainer container = containerHolderComponent.getContainer();

        var fuel = container.getFuel();
        if (!isFuel(fuel)) {
            resetToNoFuelState();
            return false;
        }

        var event = new FurnaceConsumeFuelEvent(thisBlockEntityFurnace, fuel);
        if (!event.call()) return false;

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

        burnDuration = (int) fuel.getItemData().furnaceBurnDuration();
        burnTime = burnDuration;

        return true;
    }

    protected void resetToNoFuelState() {
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
