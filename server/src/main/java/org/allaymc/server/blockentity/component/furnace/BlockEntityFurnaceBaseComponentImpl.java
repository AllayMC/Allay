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
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.FurnaceContainerImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ContainerSetDataPacket;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFurnaceBaseComponent {

    public static final String TAG_BURN_TIME = "BurnTime";
    public static final String TAG_COOK_TIME = "CookTime";
    public static final String TAG_BURN_DURATION = "BurnDuration";
    public static final String TAG_STORED_XP_INT = "StoredXPInt";

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
    protected int storedXP;

    protected int currentIngredientStackUniqueId;
    protected FurnaceRecipe currentRecipe;
    protected FurnaceRecipeInput currentRecipeInput;

    public BlockEntityFurnaceBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.currentIngredientStackUniqueId = Integer.MAX_VALUE;
        this.currentRecipe = null;
        this.currentRecipeInput = null;
    }

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

    @Override
    public BlockType<?> getUnlitBlockType() {
        return BlockTypes.FURNACE;
    }

    @Override
    public BlockType<?> getLitBlockType() {
        return BlockTypes.LIT_FURNACE;
    }

    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.FURNACE;
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
        return currentRecipe.getType() == getFurnaceRecipeType() ? getIdealSpeed() : getNormalSpeed();
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putShort(TAG_BURN_TIME, (short) burnTime)
                .putShort(TAG_COOK_TIME, (short) cookTime)
                .putShort(TAG_BURN_DURATION, (short) burnDuration)
                .putInt(TAG_STORED_XP_INT, storedXP)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort(TAG_BURN_TIME, value -> burnTime = value);
        nbt.listenForShort(TAG_COOK_TIME, value -> cookTime = value);
        nbt.listenForShort(TAG_BURN_DURATION, value -> burnDuration = value);
        nbt.listenForInt(TAG_STORED_XP_INT, value -> storedXP = value);
    }

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

    @Override
    public void tick(long currentTick) {
        var lastBurnTime = burnTime;
        tickFurnace();
        if (lastBurnTime == 1 && burnTime == 0) setLit(false);
        if (lastBurnTime == 0 && burnTime > 0) setLit(true);
        sendFurnaceContainerData();

        // Play crackle sound randomly when burning (approximately every 3 seconds)
        if (burnTime > 0 && ThreadLocalRandom.current().nextFloat() <= 0.016f) {
            getDimension().addSound(MathUtils.center(position), getCrackleSound());
        }
    }

    protected SimpleSound getCrackleSound() {
        return SimpleSound.FURNACE_CRACKLE;
    }

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

        // Calculate the amount of experience to grant
        // Round the experience down to the nearest integer, and the remaining
        // XP is a chance to be granted an additional experience point
        var xp = output.getItemType().getItemData().furnaceXPMultiplier();
        int earned = (int) Math.floor(xp);
        var chance = xp - earned;
        if (chance > 0 && ThreadLocalRandom.current().nextFloat() < chance) {
            earned++;
        }
        storedXP += earned;
    }

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

    protected void tryDropStoredXP() {
        if (storedXP < 1) return;

        var pos = new Vector3d(
                position.x() + 0.5,
                position.y() + 1.5,
                position.z() + 0.5
        );
        getDimension().dropXpOrb(pos, storedXP);
        storedXP = 0;
    }

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        tryDropStoredXP();
    }

    @Override
    public boolean sendToClient() {
        return false;
    }

    protected void sendFurnaceContainerData() {
        var container = containerHolderComponent.getContainer();
        // NOTICE: This is not an error, ask mojang for the reason why you should "/ getIdealSpeed()"
        container.sendContainerData(ContainerSetDataPacket.FURNACE_TICK_COUNT, (int) (cookTime / getIdealSpeed()));
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_TIME, burnTime);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_LIT_DURATION, burnDuration);
        container.sendContainerData(ContainerSetDataPacket.FURNACE_STORED_XP, storedXP);
    }

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

        burnDuration = (int) fuel.getItemType().getItemData().furnaceBurnDuration();
        burnTime = burnDuration;

        return true;
    }

    protected void resetToNoFuelState() {
        burnTime = 0;
        cookTime = 0;
        burnDuration = 0;
    }

    protected FurnaceRecipe matchFurnaceRecipe(ItemStack ingredient) {
        var furnaceRecipe = (FurnaceRecipe) Registries.RECIPES.get(FurnaceRecipe.buildIdentifier(ingredient, getFurnaceRecipeType()));
        if (furnaceRecipe != null) {
            return furnaceRecipe;
        }

        // Blast furnace and smoker can also use a normal furnace recipe
        return (FurnaceRecipe) Registries.RECIPES.get(FurnaceRecipe.buildIdentifier(ingredient, FurnaceRecipe.Type.FURNACE));
    }

    protected boolean isFuel(ItemStack itemStack) {
        return itemStack.getItemType().getItemData().furnaceBurnDuration() > 0;
    }
}
