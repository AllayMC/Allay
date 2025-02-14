package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockStateSafeGetter;
import org.allaymc.api.blockentity.component.BlockEntityFlowerPotBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

/**
 * @author Cdm2883
 */
public class BlockEntityFlowerPotBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFlowerPotBaseComponent {

    protected static final String TAG_PLANT_BLOCK = "PlantBlock";

    @Getter
    private BlockState plantBlock;

    public BlockEntityFlowerPotBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean setPlantBlock(BlockState block) {
        if (block != null && !isValidPlant(block)) return false;
        plantBlock = block;

        var position = getPosition();
        var dimension = position.dimension();
        dimension.updateBlockProperty(BlockPropertyTypes.UPDATE_BIT, block != null, position);
        sendBlockEntityDataPacketToViewers();
        return true;
    }

    private boolean isValidPlant(BlockState block) {
        return block.getBlockType().hasBlockTag(BlockCustomTags.POTTABLE_PLANT);
    }

    @Override
    public void onReplace(CBlockOnReplaceEvent event) {
        if (plantBlock == null) return;

        var position = event.getCurrentBlockState().pos();
        var dimension = position.dimension();
        dimension.dropItem(plantBlock.toItemStack(), new Vector3d(
                position.x() + 0.5,
                position.y() + 0.5,
                position.z() + 0.5
        ));
        plantBlock = null;
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        if (plantBlock != null) builder.putCompound(TAG_PLANT_BLOCK, this.plantBlock.getBlockStateTag());
        else builder.remove(TAG_PLANT_BLOCK);
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_PLANT_BLOCK, value -> this.plantBlock = BlockStateSafeGetter.fromNBT(value));
    }
}
