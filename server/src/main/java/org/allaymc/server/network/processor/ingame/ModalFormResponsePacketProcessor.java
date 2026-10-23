package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

/**
 * {@code ModalFormResponsePacketProcessor} dispatches a client modal-form
 * response to its registered form or matching
 * server-settings form. Normal forms are removed from the player's form
 * registry before callbacks run. If no normal form matches, the handler assumes
 * a server-settings form exists and compares its ID; a missing settings pair is
 * not handled as an unknown-form result.
 *
 * <p>
 * Nonnull response text is trimmed before delivery, while {@code null} text
 * requires an explicit cancellation reason. Successful handling of a settings
 * form then synchronizes defaults from its stored response. Closing reuses any
 * previous response; without one, {@code CustomForm} logs and leaves defaults
 * unchanged.
 * Callback or conversion failures propagate after any normal-form removal,
 * without reinserting the form or completing the later default synchronization.
 * This ordering makes a response consume its ordinary form registration even if
 * application code fails.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ModalFormResponsePacketProcessor extends PacketProcessor<ModalFormResponsePacket> {

    /**
     * Removes and handles the submitted form, falling back to the
     * server-settings form by ID. That fallback requires a nonnull settings
     * pair; unknown IDs are logged, while missing response text requires a
     * present cancellation reason.
     *
     * <p>
     * Removal precedes the response or close callback, and callback failures
     * propagate without restoring the ordinary form entry. Response text is
     * trimmed before dispatch. For a settings form, default-value
     * synchronization runs only after the callback completes, using the
     * {@code CustomForm} implementation expected by the settings pair. A close
     * callback leaves the stored response unchanged, so synchronization uses an
     * earlier response or logs and returns when none exists.
     */
    @Override
    public void handleSync(Player player, ModalFormResponsePacket packet, long receiveTime) {
        var id = packet.getFormId();
        // Remove an ordinary form before callbacks so this response consumes
        // its registration even if response parsing or application code fails.
        var form = player.removeForm(id);
        var isServerSettingsForm = false;
        if (form == null) {
            // Settings forms are stored outside the ordinary form registry, so
            // an unmatched ID falls back to that separate pair before being
            // rejected.
            var serverSettingForm = player.getServerSettingForm();
            if (id != serverSettingForm.left()) {
                log.warn("Received response for unknown form from player {}: {}", player.getOriginName(), packet.getFormId());
                return;
            }

            form = serverSettingForm.right();
            isServerSettingsForm = true;
        }

        var formData = packet.getFormData();
        if (formData != null) {
            form.handleResponse(formData.trim());
        } else {
            form.handleClose(switch (packet.getCancelReason().orElseThrow()) {
                case USER_CLOSED -> FormCancelReason.CLOSED;
                case USER_BUSY -> FormCancelReason.BUSY;
            });
        }
        if (isServerSettingsForm) {
            // Persist the latest settings response into form defaults only
            // after handling completes, preserving those values when the
            // settings interface is opened again.
            ((CustomForm) form).syncDefaultValueToResponse();
        }
    }

    /**
     * Returns the modal form response packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MODAL_FORM_RESPONSE;
    }
}
