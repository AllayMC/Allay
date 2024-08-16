package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.component.common.BlockEntityBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2024/8/16
 *
 * @author daoge_cmd
 */
public interface BlockEntitySignBaseComponent extends BlockEntityBaseComponent {
    SignText getFrontText();

    SignText getBackText();

    boolean isWaxed();

    void setWaxed(boolean waxed);

    void openSignEditorFor(EntityPlayer player, boolean frontSide);

    interface SignText {
        String[] getText();

        void setText(String[] text);

        boolean isGlowing();

        void setGlowing(boolean glowing);

        String flattenText();

        NbtMap saveNBT();
    }
}
