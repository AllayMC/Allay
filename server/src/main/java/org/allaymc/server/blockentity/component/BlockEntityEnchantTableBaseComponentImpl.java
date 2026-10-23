package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityEnchantTableBaseComponentImpl} retains the
 * enchantment-table book's rotation value alongside common block-entity data.
 * The component reads and writes that value as an NBT float; it does not
 * advance the rotation or implement book animation during ticking.
 *
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableBaseComponentImpl extends BlockEntityBaseComponentImpl {

    /**
     * {@code TAG_ROTT} names the float entry used to retain the book rotation
     * during enchantment-table NBT loading and saving.
     */
    protected static final String TAG_ROTT = "rott";

    /**
     * Retained book rotation value.
     *
     * @see <a href="https://minecraft.wiki/w/Bedrock_Edition_level_format/Block_entity_format#Enchantment_Table">Enchantment Table</a>
     */
    protected float bookRot = 0;

    /**
     * Creates common enchantment-table state with an initially zero book
     * rotation.
     */
    public BlockEntityEnchantTableBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Builds common NBT and appends the retained book-rotation float value.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putFloat(TAG_ROTT, bookRot).build();
    }

    /**
     * Loads common data and any supplied book-rotation float. An absent
     * {@code "rott"} tag preserves the existing rotation, and this method does
     * not start or update a separate animation.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForFloat(TAG_ROTT, value -> bookRot = value);
    }
}
