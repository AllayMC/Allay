package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableBaseComponentImpl extends BlockEntityBaseComponentImpl {

    protected static final String TAG_ROTT = "rott";

    /**
     * The clockwise rotation of the book in radians. Top of the book points West when 0
     *
     * @see <a href="https://minecraft.wiki/w/Bedrock_Edition_level_format/Block_entity_format#Enchantment_Table">Enchantment Table</a>
     */
    protected float bookRot = 0;

    public BlockEntityEnchantTableBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putFloat(TAG_ROTT, bookRot).build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForFloat(TAG_ROTT, value -> bookRot = value);
    }
}
