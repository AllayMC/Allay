package org.allaymc.server.entity.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.allaymc.api.entity.component.EntityPlayerClientComponent;
import org.allaymc.api.entity.component.EntityPlayerFormViewerComponent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundCloseFormPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daoge_cmd
 */
public class EntityPlayerFormViewerComponentImpl implements EntityPlayerFormViewerComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_form_viewer_component");

    @Dependency
    protected EntityPlayerClientComponent clientComponent;

    protected AtomicInteger idCounter;
    protected Cache<Integer, Form> forms;
    @Getter
    protected Pair<Integer, CustomForm> serverSettingForm;

    public EntityPlayerFormViewerComponentImpl() {
        this.idCounter = new AtomicInteger(0);
        this.forms = Caffeine.newBuilder()
                // A player can only have 100 opened forms at a time
                .maximumSize(100)
                // Opened form will be expired after 10 minutes
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public int setServerSettingForm(CustomForm form) {
        var id = assignId();
        this.serverSettingForm = new Pair<>(id, form);
        return id;
    }

    @Override
    public Pair<Integer, CustomForm> removeServerSettingForm() {
        var tmp = this.serverSettingForm;
        this.serverSettingForm = null;
        return tmp;
    }

    @Override
    public int viewForm(Form form) {
        var id = assignId();
        this.forms.put(id, form);

        var packet = new ModalFormRequestPacket();
        packet.setFormId(id);
        packet.setFormData(form.toJson());
        this.clientComponent.sendPacket(packet);

        return id;
    }

    @Override
    public @UnmodifiableView Map<Integer, Form> getForms() {
        return Collections.unmodifiableMap(this.forms.asMap());
    }

    @Override
    public Form removeForm(int formId) {
        var form = this.forms.getIfPresent(formId);
        if (form != null) {
            this.forms.invalidate(formId);
        }

        return form;
    }

    @Override
    public void removeAllForms() {
        this.clientComponent.sendPacket(new ClientboundCloseFormPacket());
        this.forms.invalidateAll();
    }

    protected int assignId() {
        return this.idCounter.getAndIncrement();
    }
}
