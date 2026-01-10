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
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockEntityCampfireBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCampfireBaseComponent {

    protected static final String TAG_ITEM = "Item";
    protected static final String TAG_ITEM_TIME = "ItemTime";

    @ComponentObject
    protected BlockEntityCampfire thisCampfire;

    @Getter
    protected ItemStack[] items;
    protected int[] cookTimes;
    protected FurnaceRecipe[] recipes;

    public BlockEntityCampfireBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.items = new ItemStack[4];
        this.cookTimes = new int[4];
        this.recipes = new FurnaceRecipe[4];
        for (int i = 0; i < 4; i++) {
            items[i] = ItemAirStack.AIR_STACK;
        }
    }

    @Override
    public FurnaceRecipe.Type getFurnaceRecipeType() {
        return FurnaceRecipe.Type.CAMPFIRE;
    }

    @Override
    public void tick(long currentTick) {
        if (isExtinguished()) {
            return;
        }

        // Play crackle sound randomly (approximately every 3 seconds)
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
                    // No valid recipe, drop the item
                    dropItem(slot);
                    changed = true;
                    continue;
                }
                cookTimes[slot] = COOK_TIME;
                recipes[slot] = recipe;
            }

            if (cookTimes[slot] > 0) {
                cookTimes[slot]--;
            }

            if (cookTimes[slot] <= 0) {
                // Cooking finished
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

    protected boolean isExtinguished() {
        return getBlockState().getPropertyValue(BlockPropertyTypes.EXTINGUISHED);
    }

    protected FurnaceRecipe matchRecipe(ItemStack ingredient) {
        var recipe = (FurnaceRecipe) Registries.RECIPES.get(
                FurnaceRecipe.buildIdentifier(ingredient, getFurnaceRecipeType())
        );
        if (recipe != null && recipe.match(new FurnaceRecipeInput(ingredient, getFurnaceRecipeType()))) {
            return recipe;
        }
        return null;
    }

    protected void dropItem(int slot) {
        var item = items[slot];
        if (item != ItemAirStack.AIR_STACK) {
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
    }

    protected void dropCookedItem(int slot, ItemStack output) {
        var pos = new Vector3d(
                position.x() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3),
                position.y() + 0.5,
                position.z() + 0.5 + ThreadLocalRandom.current().nextDouble(-0.3, 0.3)
        );
        getDimension().dropItem(output, pos);
        items[slot] = ItemAirStack.AIR_STACK;
        cookTimes[slot] = 0;
        recipes[slot] = null;
    }

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        for (int i = 0; i < 4; i++) {
            if (items[i] != ItemAirStack.AIR_STACK) {
                getDimension().dropItem(items[i], MathUtils.center(position));
                items[i] = ItemAirStack.AIR_STACK;
            }
        }
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        for (int i = 0; i < 4; i++) {
            int index = i + 1;
            if (items[i] != ItemAirStack.AIR_STACK) {
                builder.putCompound(TAG_ITEM + index, items[i].saveNBT());
                builder.putInt(TAG_ITEM_TIME + index, cookTimes[i]);
            }
        }
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
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

    @Override
    public ItemStack getItemStack(int slot) {
        if (slot < 0 || slot >= 4) {
            return ItemAirStack.AIR_STACK;
        }
        return items[slot];
    }

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
    }

    @Override
    public int getCookTime(int slot) {
        if (slot < 0 || slot >= 4) {
            return 0;
        }
        return cookTimes[slot];
    }

    @Override
    public void setCookTime(int slot, int cookTime) {
        if (slot < 0 || slot >= 4) {
            return;
        }
        cookTimes[slot] = cookTime;
    }

    @Override
    public boolean canAcceptItemStack(ItemStack item) {
        if (item == null || item == ItemAirStack.AIR_STACK) {
            return false;
        }
        // Check if there's an empty slot
        for (int i = 0; i < 4; i++) {
            if (items[i] == ItemAirStack.AIR_STACK) {
                // Check if there's a valid recipe for this item
                return matchRecipe(item) != null;
            }
        }
        return false;
    }

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
