package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.CompassRoseDirection;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntitySignBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.block.SignTextChangeEvent;
import org.allaymc.api.eventbus.event.block.SignWaxEvent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.network.NetworkHelper;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.OpenSignPacket;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockEntitySignBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntitySignBaseComponent {

    protected static final String TAG_IS_WAXED = "IsWaxed";
    protected static final String TAG_FRONT_TEXT = "FrontText";
    protected static final String TAG_BACK_TEXT = "BackText";
    protected static final String TAG_LOCKED_FOR_EDITING_BY = "LockedForEditingBy";
    protected static final String TAG_TEXT = "Text";
    protected static final String TAG_IGNORE_LIGHTING = "IgnoreLighting";
    protected static final String TAG_SIGN_TEXT_COLOR = "SignTextColor";
    protected static final String TAG_HIDE_GLOW_OUTLINE = "HideGlowOutline";
    protected static final String TAG_PERSIST_FORMATTING = "PersistFormatting";
    protected static final String TAG_TEXT_OWNER = "TextOwner";

    protected SignText frontText = new SignTextImpl();
    protected SignText backText = new SignTextImpl();
    protected boolean waxed = false;

    public BlockEntitySignBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForBoolean(TAG_IS_WAXED, value -> waxed = value);
        nbt.listenForCompound(TAG_FRONT_TEXT, value -> frontText = readSignTextFromNBT(value));
        nbt.listenForCompound(TAG_BACK_TEXT, value -> backText = readSignTextFromNBT(value));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_IS_WAXED, waxed)
                .putCompound(TAG_FRONT_TEXT, frontText.saveNBT())
                .putCompound(TAG_BACK_TEXT, backText.saveNBT())
                // Unused
                .putLong(TAG_LOCKED_FOR_EDITING_BY, -1L)
                .build();
    }

    @Override
    public void openSignEditorFor(Player player, boolean frontSide) {
        var packet = new OpenSignPacket();
        packet.setPosition(NetworkHelper.toNetwork(getPosition()));
        packet.setFrontSide(frontSide);
        player.sendPacket(packet);
    }

    @Override
    public void applyPlayerChange(EntityPlayer player, NbtMap nbt) {
        String[] newText;
        boolean isFrontSide = true;
        if (!frontText.flattenText().equals(nbt.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT))) {
            newText = AllayStringUtils.fastSplit(nbt.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT), "\n").toArray(String[]::new);
        } else if (!backText.flattenText().equals(nbt.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT))) {
            isFrontSide = false;
            newText = AllayStringUtils.fastSplit(nbt.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT), "\n").toArray(String[]::new);
        } else {
            // No changes
            return;
        }

        var event = new SignTextChangeEvent(new Block(getBlockState(), position, 0), newText, player);
        if (!event.call()) {
            return;
        }

        newText = event.getText();

        if (isFrontSide) {
            frontText.setText(newText);
        } else {
            backText.setText(newText);
        }
    }

    @Override
    public boolean isWaxed() {
        return waxed;
    }

    @Override
    public void setWaxed(boolean waxed) {
        this.waxed = waxed;
        sendBlockEntityToViewers();
    }

    @EventHandler
    protected void onBlockInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null) return;
        // If a sign is waxed, it cannot be modified.
        if (waxed) return;

        var isFrontSideInteracted = isFrontSideInteracted(event.getInteractInfo().blockFace());
        var itemInHand = player.getItemInHand();

        if (itemInHand.getItemType() == ItemTypes.HONEYCOMB && !waxed) {
            var signWaxEvent = new SignWaxEvent(new Block(getBlockState(), position, 0), player);
            if (!signWaxEvent.call()) return;

            setWaxed(true);
            player.tryConsumeItemInHand();
            player.getDimension().addSound(position.x(), position.y(), position.z(), SimpleSound.WAXED);
            event.setSuccess(true);
            return;
        }

        if (itemInHand.getItemType() == ItemTypes.GLOW_INK_SAC && !(isFrontSideInteracted ? frontText.isGlowing() : backText.isGlowing())) {
            if (isFrontSideInteracted) {
                frontText.setGlowing(true);
            } else {
                backText.setGlowing(true);
            }
            player.tryConsumeItemInHand();
            player.getDimension().addSound(player.getLocation(), SimpleSound.GLOW_INK_SAC_USED);
            event.setSuccess(true);
            return;
        }

        if (itemInHand.getItemType() == ItemTypes.INK_SAC && isFrontSideInteracted ? frontText.isGlowing() : backText.isGlowing()) {
            if (isFrontSideInteracted) {
                frontText.setGlowing(false);
            } else {
                backText.setGlowing(false);
            }
            player.tryConsumeItemInHand();
            player.getDimension().addSound(player.getLocation(), SimpleSound.GLOW_INK_SAC_USED);
            event.setSuccess(true);
            return;
        }

        if (player.isActualPlayer()) {
            openSignEditorFor(player.getController(), isFrontSideInteracted);
            event.setSuccess(true);
        }
    }

    protected boolean isFrontSideInteracted(BlockFace interactedFace) {
        var blockState = getBlockState();
        if (blockState.getBlockType().hasProperty(BlockPropertyTypes.FACING_DIRECTION)) {
            // Wall sign
            return interactedFace == BlockFace.fromIndex(blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
        }
        var signDirection = CompassRoseDirection.from(blockState.getPropertyValue(BlockPropertyTypes.GROUND_SIGN_DIRECTION));
        return switch (signDirection) {
            case EAST -> interactedFace == BlockFace.EAST;
            case SOUTH -> interactedFace == BlockFace.SOUTH;
            case WEST -> interactedFace == BlockFace.WEST;
            case NORTH -> interactedFace == BlockFace.NORTH;
            case NORTH_EAST, NORTH_NORTH_EAST, EAST_NORTH_EAST -> interactedFace == BlockFace.EAST || interactedFace == BlockFace.NORTH;
            case NORTH_WEST, NORTH_NORTH_WEST, WEST_NORTH_WEST -> interactedFace == BlockFace.WEST || interactedFace == BlockFace.NORTH;
            case SOUTH_EAST, SOUTH_SOUTH_EAST, EAST_SOUTH_EAST -> interactedFace == BlockFace.EAST || interactedFace == BlockFace.SOUTH;
            case SOUTH_WEST, SOUTH_SOUTH_WEST, WEST_SOUTH_WEST -> interactedFace == BlockFace.WEST || interactedFace == BlockFace.SOUTH;
        };
    }

    protected SignText readSignTextFromNBT(NbtMap nbt) {
        var signText = new SignTextImpl();

        nbt.listenForString(TAG_TEXT, value -> {
            signText.text = AllayStringUtils.fastSplit(value, "\n", 4).toArray(String[]::new);
        });
        nbt.listenForBoolean(TAG_IGNORE_LIGHTING, value -> signText.glowing = value);

        return signText;
    }

    protected class SignTextImpl implements SignText {

        protected String[] text = new String[]{""};
        protected boolean glowing = false;
        // TODO: Color

        private static void sanitizeText(String[] lines) {
            for (int i = 0; i < lines.length; i++) {
                // Don't allow excessive text per line.
                if (lines[i] != null) {
                    lines[i] = lines[i].substring(0, Math.min(255, lines[i].length()));
                }
            }
        }

        @Override
        public String[] getText() {
            return text;
        }

        @Override
        public void setText(String[] text) {
            sanitizeText(text);
            this.text = text;
            sendBlockEntityToViewers();
        }

        @Override
        public boolean isGlowing() {
            return glowing;
        }

        @Override
        public void setGlowing(boolean glowing) {
            this.glowing = glowing;
            sendBlockEntityToViewers();
        }

        @Override
        public String flattenText() {
            return String.join("\n", text);
        }

        @Override
        public NbtMap saveNBT() {
            return NbtMap.builder()
                    .putString(TAG_TEXT, flattenText())
                    .putBoolean(TAG_IGNORE_LIGHTING, glowing)
                    // Not implemented
                    .putInt(TAG_SIGN_TEXT_COLOR, -16777216)
                    // true if the outer glow of a sign with glowing text does not show.
                    .putBoolean(TAG_HIDE_GLOW_OUTLINE, false)
                    // Unknown. Maybe save formatting character like §?
                    .putBoolean(TAG_PERSIST_FORMATTING, true)
                    // Unknown. The player who placed the sign?
                    .putString(TAG_TEXT_OWNER, "")
                    .build();
        }
    }

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        if (event.getPlacementInfo() == null) {
            return;
        }

        var player = event.getPlacementInfo().player();
        if (player.isActualPlayer()) {
            openSignEditorFor(player.getController(), true);
        }
    }
}
