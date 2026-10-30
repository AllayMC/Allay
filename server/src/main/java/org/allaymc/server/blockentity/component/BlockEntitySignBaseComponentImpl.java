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
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntitySignBaseComponentImpl} stores front and back sign text,
 * glowing flags, and waxing state and handles sign editing interactions. Each
 * face owns a mutable line array whose setter sanitizes entries in place and
 * broadcasts through the enclosing component. Loading face compounds replaces
 * the corresponding face objects instead of mutating previously returned
 * objects.
 *
 * <p>
 * {@code applyPlayerChange} compares flattened front text before back text and
 * applies at most one changed face through a cancellable event. Direct player
 * NBT application does not check waxing or editor ownership here. Interaction
 * handling separately blocks waxed signs, handles item-based changes, and opens
 * the selected editor face for actual players. NBT stores only text and glowing
 * state as variable face fields; remaining compatibility entries are fixed
 * values.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntitySignBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntitySignBaseComponent {

    /**
     * {@code TAG_IS_WAXED} names the persisted wax flag. The sign tag constants
     * identify front/back text compounds, their display fields, and saved
     * editing metadata.
     */
    protected static final String TAG_IS_WAXED = "IsWaxed";
    /**
     * Persisted front-face text compound.
     */
    protected static final String TAG_FRONT_TEXT = "FrontText";
    /**
     * Persisted back-face text compound.
     */
    protected static final String TAG_BACK_TEXT = "BackText";
    /**
     * Persisted editor identity placeholder.
     */
    protected static final String TAG_LOCKED_FOR_EDITING_BY = "LockedForEditingBy";
    /**
     * Persisted newline-separated face text.
     */
    protected static final String TAG_TEXT = "Text";
    /**
     * Persisted glowing text flag.
     */
    protected static final String TAG_IGNORE_LIGHTING = "IgnoreLighting";
    /**
     * Persisted packed text color.
     */
    protected static final String TAG_SIGN_TEXT_COLOR = "SignTextColor";
    /**
     * Persisted hidden-outline display flag.
     */
    protected static final String TAG_HIDE_GLOW_OUTLINE = "HideGlowOutline";
    /**
     * Persisted formatting retention flag.
     */
    protected static final String TAG_PERSIST_FORMATTING = "PersistFormatting";
    /**
     * Persisted text-owner identity placeholder.
     */
    protected static final String TAG_TEXT_OWNER = "TextOwner";

    /**
     * {@code frontText} holds the mutable front face exposed by its getter.
     * Loading a front compound replaces the object, so earlier references then
     * describe the old face.
     */
    protected SignText frontText = new SignTextImpl();
    /**
     * {@code backText} holds the mutable back face exposed by its getter.
     * Loading a back compound replaces the object, so earlier references then
     * describe the old face.
     */
    protected SignText backText = new SignTextImpl();
    /**
     * Current interaction waxing flag.
     */
    protected boolean waxed = false;

    /**
     * Creates common sign state with two empty text faces and no wax.
     */
    public BlockEntitySignBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads common state, optional waxing, and any supplied face compounds.
     * Missing tags preserve existing values, while supplied compounds replace
     * face objects through the NBT reader without invoking text setters or
     * broadcasting.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForBoolean(TAG_IS_WAXED, value -> waxed = value);
        nbt.listenForCompound(TAG_FRONT_TEXT, value -> frontText = readSignTextFromNBT(value));
        nbt.listenForCompound(TAG_BACK_TEXT, value -> backText = readSignTextFromNBT(value));
    }

    /**
     * Builds common NBT with waxing and both current face representations. The
     * component writes {@code -1L} as the editor placeholder rather than
     * tracking an active editor, and each face contributes its own display
     * entries.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putBoolean(TAG_IS_WAXED, waxed)
                .putCompound(TAG_FRONT_TEXT, frontText.saveNBT())
                .putCompound(TAG_BACK_TEXT, backText.saveNBT())
                // No editor identity is retained, so persist the unlocked
                // placeholder.
                .putLong(TAG_LOCKED_FOR_EDITING_BY, -1L)
                .build();
    }

    /**
     * Applies one changed text face after a cancellable text-change event
     * accepts it. The front comparison wins when both submitted faces differ;
     * the back is considered only when the front is unchanged. Submitted text
     * is split before the event, and the accepted event array passes to the
     * selected face's setter for sanitization and retention. Cancellation
     * prevents this method's assignment. This path does not load other base
     * fields, validate waxing, or check editor ownership before accepting a
     * face change.
     */
    @Override
    public void applyPlayerChange(EntityPlayer player, NbtMap nbt) {
        String[] newText;
        boolean isFrontSide = true;
        // Front text takes precedence when both submitted faces differ, so one
        // event and one setter identify the face changed by this packet.
        if (!frontText.flattenText().equals(nbt.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT))) {
            newText = AllayStringUtils.fastSplit(nbt.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT), "\n").toArray(String[]::new);
        } else if (!backText.flattenText().equals(nbt.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT))) {
            isFrontSide = false;
            newText = AllayStringUtils.fastSplit(nbt.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT), "\n").toArray(String[]::new);
        } else {
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

    /**
     * Returns the stored flag used to block ordinary sign interactions.
     */
    @Override
    public boolean isWaxed() {
        return waxed;
    }

    /**
     * Stores the waxing flag and broadcasts the sign's current state. This
     * direct operation does not emit {@code SignWaxEvent}, consume an item, or
     * prevent subsequent programmatic changes to either text face.
     */
    @Override
    public void setWaxed(boolean waxed) {
        this.waxed = waxed;
        sendBlockEntityToViewers();
    }

    /**
     * Handles waxing, glow changes, or editor opening for a player interaction.
     * Absent players and waxed signs return before item processing. Honeycomb
     * waxing requires an accepted wax event; other accepted changes update a
     * face before attempting hand consumption and playing a sound. The
     * ink-removal condition currently uses a ternary whose alternative reads
     * back-face glowing state, so that state can select removal independently
     * of an ink-item match. Actual players reach the editor path only when no
     * earlier item branch returned.
     */
    @EventHandler
    protected void onBlockInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null) return;
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
            player.getController().viewSignEditor(getPosition(), isFrontSideInteracted);
            event.setSuccess(true);
        }
    }

    /**
     * Returns whether the clicked block face is treated as the sign's front.
     * Wall signs compare the hit with their facing-direction property. Other
     * signs use the ground-sign compass direction: cardinal orientations select
     * one horizontal face, while diagonal sectors accept either bordering
     * cardinal face. Upper and lower hits therefore fall through as back-side
     * interactions. The block state must supply the relevant orientation
     * property for its sign form; this method does not infer orientation from
     * player position.
     */
    protected boolean isFrontSideInteracted(BlockFace interactedFace) {
        var blockState = getBlockState();
        if (blockState.getBlockType().hasProperty(BlockPropertyTypes.FACING_DIRECTION)) {
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

    /**
     * Creates one text face from optional newline text and glowing entries. A
     * new face starts with one empty line and no glow when those entries are
     * absent. Splitting produces at most four array entries and retains
     * remaining newlines inside the fourth entry, so flattening can preserve
     * the full supplied string rather than discarding extra lines. This reader
     * assigns storage directly without setter truncation or viewer updates, and
     * it does not retain other saved face metadata.
     */
    protected SignText readSignTextFromNBT(NbtMap nbt) {
        var signText = new SignTextImpl();

        nbt.listenForString(TAG_TEXT, value -> {
            signText.text = AllayStringUtils.fastSplit(value, "\n", 4).toArray(String[]::new);
        });
        nbt.listenForBoolean(TAG_IGNORE_LIGHTING, value -> signText.glowing = value);

        return signText;
    }

    /**
     * {@code SignTextImpl} retains mutable lines and a glowing flag for one
     * sign face and broadcasts its setters through the enclosing sign
     * component.
     */
    protected class SignTextImpl implements SignText {

        /**
         * {@code text} holds the shared line array. Its setter sanitizes
         * entries in place and retains the supplied array, while its getter
         * exposes that same storage.
         */
        protected String[] text = new String[]{""};
        /**
         * Current face glowing flag.
         */
        protected boolean glowing = false;
        // TODO: Retain face color and expose it through the sign-text API
        // instead of writing a fixed color.

        /**
         * Truncates non-{@code null} entries in the supplied array in place.
         * Each retained string is limited to 255 UTF-16 code units; the array
         * itself must be non-{@code null}, and its entry count remains
         * unchanged.
         */
        private static void sanitizeText(String[] lines) {
            for (int i = 0; i < lines.length; i++) {
                // Limit each line to the stored sign-text length in UTF-16
                // units.
                if (lines[i] != null) {
                    lines[i] = lines[i].substring(0, Math.min(255, lines[i].length()));
                }
            }
        }

        /**
         * Returns the backing line array used by flattening and NBT saving.
         * Callers receive shared mutable storage; direct changes bypass
         * truncation and the viewer broadcast performed by {@code setText}.
         */
        @Override
        public String[] getText() {
            return text;
        }

        /**
         * Sanitizes the supplied array in place, retains it, and updates sign
         * viewers. The array is not copied and its line count is not limited;
         * later caller mutations therefore affect the retained face directly.
         */
        @Override
        public void setText(String[] text) {
            sanitizeText(text);
            this.text = text;
            sendBlockEntityToViewers();
        }

        /**
         * Returns whether this retained face currently marks text as glowing.
         */
        @Override
        public boolean isGlowing() {
            return glowing;
        }

        /**
         * Stores this face's glowing flag and sends updated sign state to
         * viewers.
         */
        @Override
        public void setGlowing(boolean glowing) {
            this.glowing = glowing;
            sendBlockEntityToViewers();
        }

        /**
         * Joins the retained lines with newline separators for comparisons and
         * saving. No additional trailing separator is appended, and
         * {@code String.join} renders {@code null} entries as its textual
         * {@code "null"} value.
         */
        @Override
        public String flattenText() {
            return String.join("\n", text);
        }

        /**
         * Builds this face's text and glowing fields with fixed display
         * metadata. Color is {@code -16777216}, hidden outline is
         * {@code false}, formatting retention is {@code true}, and text owner
         * is {@code ""}; those fields have no separate mutable storage in this
         * implementation.
         */
        @Override
        public NbtMap saveNBT() {
            // Only text and glowing state are retained here, so the remaining
            // display and owner entries use fixed placeholders rather than
            // round-tripped values.
            return NbtMap.builder()
                    .putString(TAG_TEXT, flattenText())
                    .putBoolean(TAG_IGNORE_LIGHTING, glowing)
                    .putInt(TAG_SIGN_TEXT_COLOR, -16777216)
                    .putBoolean(TAG_HIDE_GLOW_OUTLINE, false)
                    .putBoolean(TAG_PERSIST_FORMATTING, true)
                    .putString(TAG_TEXT_OWNER, "")
                    .build();
        }
    }

    /**
     * Opens the front-face editor for an actual player placing the sign. Events
     * without placement information and non-actual players do nothing; this
     * hook does not change either face's retained text.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        if (event.getPlacementInfo() == null) {
            return;
        }

        var player = event.getPlacementInfo().player();
        if (player.isActualPlayer()) {
            player.getController().viewSignEditor(getPosition(), true);
        }
    }
}
