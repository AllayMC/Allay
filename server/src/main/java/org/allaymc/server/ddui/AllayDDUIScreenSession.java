package org.allaymc.server.ddui;

import org.allaymc.api.ddui.*;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.element.*;
import org.allaymc.api.ddui.type.CustomFormScreen;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.ddui.type.MessageBoxScreen;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

import java.util.*;
import java.util.function.Consumer;

/**
 * Server-side DDUI session implementation.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class AllayDDUIScreenSession implements DDUIScreenSession {

    private static final String DATA_STORE_NAME = "minecraft";
    private static final String CUSTOM_FORM_SCREEN_ID = "minecraft:custom_form";
    private static final String CUSTOM_FORM_PROPERTY = "custom_form_data";
    private static final String MESSAGE_BOX_SCREEN_ID = "minecraft:message_box";
    private static final String MESSAGE_BOX_PROPERTY = "message_box_data";

    private final Player viewer;
    private final int formId;
    private final DDUIScreen screen;
    private final String screenId;
    private final String propertyName;
    private final Map<String, BoundProperty> inboundBindings = new HashMap<>();
    private final Map<ValueElement<?>, BoundProperty> valueBindings = new IdentityHashMap<>();
    private final Map<DDUIElement, ServerBinding> visibleBindings = new IdentityHashMap<>();
    private final Map<DDUIElement, ServerBinding> disabledBindings = new IdentityHashMap<>();
    private final Map<DDUIElement, String> elementPaths = new IdentityHashMap<>();
    private final List<AutoCloseable> subscriptions = new ArrayList<>();
    private final Map<String, Object> initialState = new LinkedHashMap<>();

    private boolean closed;
    private MessageBoxResult messageBoxResult;

    public AllayDDUIScreenSession(Player viewer, int formId, DDUIScreen screen) {
        this.viewer = viewer;
        this.formId = formId;
        this.screen = screen;
        this.screenId = resolveScreenId(screen);
        this.propertyName = resolvePropertyName(screen);
        compile();
    }

    @Override
    public Player viewer() {
        return viewer;
    }

    @Override
    public int formId() {
        return formId;
    }

    @Override
    public DDUIScreen screen() {
        return screen;
    }

    @Override
    public boolean closed() {
        return closed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(ValueElement<T> element) {
        ensureOpen();
        var binding = requireValueBinding(element);
        return (T) binding.currentValue();
    }

    @Override
    public <T> void set(ValueElement<T> element, T value) {
        ensureOpen();
        requireValueBinding(element).applyServer(value);
    }

    @Override
    public void setVisible(DDUIElement element, boolean visible) {
        ensureOpen();
        var binding = visibleBindings.get(element);
        if (binding != null) {
            binding.applyServer(visible);
        }
    }

    @Override
    public void setDisabled(DDUIElement element, boolean disabled) {
        ensureOpen();
        var binding = disabledBindings.get(element);
        if (binding != null) {
            binding.applyServer(disabled);
        }
    }

    @Override
    public void close() {
        closeInternal(Integer.valueOf(formId), DDUIScreenCloseReason.PROGRAMMATIC);
    }

    public void closeAll() {
        closeInternal(null, DDUIScreenCloseReason.PROGRAMMATIC_ALL);
    }

    public void discard() {
        if (closed) {
            return;
        }
        if (viewer.getActiveScreen() == this) {
            viewer.removeActiveScreen();
        }
        closed = true;
        cleanup();
    }

    public boolean matchesFormId(Integer formId) {
        return formId != null && formId == this.formId;
    }

    public boolean handleDataStoreUpdate(DataStoreUpdate update) {
        if (closed || update == null) {
            return false;
        }
        if (!DATA_STORE_NAME.equals(update.getDataStoreName())) {
            return false;
        }
        if (!propertyName.equals(update.getProperty())) {
            return false;
        }
        var binding = inboundBindings.get(update.getPath());
        if (binding == null) {
            return false;
        }

        binding.applyClient(update.getData());
        return true;
    }

    public void handleClientClosed(ServerboundDataDrivenScreenClosedPacket.CloseReason reason) {
        if (closed) {
            return;
        }

        closed = true;
        cleanup();

        if (messageBoxResult == null) {
            screen.handleClose(this, mapCloseReason(reason));
        }
    }

    public void show() {
        var dataPacket = new ClientboundDataStorePacket();
        var change = new DataStoreChange();
        change.setDataStoreName(DATA_STORE_NAME);
        change.setProperty(propertyName);
        change.setNewValue(initialState);
        change.setUpdateCount(1);
        dataPacket.getUpdates().add(change);
        viewer.sendPacket(dataPacket);

        var showPacket = new ClientboundDataDrivenUIShowScreenPacket();
        showPacket.setScreenId(screenId);
        showPacket.setFormId(formId);
        viewer.sendPacket(showPacket);
    }

    private void compile() {
        switch (screen) {
            case CustomFormScreen customForm -> compileCustomForm(customForm);
            case MessageBoxScreen messageBox -> compileMessageBox(messageBox);
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        }
    }

    private void compileCustomForm(CustomFormScreen customForm) {
        registerProperty(initialState, "title", "title", PropertyType.STRING, binding(customForm.getTitle()));

        var layout = new LinkedHashMap<String, Object>();
        initialState.put("layout", layout);

        var layoutIndex = 0;
        var hasCloseButton = false;
        for (var element : customForm.getElements()) {
            if (element instanceof CloseButton closeButton) {
                if (hasCloseButton) {
                    throw new IllegalArgumentException("Custom form can only contain one close button");
                }
                hasCloseButton = true;
                elementPaths.put(closeButton, "closeButton");
                initialState.put("closeButton", compileCloseButton(closeButton));
                continue;
            }

            elementPaths.put(element, "layout[" + layoutIndex + "]");
            layout.put(String.valueOf(layoutIndex), compileElement(element));
            layoutIndex++;
        }
        layout.put("length", (long) layoutIndex);
    }

    private Map<String, Object> compileElement(DDUIElement element) {
        return switch (element) {
            case TextField textField -> compileTextField(textField);
            case Toggle toggle -> compileToggle(toggle);
            case Slider slider -> compileSlider(slider);
            case Dropdown dropdown -> compileDropdown(dropdown);
            case Button button -> compileButton(button);
            case CloseButton closeButton -> compileCloseButton(closeButton);
            case Label label -> compileLabel(label);
            case Header header -> compileHeader(header);
            case Divider divider -> compileDivider(divider);
            case Spacer spacer -> compileSpacer(spacer);
            default -> throw new IllegalArgumentException("Unsupported DDUI element type: " + element.getClass().getName());
        };
    }

    private Map<String, Object> compileTextField(TextField element) {
        var path = elementPath(element);
        var data = compileValueElementFrame(
                element,
                "textfield_visible",
                binding(element.getLabel()),
                binding(element.getDescription()),
                binding(element.getDisabled())
        );
        registerValue(element, data, "text", path + ".text", PropertyType.STRING, binding(element.getValue()), value ->
                element.getOnChange().accept(this, (String) value));
        return data;
    }

    private Map<String, Object> compileToggle(Toggle element) {
        var path = elementPath(element);
        var data = compileValueElementFrame(
                element,
                "toggle_visible",
                binding(element.getLabel()),
                binding(element.getDescription()),
                binding(element.getDisabled())
        );
        registerValue(element, data, "toggled", path + ".toggled", PropertyType.BOOLEAN, binding(element.getValue()), value ->
                element.getOnChange().accept(this, (Boolean) value));
        return data;
    }

    private Map<String, Object> compileSlider(Slider element) {
        var path = elementPath(element);
        var data = compileValueElementFrame(
                element,
                "slider_visible",
                binding(element.getLabel()),
                binding(element.getDescription()),
                binding(element.getDisabled())
        );
        registerProperty(data, "minValue", path + ".minValue", PropertyType.LONG, binding(element.getMin()));
        registerProperty(data, "maxValue", path + ".maxValue", PropertyType.LONG, binding(element.getMax()));
        data.put("step", 1L);
        registerValue(element, data, "value", path + ".value", PropertyType.LONG, binding(element.getValue()), value ->
                element.getOnChange().accept(this, (Long) value));
        return data;
    }

    private Map<String, Object> compileDropdown(Dropdown element) {
        var path = elementPath(element);
        var data = compileValueElementFrame(
                element,
                "dropdown_visible",
                binding(element.getLabel()),
                binding(element.getDescription()),
                binding(element.getDisabled())
        );
        registerValue(element, data, "value", path + ".value", PropertyType.LONG, binding(element.getValue()), value ->
                element.getOnChange().accept(this, (Long) value));
        data.put("items", compileDropdownItems(element));
        return data;
    }

    private Map<String, Object> compileButton(Button element) {
        var path = elementPath(element);
        var data = compileButtonFrame(
                element,
                binding(element.getLabel()),
                binding(element.getDisabled())
        );
        registerProperty(data, "tooltip", path + ".tooltip", PropertyType.STRING, binding(element.getTooltip()));
        registerInboundLong(data, "onClick", path + ".onClick", value -> element.getOnClick().accept(this));
        return data;
    }

    private Map<String, Object> compileCloseButton(CloseButton element) {
        var path = elementPath(element);
        var data = compileButtonFrame(
                element,
                binding(element.getLabel()),
                null
        );
        registerInboundLong(data, "onClick", path + ".onClick", value -> {
            element.getOnClick().accept(this);
            closeSilently(formId);
        });
        return data;
    }

    private Map<String, Object> compileLabel(Label element) {
        return compileTextElementFrame(element, "label_visible", binding(element.getText()));
    }

    private Map<String, Object> compileHeader(Header element) {
        return compileTextElementFrame(element, "header_visible", binding(element.getText()));
    }

    private Map<String, Object> compileDivider(Divider element) {
        var data = new LinkedHashMap<String, Object>();
        registerVisible(data, element, "divider_visible");
        return data;
    }

    private Map<String, Object> compileSpacer(Spacer element) {
        var data = new LinkedHashMap<String, Object>();
        registerVisible(data, element, "spacer_visible");
        return data;
    }

    private void compileMessageBox(MessageBoxScreen messageBox) {
        registerProperty(initialState, "title", "title", PropertyType.STRING, binding(messageBox.getTitle()));
        registerProperty(initialState, "body", "body", PropertyType.STRING, binding(messageBox.getBody()));

        var button1 = new LinkedHashMap<String, Object>();
        initialState.put("button1", button1);
        registerProperty(button1, "label", "button1.label", PropertyType.STRING, binding(messageBox.getButton1()));
        registerInboundLong(button1, "onClick", "button1.onClick", value -> handleMessageBoxResponse(messageBox, MessageBoxResult.BUTTON1));

        var button2 = new LinkedHashMap<String, Object>();
        initialState.put("button2", button2);
        registerProperty(button2, "label", "button2.label", PropertyType.STRING, binding(messageBox.getButton2()));
        registerInboundLong(button2, "onClick", "button2.onClick", value -> handleMessageBoxResponse(messageBox, MessageBoxResult.BUTTON2));
    }

    private void registerVisible(Map<String, Object> data, DDUIElement element, String specificKey) {
        var initial = element.getVisible().value();
        data.put("visible", initial);
        data.put(specificKey, initial);

        var binding = bindServer(PropertyType.BOOLEAN, binding(element.getVisible()),
                elementPath(element) + ".visible", elementPath(element) + "." + specificKey);
        visibleBindings.put(element, binding);
    }

    private void registerDisabled(Map<String, Object> data, DDUIElement element, PropertyBinding<Boolean> disabledBinding) {
        data.put("disabled", disabledBinding.value());

        var binding = bindServer(PropertyType.BOOLEAN, disabledBinding, elementPath(element) + ".disabled");
        disabledBindings.put(element, binding);
    }

    private <T> void registerProperty(Map<String, Object> data, String key, String path, PropertyType type, PropertyBinding<T> property) {
        var binding = bindServer(type, property, path);
        data.put(key, binding.currentValue());
    }

    private void registerInboundLong(Map<String, Object> data, String key, String path, Consumer<Object> onClientChange) {
        var binding = bindInboundAction(path, onClientChange);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
    }

    private <T> void registerValue(ValueElement<T> element, Map<String, Object> data, String key, String path, PropertyType type,
                                   PropertyBinding<T> property, Consumer<Object> onClientChange) {
        var binding = bindProperty(type, property, onClientChange, path);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
        valueBindings.put(element, binding);
    }

    @SuppressWarnings("unchecked")
    private void subscribe(ServerBinding binding, Observable<?> observable) {
        if (observable == null) {
            return;
        }
        subscriptions.add(((Observable<Object>) observable).subscribe(binding::applyServer));
    }

    private String elementPath(DDUIElement element) {
        var path = elementPaths.get(element);
        if (path == null) {
            throw new IllegalArgumentException("Element does not belong to this DDUI session");
        }
        return path;
    }

    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("DDUI session is already closed");
        }
    }

    private BoundProperty requireValueBinding(ValueElement<?> element) {
        var binding = valueBindings.get(element);
        if (binding == null) {
            throw new IllegalArgumentException("Element does not belong to this DDUI session");
        }
        return binding;
    }

    private void closeSilently(Integer closeFormId) {
        closeInternal(closeFormId, null);
    }

    private void closeInternal(Integer closeFormId, DDUIScreenCloseReason closeReason) {
        if (closed) {
            return;
        }
        if (viewer.getActiveScreen() == this) {
            viewer.removeActiveScreen();
        }
        closed = true;
        sendClosePacket(closeFormId);
        cleanup();
        if (closeReason != null) {
            screen.handleClose(this, closeReason);
        }
    }

    private void handleMessageBoxResponse(MessageBoxScreen messageBox, MessageBoxResult result) {
        if (messageBoxResult != null) {
            return;
        }

        messageBoxResult = result;
        switch (result) {
            case BUTTON1 -> messageBox.handleButton1(this);
            case BUTTON2 -> messageBox.handleButton2(this);
        }
        messageBox.handleResponse(this, result);
        closeSilently(formId);
    }

    private void sendClosePacket(Integer closeFormId) {
        var packet = new ClientboundDataDrivenUICloseScreenPacket();
        packet.setFormId(closeFormId);
        viewer.sendPacket(packet);
    }

    private void cleanup() {
        for (var subscription : subscriptions) {
            try {
                subscription.close();
            } catch (Exception ignored) {
            }
        }
        subscriptions.clear();
    }

    private void sendUpdate(List<String> paths, Object value) {
        if (closed || paths.isEmpty()) {
            return;
        }

        var packet = new ClientboundDataStorePacket();
        for (var path : paths) {
            var update = new DataStoreUpdate();
            update.setDataStoreName(DATA_STORE_NAME);
            update.setProperty(propertyName);
            update.setPath(path);
            update.setData(value);
            update.setUpdateCount(1);
            update.setPathUpdateCount(1);
            packet.getUpdates().add(update);
        }
        viewer.sendPacket(packet);
    }

    private Map<String, Object> compileValueElementFrame(DDUIElement element, String specificVisibleKey, PropertyBinding<String> label,
                                                         PropertyBinding<String> description, PropertyBinding<Boolean> disabled) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerProperty(data, "label", path + ".label", PropertyType.STRING, label);
        registerProperty(data, "description", path + ".description", PropertyType.STRING, description);
        registerVisible(data, element, specificVisibleKey);
        registerDisabled(data, element, disabled);
        return data;
    }

    private Map<String, Object> compileButtonFrame(DDUIElement element, PropertyBinding<String> label, PropertyBinding<Boolean> disabled) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerProperty(data, "label", path + ".label", PropertyType.STRING, label);
        registerVisible(data, element, "button_visible");
        if (disabled != null) {
            registerDisabled(data, element, disabled);
        }
        return data;
    }

    private Map<String, Object> compileTextElementFrame(DDUIElement element, String specificVisibleKey, PropertyBinding<String> text) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerProperty(data, "text", path + ".text", PropertyType.STRING, text);
        registerVisible(data, element, specificVisibleKey);
        return data;
    }

    private Map<String, Object> compileDropdownItems(Dropdown element) {
        var items = new LinkedHashMap<String, Object>();
        for (int i = 0; i < element.getItems().size(); i++) {
            var item = element.getItems().get(i);
            var itemData = new LinkedHashMap<String, Object>();
            itemData.put("label", item.getLabel());
            itemData.put("description", item.getDescription());
            itemData.put("value", item.getValue());
            items.put(String.valueOf(i), itemData);
        }
        items.put("length", (long) element.getItems().size());
        return items;
    }

    private static <T> PropertyBinding<T> binding(Property<T> value) {
        return new PropertyBinding<>(value.value(), value.observable());
    }

    private <T> ServerBinding bindServer(PropertyType type, PropertyBinding<T> property, String... paths) {
        var binding = new ServerBinding(type, List.of(paths), property.value());
        subscribe(binding, property.observable());
        return binding;
    }

    private BoundProperty bindInboundAction(String path, Consumer<Object> onClientChange) {
        return new BoundProperty(PropertyType.LONG, List.of(path), 0L, null, false, onClientChange);
    }

    private <T> BoundProperty bindProperty(PropertyType type, PropertyBinding<T> property, Consumer<Object> onClientChange, String... paths) {
        var observable = property.observable();
        var binding = new BoundProperty(type, List.of(paths), property.value(), observable, observable != null && observable.clientWritable(), onClientChange);
        subscribe(binding, observable);
        return binding;
    }

    private static String resolveScreenId(DDUIScreen screen) {
        return switch (screen) {
            case CustomFormScreen ignored -> CUSTOM_FORM_SCREEN_ID;
            case MessageBoxScreen ignored -> MESSAGE_BOX_SCREEN_ID;
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        };
    }

    private static String resolvePropertyName(DDUIScreen screen) {
        return switch (screen) {
            case CustomFormScreen ignored -> CUSTOM_FORM_PROPERTY;
            case MessageBoxScreen ignored -> MESSAGE_BOX_PROPERTY;
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        };
    }

    private static DDUIScreenCloseReason mapCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason reason) {
        return switch (reason) {
            case PROGRAMMATIC_CLOSE -> DDUIScreenCloseReason.PROGRAMMATIC;
            case PROGRAMMATIC_CLOSE_ALL -> DDUIScreenCloseReason.PROGRAMMATIC_ALL;
            case CLIENT_CANCELED -> DDUIScreenCloseReason.CLOSED;
            case USER_BUSY -> DDUIScreenCloseReason.BUSY;
            case INVALID_FORM -> DDUIScreenCloseReason.INVALID;
        };
    }

    private enum PropertyType {
        STRING,
        BOOLEAN,
        LONG
    }

    private class ServerBinding {
        private final PropertyType type;
        private final List<String> paths;
        private Object value;

        private ServerBinding(PropertyType type, List<String> paths, Object value) {
            this.type = type;
            this.paths = List.copyOf(paths);
            this.value = normalize(type, value);
        }

        protected final Object currentValue() {
            return value;
        }

        protected final PropertyType type() {
            return type;
        }

        protected final void updateValue(Object value) {
            this.value = normalize(type, value);
        }

        protected void applyServer(Object value) {
            updateValue(value);
            sendUpdate(paths, this.value);
        }
    }

    private final class BoundProperty extends ServerBinding {
        private final Observable<?> observable;
        private final boolean clientWritable;
        private final Consumer<Object> onClientChange;
        private boolean suppressOutbound;

        private BoundProperty(PropertyType type, List<String> paths, Object value, Observable<?> observable, boolean clientWritable,
                              Consumer<Object> onClientChange) {
            super(type, paths, value);
            this.observable = observable;
            this.clientWritable = clientWritable;
            this.onClientChange = onClientChange;
        }

        @SuppressWarnings("unchecked")
        private void applyClient(Object value) {
            var normalized = normalize(type(), value);
            if (observable != null && clientWritable) {
                suppressOutbound = true;
                try {
                    ((Observable<Object>) observable).set(normalized);
                } finally {
                    suppressOutbound = false;
                }
            } else {
                updateValue(normalized);
            }
            onClientChange.accept(normalized);
        }

        @Override
        protected void applyServer(Object value) {
            if (suppressOutbound) {
                updateValue(value);
                return;
            }
            super.applyServer(value);
        }
    }

    private static Object normalize(PropertyType type, Object value) {
        return switch (type) {
            case STRING -> value == null ? "" : String.valueOf(value);
            case BOOLEAN -> value instanceof Boolean bool ? bool : false;
            case LONG -> value instanceof Number number ? number.longValue() : 0L;
        };
    }

    private record PropertyBinding<T>(T value, Observable<T> observable) {
    }
}
