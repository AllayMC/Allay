package org.allaymc.api.entity.component.misc;

import org.allaymc.api.component.annotation.ComponentEventListener;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.event.EntityLoadNBTEvent;
import org.allaymc.api.entity.event.EntitySaveNBTEvent;
import org.allaymc.api.identifier.Identifier;

import java.util.function.Consumer;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class EntityNBTEventListenerComponent implements EntityComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_item_nbt_serialization_listener_component");

    Consumer<EntityLoadNBTEvent> loadNBTEventConsumer;
    Consumer<EntitySaveNBTEvent> saveNBTEventConsumer;

    public EntityNBTEventListenerComponent(
            Consumer<EntityLoadNBTEvent> loadNBTEventConsumer,
            Consumer<EntitySaveNBTEvent> saveNBTEventConsumer
    ) {
        this.loadNBTEventConsumer = loadNBTEventConsumer;
        this.saveNBTEventConsumer = saveNBTEventConsumer;
    }

    public static EntityNBTEventListenerComponent ofLoadAndSave(
            Consumer<EntityLoadNBTEvent> loadNBTEventConsumer,
            Consumer<EntitySaveNBTEvent> saveNBTEventConsumer
    ) {
        return new EntityNBTEventListenerComponent(loadNBTEventConsumer, saveNBTEventConsumer);
    }

    public static EntityNBTEventListenerComponent ofLoad(
            Consumer<EntityLoadNBTEvent> loadNBTEventConsumer
    ) {
        return new EntityNBTEventListenerComponent(loadNBTEventConsumer, e -> {
        });
    }

    public static EntityNBTEventListenerComponent ofSave(
            Consumer<EntitySaveNBTEvent> saveNBTEventConsumer
    ) {
        return new EntityNBTEventListenerComponent(e -> {
        }, saveNBTEventConsumer);
    }


    @ComponentEventListener
    private void onLoadNBT(EntityLoadNBTEvent event) {
        loadNBTEventConsumer.accept(event);
    }

    @ComponentEventListener
    private void onSaveNBT(EntitySaveNBTEvent event) {
        saveNBTEventConsumer.accept(event);
    }
}
