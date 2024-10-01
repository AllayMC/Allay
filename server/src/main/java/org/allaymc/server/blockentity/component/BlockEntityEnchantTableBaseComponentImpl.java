package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableBaseComponentImpl extends BlockEntityBaseComponentImpl {

    // The clockwise rotation of the book in radians. Top of the book points West when 0
    // See https://minecraft.wiki/w/Bedrock_Edition_level_format/Block_entity_format#Enchantment_Table
    protected float rott = 0;

    public BlockEntityEnchantTableBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putFloat("rot", rott).build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForFloat("rott", value -> rott = value);
    }
}
