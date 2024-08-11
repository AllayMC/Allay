package org.allaymc.server.blockentity.component.furnace;

import lombok.Getter;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class BlockEntityFurnaceBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFurnaceBaseComponent {
    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    @Getter
    protected short burnTime;
    @Getter
    protected short cookTime;
    @Getter
    protected short burnDuration;
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
    public int getSpeedMultiplier() {
        return 1;
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
        getDimension().setBlockState(position, lit ? getLitBlockType().getDefaultState() : getUnlitBlockType().getDefaultState());
    }

    @Override
    public void tick() {
        var container = containerHolderComponent.getContainer(FullContainerType.FURNACE);
        // TODO: Furnace logic
    }
}
