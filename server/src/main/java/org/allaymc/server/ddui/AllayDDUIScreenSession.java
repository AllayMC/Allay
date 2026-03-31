package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.MessageBoxResult;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.element.*;
import org.allaymc.api.ddui.session.DDUIScreenSession;
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
 * @author OpenAI
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
        registerString(initialState, "title", "title", customForm.getTitle(), customForm.getTitleObservable());

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
        if (element instanceof TextField textField) {
            return compileTextField(textField);
        }
        if (element instanceof Toggle toggle) {
            return compileToggle(toggle);
        }
        if (element instanceof Slider slider) {
            return compileSlider(slider);
        }
        if (element instanceof Dropdown dropdown) {
            return compileDropdown(dropdown);
        }
        if (element instanceof Button button) {
            return compileButton(button);
        }
        if (element instanceof CloseButton closeButton) {
            return compileCloseButton(closeButton);
        }
        if (element instanceof Label label) {
            return compileLabel(label);
        }
        if (element instanceof Header header) {
            return compileHeader(header);
        }
        if (element instanceof Divider divider) {
            return compileDivider(divider);
        }
        if (element instanceof Spacer spacer) {
            return compileSpacer(spacer);
        }

        throw new IllegalArgumentException("Unsupported DDUI element type: " + element.getClass().getName());
    }

    private Map<String, Object> compileTextField(TextField element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerString(data, "description", path + ".description", element.getDescription(), element.getDescriptionObservable());
        registerVisible(data, element, "textfield_visible");
        registerDisabled(data, element);
        registerValue(element, data, "text", path + ".text", PropertyType.STRING, element.getText(), element.getTextObservable(), value ->
                element.getOnChange().accept(this, (String) value));
        return data;
    }

    private Map<String, Object> compileToggle(Toggle element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerString(data, "description", path + ".description", element.getDescription(), element.getDescriptionObservable());
        registerVisible(data, element, "toggle_visible");
        registerDisabled(data, element);
        registerValue(element, data, "toggled", path + ".toggled", PropertyType.BOOLEAN, element.isToggled(), element.getToggledObservable(), value ->
                element.getOnChange().accept(this, (Boolean) value));
        return data;
    }

    private Map<String, Object> compileSlider(Slider element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerString(data, "description", path + ".description", element.getDescription(), element.getDescriptionObservable());
        registerVisible(data, element, "slider_visible");
        registerDisabled(data, element);
        registerLong(data, "minValue", path + ".minValue", element.getMin(), element.getMinObservable());
        registerLong(data, "maxValue", path + ".maxValue", element.getMax(), element.getMaxObservable());
        data.put("step", 1L);
        registerValue(element, data, "value", path + ".value", PropertyType.LONG, element.getValue(), element.getValueObservable(), value ->
                element.getOnChange().accept(this, (Long) value));
        return data;
    }

    private Map<String, Object> compileDropdown(Dropdown element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerString(data, "description", path + ".description", element.getDescription(), element.getDescriptionObservable());
        registerVisible(data, element, "dropdown_visible");
        registerDisabled(data, element);
        registerValue(element, data, "value", path + ".value", PropertyType.LONG, element.getValue(), element.getValueObservable(), value ->
                element.getOnChange().accept(this, (Long) value));

        var items = new LinkedHashMap<String, Object>();
        data.put("items", items);
        for (int i = 0; i < element.getItems().size(); i++) {
            var item = element.getItems().get(i);
            var itemData = new LinkedHashMap<String, Object>();
            itemData.put("label", item.getLabel());
            itemData.put("description", item.getDescription());
            itemData.put("value", item.getValue());
            items.put(String.valueOf(i), itemData);
        }
        items.put("length", (long) element.getItems().size());
        return data;
    }

    private Map<String, Object> compileButton(Button element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerString(data, "tooltip", path + ".tooltip", element.getTooltip(), element.getTooltipObservable());
        registerVisible(data, element, "button_visible");
        registerDisabled(data, element);
        registerInboundLong(data, "onClick", path + ".onClick", value -> element.getOnClick().accept(this));
        return data;
    }

    private Map<String, Object> compileCloseButton(CloseButton element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "label", path + ".label", element.getLabel(), element.getLabelObservable());
        registerVisible(data, element, "button_visible");
        registerInboundLong(data, "onClick", path + ".onClick", value -> {
            element.getOnClick().accept(this);
            closeSilently(Integer.valueOf(formId));
        });
        return data;
    }

    private Map<String, Object> compileLabel(Label element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "text", path + ".text", element.getText(), element.getTextObservable());
        registerVisible(data, element, "label_visible");
        return data;
    }

    private Map<String, Object> compileHeader(Header element) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerString(data, "text", path + ".text", element.getText(), element.getTextObservable());
        registerVisible(data, element, "header_visible");
        return data;
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
        registerString(initialState, "title", "title", messageBox.getTitle(), messageBox.getTitleObservable());
        registerString(initialState, "body", "body", messageBox.getBody(), messageBox.getBodyObservable());

        var button1 = new LinkedHashMap<String, Object>();
        initialState.put("button1", button1);
        registerString(button1, "label", "button1.label", messageBox.getButton1(), messageBox.getButton1Observable());
        registerInboundLong(button1, "onClick", "button1.onClick", value -> handleMessageBoxResponse(messageBox, MessageBoxResult.BUTTON1));

        var button2 = new LinkedHashMap<String, Object>();
        initialState.put("button2", button2);
        registerString(button2, "label", "button2.label", messageBox.getButton2(), messageBox.getButton2Observable());
        registerInboundLong(button2, "onClick", "button2.onClick", value -> handleMessageBoxResponse(messageBox, MessageBoxResult.BUTTON2));
    }

    private void registerVisible(Map<String, Object> data, DDUIElement element, String specificKey) {
        var initial = element.isVisible();
        data.put("visible", initial);
        data.put(specificKey, initial);

        var binding = new ServerBinding(PropertyType.BOOLEAN, List.of(elementPath(element) + ".visible", elementPath(element) + "." + specificKey), initial);
        visibleBindings.put(element, binding);
        subscribe(binding, element.getVisibleObservable());
    }

    private void registerDisabled(Map<String, Object> data, DDUIElement element) {
        var initial = switch (element) {
            case TextField textField -> textField.isDisabled();
            case Toggle toggle -> toggle.isDisabled();
            case Slider slider -> slider.isDisabled();
            case Dropdown dropdown -> dropdown.isDisabled();
            case Button button -> button.isDisabled();
            default -> false;
        };
        data.put("disabled", initial);

        var binding = new ServerBinding(PropertyType.BOOLEAN, List.of(elementPath(element) + ".disabled"), initial);
        disabledBindings.put(element, binding);
        switch (element) {
            case TextField textField -> subscribe(binding, textField.getDisabledObservable());
            case Toggle toggle -> subscribe(binding, toggle.getDisabledObservable());
            case Slider slider -> subscribe(binding, slider.getDisabledObservable());
            case Dropdown dropdown -> subscribe(binding, dropdown.getDisabledObservable());
            case Button button -> subscribe(binding, button.getDisabledObservable());
            default -> {
            }
        }
    }

    private void registerString(Map<String, Object> data, String key, String path, String value, Observable<String> observable) {
        var binding = new ServerBinding(PropertyType.STRING, List.of(path), value);
        data.put(key, binding.currentValue());
        subscribe(binding, observable);
    }

    private void registerLong(Map<String, Object> data, String key, String path, long value, Observable<Long> observable) {
        var binding = new ServerBinding(PropertyType.LONG, List.of(path), value);
        data.put(key, binding.currentValue());
        subscribe(binding, observable);
    }

    private void registerInboundLong(Map<String, Object> data, String key, String path, Consumer<Object> onClientChange) {
        var binding = new BoundProperty(PropertyType.LONG, List.of(path), 0L, null, false, onClientChange);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
    }

    @SuppressWarnings("unchecked")
    private <T> void registerValue(ValueElement<T> element, Map<String, Object> data, String key, String path, PropertyType type, T value,
                                   Observable<T> observable, Consumer<Object> onClientChange) {
        var binding = new BoundProperty(type, List.of(path), value, observable, observable != null && observable.clientWritable(), onClientChange);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
        valueBindings.put(element, binding);
        subscribe(binding, observable);
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
        closeSilently(Integer.valueOf(formId));
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
}
