package org.allaymc.server.ddui;

import org.allaymc.api.ddui.*;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.element.*;
import org.allaymc.api.ddui.type.CustomFormScreen;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.ddui.type.MessageBoxScreen;
import org.allaymc.api.player.Player;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@code AllayDDUIScreenSession} represents one viewer's compiled DDUI template, runtime values,
 * and observable subscriptions. Compilation maps original element identities to client data-store
 * paths, preserving the distinction between indexed layout entries and the dedicated close button.
 * The initial property tree is submitted before the screen-show request.
 *
 * <p>
 * Client-writable bindings write into their shared observable while suppressing this binding's
 * outbound echo; other subscribed sessions can still receive the update. Callbacks run
 * synchronously and can interrupt later response or close steps. Closure marks the session closed
 * before packet submission and subscription cleanup, so a submission exception can leave
 * subscriptions retained. Callers coordinate template mutation, observable updates, and session
 * operations because these maps and lifecycle fields have no shared lock.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class AllayDDUIScreenSession implements DDUIScreenSession {

    /**
     * Client data-store namespace name.
     */
    // DATA_STORE_NAME selects the client store; CUSTOM_FORM_SCREEN_ID and
    // CUSTOM_FORM_PROPERTY pair custom-form presentation with its state, while
    // MESSAGE_BOX_SCREEN_ID and MESSAGE_BOX_PROPERTY do the same for message boxes.
    private static final String DATA_STORE_NAME = "minecraft";
    /**
     * Custom-form client screen identifier.
     */
    private static final String CUSTOM_FORM_SCREEN_ID = "minecraft:custom_form";
    /**
     * Custom-form data-store property name.
     */
    private static final String CUSTOM_FORM_PROPERTY = "custom_form_data";
    /**
     * Message-box client screen identifier.
     */
    private static final String MESSAGE_BOX_SCREEN_ID = "minecraft:message_box";
    /**
     * Message-box data-store property name.
     */
    private static final String MESSAGE_BOX_PROPERTY = "message_box_data";

    /**
     * Player owning this runtime session.
     */
    private final AllayPlayer viewer;
    /**
     * Protocol identifier for this session.
     */
    private final int formId;
    /**
     * Original shared screen template reference.
     */
    private final DDUIScreen screen;
    /**
     * Client screen layout identifier.
     */
    private final String screenId;
    /**
     * Client data-store property name.
     */
    private final String propertyName;
    /**
     * Client-path to writable-binding lookup.
     */
    // inboundBindings resolves protocol paths, while valueBindings, visibleBindings,
    // disabledBindings, and elementPaths retain original handle identity so a
    // structurally similar element cannot address another element's runtime state.
    private final Map<String, BoundProperty> inboundBindings = new HashMap<>();
    /**
     * Original value-handle identity lookup.
     */
    private final Map<ValueElement<?>, BoundProperty> valueBindings = new IdentityHashMap<>();
    /**
     * Original element visibility-binding lookup.
     */
    private final Map<DDUIElement, ServerBinding> visibleBindings = new IdentityHashMap<>();
    /**
     * Original element disabled-binding lookup.
     */
    private final Map<DDUIElement, ServerBinding> disabledBindings = new IdentityHashMap<>();
    /**
     * Original element client-path lookup.
     */
    private final Map<DDUIElement, String> elementPaths = new IdentityHashMap<>();
    /**
     * {@code subscriptions} retains observable unsubscribe handles until cleanup. A constructor
     * failure after partial compilation does not close handles already added here.
     */
    private final List<AutoCloseable> subscriptions = new ArrayList<>();
    /**
     * {@code initialState} holds the compiled tree sent by {@code show}. Later binding updates do
     * not rewrite this tree, so callers coordinate changes before initial submission.
     */
    private final Map<String, Object> initialState = new LinkedHashMap<>();

    /**
     * {@code closed} rejects guarded session access and client updates after closure starts.
     * {@code sendUpdate} suppresses ordinary updates, but observable notifications can still change
     * binding values.
     */
    private boolean closed;
    /**
     * {@code messageBoxResult} records the first selected button before callbacks. A non-{@code
     * null} value suppresses duplicate response dispatch and subsequent normal close reporting.
     */
    private MessageBoxResult messageBoxResult;

    /**
     * Compiles a supported template for the supplied player and protocol identifier without showing
     * it. Compilation captures current observable values, builds element-identity indexes, and
     * installs subscriptions.
     *
     * <p>
     * Callers keep template and observable changes coordinated until initial submission because
     * {@code initialState} is not refreshed by later binding updates. Unsupported templates or
     * elements and duplicate close buttons can fail after partial compilation; already installed
     * subscriptions are not released when construction fails. The caller registers the successfully
     * created session with the viewer before showing it.
     */
    public AllayDDUIScreenSession(AllayPlayer viewer, int formId, DDUIScreen screen) {
        this.viewer = viewer;
        this.formId = formId;
        this.screen = screen;
        this.screenId = resolveScreenId(screen);
        this.propertyName = resolvePropertyName(screen);
        compile();
    }

    /**
     * Returns the player that owns this compiled DDUI session.
     */
    @Override
    public Player viewer() {
        return viewer;
    }

    /**
     * Returns the protocol form identifier assigned to this runtime session.
     */
    @Override
    public int formId() {
        return formId;
    }

    /**
     * Returns the original shared template used to compile this session.
     */
    @Override
    public DDUIScreen screen() {
        return screen;
    }

    /**
     * Returns whether a close or discard path has marked this session closed.
     */
    @Override
    public boolean closed() {
        return closed;
    }

    /**
     * Returns the normalized runtime value for the original element handle in this session. Closed
     * sessions raise {@code IllegalStateException}, and handles without a compiled value binding
     * raise {@code IllegalArgumentException}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(ValueElement<T> element) {
        ensureOpen();
        var binding = requireValueBinding(element);
        return (T) binding.currentValue();
    }

    /**
     * Normalizes and stores a value for the original handle, then sends the binding update. Closed
     * or foreign handles are rejected; this server-side write does not change the observable or
     * invoke the client-change callback.
     */
    @Override
    public <T> void set(ValueElement<T> element, T value) {
        ensureOpen();
        requireValueBinding(element).applyServer(value);
    }

    /**
     * Updates both compiled visibility paths for an element belonging to this open session. Closed
     * sessions and foreign handles are rejected, while the element's template property and bound
     * observable remain unchanged.
     */
    @Override
    public void setVisible(DDUIElement element, boolean visible) {
        ensureOpen();
        requireElement(element);
        visibleBindings.get(element).applyServer(visible);
    }

    /**
     * Updates an attached element's disabled binding when one was compiled for it. Other attached
     * elements ignore the request; closed sessions and foreign handles are rejected before any
     * update is sent.
     */
    @Override
    public void setDisabled(DDUIElement element, boolean disabled) {
        ensureOpen();
        requireElement(element);
        var binding = disabledBindings.get(element);
        if (binding != null) {
            binding.applyServer(disabled);
        }
    }

    /**
     * Requests programmatic closure for this session's form identifier. The close path marks the
     * session closed before packet submission; normal return from submission permits subscription
     * cleanup followed by the template's close callback.
     */
    @Override
    public void close() {
        closeInternal(Integer.valueOf(formId), DDUIScreenCloseReason.PROGRAMMATIC);
    }

    /**
     * Discards an open session without sending a packet or invoking a close callback. The viewer's
     * active reference is removed only when it still points here, and subscriptions are then
     * released.
     */
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

    /**
     * Returns whether a non-{@code null} boxed identifier matches this session's form identifier.
     */
    public boolean matchesFormId(Integer formId) {
        return formId != null && formId == this.formId;
    }

    /**
     * Routes an incoming client update to its compiled binding and reports whether a path matched.
     * Closed sessions, missing updates, other stores or properties, and unknown paths return
     * {@code false}.
     *
     * <p>
     * A matching binding normalizes the payload before changing session state or a client-writable
     * observable. The binding then dispatches its callback, even for an unchanged normalized value.
     * Callback or observer failures propagate, preventing the final {@code true} return after any
     * preceding source or state mutation; no rollback restores that earlier state.
     */
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

    /**
     * Marks an open session closed, releases subscriptions, and reports the mapped client reason
     * when no message-box result was recorded. The caller owns removal of the viewer's
     * active-session reference before this notification. A recorded response suppresses the close
     * callback, including when its response callback failed earlier. Repeated calls after closure
     * do nothing, and callback failures propagate after the closed state and cleanup have been
     * applied.
     */
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

    /**
     * Submits the compiled initial data-store tree before submitting the screen-show request and
     * form identifier. Callers register the active session first; an exception during submission
     * can stop the sequence without confirming receipt of either packet.
     */
    public void show() {
        var encoder = viewer.getProtocol().getEncoder();
        // Publish the property tree before showing the screen because its layout
        // reads that state when the client opens this form identifier.
        viewer.sendPacket(encoder.encodeDataStoreChange(DATA_STORE_NAME, propertyName, initialState));
        viewer.sendPacket(encoder.encodeDataDrivenUIShowScreen(screenId, formId));
    }

    /**
     * Compiles the supported screen template into initial state and runtime bindings. Unknown
     * subclasses raise {@code IllegalArgumentException}; partial compilation is not rolled back if
     * a later element or property fails.
     */
    private void compile() {
        switch (screen) {
            case CustomFormScreen customForm -> compileCustomForm(customForm);
            case MessageBoxScreen messageBox -> compileMessageBox(messageBox);
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        }
    }

    /**
     * Compiles the title and ordered element list into the custom-form data-store shape. Ordinary
     * elements receive consecutive indexed layout paths, while a dedicated close button uses
     * {@code closeButton} without consuming an index.
     *
     * <p>
     * The compiled identity map associates later runtime operations with these exact handles. A
     * second close button raises {@code IllegalArgumentException}, potentially after earlier
     * properties installed subscriptions. The layout stores its numeric length as a long because
     * client data-store collections use explicit length metadata rather than a Java list.
     */
    private void compileCustomForm(CustomFormScreen customForm) {
        registerProperty(initialState, "title", "title", PropertyType.STRING, binding(customForm.getTitle()));

        var layout = new LinkedHashMap<String, Object>();
        initialState.put("layout", layout);

        var layoutIndex = 0;
        var hasCloseButton = false;
        for (var element : customForm.getElements()) {
            // The close control occupies a dedicated property rather than a layout
            // entry, keeping ordinary client paths stable when this control is present.
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
        // The data-store collection uses explicit long length metadata.
        layout.put("length", (long) layoutIndex);
    }

    /**
     * Compiles a supported element into its client property map using the previously assigned
     * identity path. An unknown element type raises {@code IllegalArgumentException}, and earlier
     * compilation work remains installed.
     */
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

    /**
     * Compiles the text field frame and registers its editable string value binding. Client changes
     * resolve the element's current callback on dispatch, while runtime reads and writes use this
     * original handle's compiled binding.
     */
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

    /**
     * Compiles the toggle frame and registers its editable boolean value binding. Client changes
     * resolve the element's current callback on dispatch, while runtime reads and writes use this
     * original handle's compiled binding.
     */
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

    /**
     * Compiles the integer slider frame and registers its editable long value binding. Client
     * changes resolve the element's current callback on dispatch, while runtime reads and writes
     * use this original handle's compiled binding.
     */
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

    /**
     * Compiles the dropdown frame and registers its editable long value binding. Client changes
     * resolve the element's current callback on dispatch, while runtime reads and writes use this
     * original handle's compiled binding.
     */
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

    /**
     * Compiles a normal button's label, visibility, disabled state, tooltip, and click counter
     * binding. Each accepted client counter update invokes the element's current click callback;
     * the counter value is not deduplicated.
     */
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

    /**
     * Compiles the dedicated close button and binds its client click counter. The configured
     * callback runs before silent session closure, so a callback exception propagates and prevents
     * that subsequent close request.
     */
    private Map<String, Object> compileCloseButton(CloseButton element) {
        var path = elementPath(element);
        var data = compileButtonFrame(
                element,
                binding(element.getLabel()),
                null
        );
        registerInboundLong(data, "onClick", path + ".onClick", value -> {
            // The application callback can inspect or update the open session;
            // closure follows only when that callback returns successfully.
            element.getOnClick().accept(this);
            closeSilently(formId);
        });
        return data;
    }

    /**
     * Compiles a read-only label with its text and visibility bindings.
     */
    private Map<String, Object> compileLabel(Label element) {
        return compileTextElementFrame(element, "label_visible", binding(element.getText()));
    }

    /**
     * Compiles a section heading with its text and visibility bindings.
     */
    private Map<String, Object> compileHeader(Header element) {
        return compileTextElementFrame(element, "header_visible", binding(element.getText()));
    }

    /**
     * Compiles a decorative divider with the visibility paths expected by its client frame.
     */
    private Map<String, Object> compileDivider(Divider element) {
        var data = new LinkedHashMap<String, Object>();
        registerVisible(data, element, "divider_visible");
        return data;
    }

    /**
     * Compiles a decorative spacer with the visibility paths expected by its client frame.
     */
    private Map<String, Object> compileSpacer(Spacer element) {
        var data = new LinkedHashMap<String, Object>();
        registerVisible(data, element, "spacer_visible");
        return data;
    }

    /**
     * Compiles message-box title, body, labels, and inbound button counter bindings. Each counter
     * selects a fixed result enum and delegates to the shared response gate, preserving button
     * identity independently of the incoming numeric counter. Property observables are subscribed
     * during compilation, while callbacks are resolved from the shared template when a response
     * arrives. Partial failure leaves earlier bindings and subscriptions installed rather than
     * undoing compilation.
     */
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

    /**
     * Registers one visibility binding for the generic path and the element-specific visibility
     * path. Both initial keys use the template snapshot, while observable-backed runtime binding
     * initialization uses the source's current value.
     *
     * <p>
     * The shared binding sends subsequent visibility changes to both paths so the client frame and
     * its specific element flag remain aligned. The element identity indexes that binding for
     * session updates. Callers coordinate property mutation during compilation because the initial
     * tree and live source can otherwise capture different moments.
     */
    private void registerVisible(Map<String, Object> data, DDUIElement element, String specificKey) {
        var initial = element.getVisible().value();
        // Both client visibility aliases address the same control state, so retain
        // one runtime binding that publishes later changes to both paths together.
        data.put("visible", initial);
        data.put(specificKey, initial);

        var binding = bindServer(PropertyType.BOOLEAN, binding(element.getVisible()),
                elementPath(element) + ".visible", elementPath(element) + "." + specificKey);
        visibleBindings.put(element, binding);
    }

    /**
     * Registers the initial disabled flag and its one-way server binding for this element. The
     * resulting binding is indexed by the original handle so session changes bypass template
     * reconfiguration.
     */
    private void registerDisabled(Map<String, Object> data, DDUIElement element, PropertyBinding<Boolean> disabledBinding) {
        data.put("disabled", disabledBinding.value());

        var binding = bindServer(PropertyType.BOOLEAN, disabledBinding, elementPath(element) + ".disabled");
        disabledBindings.put(element, binding);
    }

    /**
     * Registers a one-way property binding and writes its normalized initial value into the
     * supplied map. Observable changes publish later updates, while a literal property retains only
     * its compiled value.
     */
    private <T> void registerProperty(Map<String, Object> data, String key, String path, PropertyType type, PropertyBinding<T> property) {
        var binding = bindServer(type, property, path);
        data.put(key, binding.currentValue());
    }

    /**
     * Registers an inbound long-valued action counter and stores its initial zero in the client
     * map. Every matching update dispatches the supplied action callback after normalization,
     * without validating counter progression.
     */
    private void registerInboundLong(Map<String, Object> data, String key, String path, Consumer<Object> onClientChange) {
        var binding = bindInboundAction(path, onClientChange);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
    }

    /**
     * Registers an editable property under its client path and original element identity, then
     * stores the normalized initial value. The path handles incoming updates, while the identity
     * index supports runtime value access.
     */
    private <T> void registerValue(ValueElement<T> element, Map<String, Object> data, String key, String path, PropertyType type,
                                   PropertyBinding<T> property, Consumer<Object> onClientChange) {
        var binding = bindProperty(type, property, onClientChange, path);
        data.put(key, binding.currentValue());
        inboundBindings.put(path, binding);
        valueBindings.put(element, binding);
    }

    /**
     * Subscribes a binding to a non-{@code null} observable and retains the unsubscribe handle for
     * cleanup. A missing source requires no subscription, while notifications execute the binding
     * synchronously on the source writer's thread.
     */
    @SuppressWarnings("unchecked")
    private void subscribe(ServerBinding binding, Observable<?> observable) {
        if (observable == null) {
            return;
        }
        subscriptions.add(((Observable<Object>) observable).subscribe(binding::applyServer));
    }

    /**
     * Returns the compiled path for the supplied original element handle. Missing handles raise
     * {@code IllegalArgumentException}, preventing runtime operations from addressing structurally
     * similar or foreign template elements.
     */
    private String elementPath(DDUIElement element) {
        var path = elementPaths.get(element);
        if (path == null) {
            throw new IllegalArgumentException("Element does not belong to this DDUI session");
        }
        return path;
    }

    /**
     * Rejects an already closed session before operations read or mutate runtime bindings.
     */
    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("DDUI session is already closed");
        }
    }

    /**
     * Returns the value binding indexed by the supplied original element handle. A handle without a
     * compiled editable value raises {@code IllegalArgumentException}, including foreign elements
     * and decorative controls.
     */
    private BoundProperty requireValueBinding(ValueElement<?> element) {
        var binding = valueBindings.get(element);
        if (binding == null) {
            throw new IllegalArgumentException("Element does not belong to this DDUI session");
        }
        return binding;
    }

    /**
     * Checks that the supplied original handle belongs to this compiled session. Unknown handles
     * raise {@code IllegalArgumentException}, preventing visibility or disabled-state updates from
     * targeting another screen's elements.
     */
    private void requireElement(DDUIElement element) {
        if (!elementPaths.containsKey(element)) {
            throw new IllegalArgumentException("Element does not belong to this DDUI session");
        }
    }

    /**
     * Closes this session without dispatching the template's ordinary close callback. The normal
     * close path still removes an active reference, marks closure, and submits the close packet
     * before cleaning subscriptions when submission returns normally.
     */
    private void closeSilently(Integer closeFormId) {
        closeInternal(closeFormId, null);
    }

    /**
     * Closes an open session with an optional form target and optional callback reason. Repeated
     * closure returns immediately, and removal only clears the viewer's active reference when it
     * still points to this session.
     *
     * <p>
     * The session becomes closed before close-packet submission, then cleanup precedes callback
     * dispatch after submission returns normally. A thrown submission failure prevents cleanup and
     * callback work; a normal return does not establish client receipt because packet handling can
     * reject submission. A {@code null} reason suppresses the callback, and later failure does not
     * reopen the session.
     */
    private void closeInternal(Integer closeFormId, DDUIScreenCloseReason closeReason) {
        if (closed) {
            return;
        }
        if (viewer.getActiveScreen() == this) {
            viewer.removeActiveScreen();
        }
        // Mark closure before packet submission so reentrant guarded updates stop;
        // cleanup follows a normal submission return without establishing client receipt.
        closed = true;
        sendClosePacket(closeFormId);
        cleanup();
        if (closeReason != null) {
            screen.handleClose(this, closeReason);
        }
    }

    /**
     * Records and dispatches the first message-box result, ignoring later selections once a result
     * exists. The button-specific callback runs before the shared response callback and silent
     * closure.
     *
     * <p>
     * Recording the result first prevents reentrant response handling from choosing another button.
     * A callback failure leaves that result recorded but prevents later callbacks or closure.
     * Normal client-close reporting also checks this result, so a failed response callback can
     * leave an open session whose later close callback is suppressed.
     */
    private void handleMessageBoxResponse(MessageBoxScreen messageBox, MessageBoxResult result) {
        if (messageBoxResult != null) {
            return;
        }

        // Record the winning button before application callbacks because they can
        // reenter session handling; later selections must not dispatch another response.
        messageBoxResult = result;
        switch (result) {
            case BUTTON1 -> messageBox.handleButton1(this);
            case BUTTON2 -> messageBox.handleButton2(this);
        }
        messageBox.handleResponse(this, result);
        closeSilently(formId);
    }

    /**
     * Sends a close request for the supplied form identifier through the viewer encoder.
     */
    private void sendClosePacket(Integer closeFormId) {
        viewer.sendPacket(viewer.getProtocol().getEncoder().encodeDataDrivenUICloseScreen(closeFormId));
    }

    /**
     * Closes every retained subscription handle and clears the handle list afterward. Individual
     * {@code Exception} failures are ignored so other handles are attempted; errors outside that
     * exception hierarchy still abort cleanup.
     */
    private void cleanup() {
        for (var subscription : subscriptions) {
            try {
                subscription.close();
            } catch (Exception ignored) {
                // A failed unsubscribe must not prevent attempts for other properties;
                // closing continues and drops the retained handles after this pass.
            }
        }
        subscriptions.clear();
    }

    /**
     * Sends one normalized value to the supplied client paths for this session's data-store
     * property. Closed sessions and empty path lists suppress delivery, preventing ordinary
     * post-close binding updates from sending new state.
     */
    private void sendUpdate(List<String> paths, Object value) {
        if (closed || paths.isEmpty()) {
            return;
        }

        viewer.sendPacket(viewer.getProtocol().getEncoder().encodeDataStoreUpdates(
                DATA_STORE_NAME,
                propertyName,
                paths,
                value
        ));
    }

    /**
     * Compiles label, description, visibility, and disabled-state properties shared by editable
     * controls.
     */
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

    /**
     * Compiles a button frame with label and visibility, optionally registering a disabled binding.
     * Dedicated close buttons omit that binding, so runtime disabled requests for them are ignored.
     */
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

    /**
     * Compiles read-only text and visibility properties for a text element frame.
     */
    private Map<String, Object> compileTextElementFrame(DDUIElement element, String specificVisibleKey, PropertyBinding<String> text) {
        var path = elementPath(element);
        var data = new LinkedHashMap<String, Object>();
        registerProperty(data, "text", path + ".text", PropertyType.STRING, text);
        registerVisible(data, element, specificVisibleKey);
        return data;
    }

    /**
     * Converts dropdown entries to the client's indexed map while preserving each entry's label,
     * description, and numeric value. The explicit long {@code length} describes the number of
     * selectable entries rather than the largest item value.
     */
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
        // Collection size describes indexes, independently of item selection values.
        items.put("length", (long) element.getItems().size());
        return items;
    }

    /**
     * Captures a property's current observable value when bound, otherwise its stored literal
     * snapshot. The resulting record retains the same source reference for later subscription
     * rather than copying the observable.
     */
    private static <T> PropertyBinding<T> binding(Property<T> value) {
        var observable = value.observable();
        return new PropertyBinding<>(observable != null ? observable.get() : value.value(), observable);
    }

    /**
     * Creates a normalized one-way binding for the supplied paths and subscribes its observable
     * source when present. The binding retains immutable path membership, while later source
     * notifications update its runtime value.
     */
    private <T> ServerBinding bindServer(PropertyType type, PropertyBinding<T> property, String... paths) {
        var binding = new ServerBinding(type, List.of(paths), property.value());
        subscribe(binding, property.observable());
        return binding;
    }

    /**
     * Creates an inbound long counter binding initialized to zero with no observable source. Client
     * updates invoke the supplied action callback regardless of counter equality or progression,
     * and server echo suppression is unnecessary.
     */
    private BoundProperty bindInboundAction(String path, Consumer<Object> onClientChange) {
        return new BoundProperty(PropertyType.LONG, List.of(path), 0L, null, false, onClientChange);
    }

    /**
     * Creates an editable normalized binding with the observable's current client-write policy and
     * subscribes its source. Literal properties and server-only observables still permit
     * session-local client values without writing those values back to a shared source.
     */
    private <T> BoundProperty bindProperty(PropertyType type, PropertyBinding<T> property, Consumer<Object> onClientChange, String... paths) {
        var observable = property.observable();
        var binding = new BoundProperty(type, List.of(paths), property.value(), observable, observable != null && observable.clientWritable(), onClientChange);
        subscribe(binding, observable);
        return binding;
    }

    /**
     * Returns the client screen identifier for a supported custom-form or message-box template. Any
     * other screen subtype raises {@code IllegalArgumentException} before compilation can choose an
     * unsupported client layout.
     */
    private static String resolveScreenId(DDUIScreen screen) {
        return switch (screen) {
            case CustomFormScreen ignored -> CUSTOM_FORM_SCREEN_ID;
            case MessageBoxScreen ignored -> MESSAGE_BOX_SCREEN_ID;
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        };
    }

    /**
     * Returns the data-store property name for a supported custom-form or message-box template. Any
     * other screen subtype raises {@code IllegalArgumentException} before compilation can choose an
     * unsupported client layout.
     */
    private static String resolvePropertyName(DDUIScreen screen) {
        return switch (screen) {
            case CustomFormScreen ignored -> CUSTOM_FORM_PROPERTY;
            case MessageBoxScreen ignored -> MESSAGE_BOX_PROPERTY;
            default -> throw new IllegalArgumentException("Unsupported DDUI screen type: " + screen.getClass().getName());
        };
    }

    /**
     * Maps each protocol close reason to the corresponding API close reason.
     */
    private static DDUIScreenCloseReason mapCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason reason) {
        return switch (reason) {
            case PROGRAMMATIC_CLOSE -> DDUIScreenCloseReason.PROGRAMMATIC;
            case PROGRAMMATIC_CLOSE_ALL -> DDUIScreenCloseReason.PROGRAMMATIC_ALL;
            case CLIENT_CANCELED -> DDUIScreenCloseReason.CLOSED;
            case USER_BUSY -> DDUIScreenCloseReason.BUSY;
            case INVALID_FORM -> DDUIScreenCloseReason.INVALID;
        };
    }

    /**
     * {@code PropertyType} identifies the normalized scalar representation used by a compiled DDUI
     * binding.
     */
    private enum PropertyType {
        /**
         * String-valued client property representation.
         */
        STRING,
        /**
         * Boolean-valued client property representation.
         */
        BOOLEAN,
        /**
         * Long-valued client property representation.
         */
        LONG
    }

    /**
     * {@code ServerBinding} retains one normalized value and immutable client paths for
     * server-originated updates.
     */
    private class ServerBinding {
        /**
         * Scalar normalization rule for this binding.
         */
        private final PropertyType type;
        /**
         * {@code paths} holds an immutable copy of all client locations updated together whenever
         * this binding publishes a value.
         */
        private final List<String> paths;
        /**
         * Current normalized runtime property value.
         */
        private Object value;

        /**
         * Creates a binding with normalized initial data and an immutable copy of its client paths.
         * Unsupported input shapes use the normalization defaults, and later changes to the
         * caller's path list cannot redirect updates.
         */
        private ServerBinding(PropertyType type, List<String> paths, Object value) {
            this.type = type;
            this.paths = List.copyOf(paths);
            this.value = normalize(type, value);
        }

        /**
         * Returns the normalized runtime value currently retained by this binding.
         */
        protected final Object currentValue() {
            return value;
        }

        /**
         * Returns the scalar normalization rule assigned to this compiled binding.
         */
        protected final PropertyType type() {
            return type;
        }

        /**
         * Normalizes and stores the supplied runtime value without publishing any client update.
         */
        protected final void updateValue(Object value) {
            this.value = normalize(type, value);
        }

        /**
         * Normalizes and stores a server-originated value before publishing it to all retained
         * paths. A send failure leaves the new runtime value stored, and a closed session
         * suppresses submission without suppressing storage.
         */
        protected void applyServer(Object value) {
            updateValue(value);
            sendUpdate(paths, this.value);
        }
    }

    /**
     * {@code BoundProperty} extends a server binding with client-write policy, callback dispatch,
     * and local echo suppression.
     */
    private final class BoundProperty extends ServerBinding {
        /**
         * Shared source, or {@code null} for literals.
         */
        private final Observable<?> observable;
        /**
         * Whether client updates write the shared source.
         */
        private final boolean clientWritable;
        /**
         * Callback for accepted normalized client values.
         */
        private final Consumer<Object> onClientChange;
        /**
         * {@code suppressOutbound} marks this binding's synchronous client write into its
         * observable. The flag suppresses only this session's echo and is reset even when listeners
         * fail.
         */
        private boolean suppressOutbound;

        /**
         * Creates a normalized editable binding retaining its source, write policy, and client
         * callback. The constructor itself installs no subscription; its factory subscribes after
         * the object has been fully initialized.
         */
        private BoundProperty(PropertyType type, List<String> paths, Object value, Observable<?> observable, boolean clientWritable,
                              Consumer<Object> onClientChange) {
            super(type, paths, value);
            this.observable = observable;
            this.clientWritable = clientWritable;
            this.onClientChange = onClientChange;
        }

        /**
         * Normalizes a client value, applies its source-write policy, and then dispatches the
         * client callback. Literal or server-only sources update this binding directly without
         * changing a shared observable.
         *
         * <p>
         * A writable source receives the value while this binding suppresses its own outbound echo;
         * other sessions subscribed to the source can still publish. The suppression flag is
         * restored in {@code finally}. Observer failure can prevent this binding's own listener or
         * callback from running after the source changes, and neither observer nor callback failure
         * rolls back earlier mutations.
         */
        @SuppressWarnings("unchecked")
        private void applyClient(Object value) {
            var normalized = normalize(type(), value);
            if (observable != null && clientWritable) {
                // Observable notification synchronously returns through this binding;
                // suppress only its echo while allowing other subscribed sessions to publish.
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

        /**
         * Applies a source notification locally while this binding is echo-suppressed, otherwise
         * publishing through the base binding. Suppression belongs to this session only, so the
         * same source can update other sessions.
         *
         * <p>
         * During a client write, {@code Observable.set} synchronously invokes this binding before
         * returning to client callback dispatch. Updating the local value without sending avoids
         * returning that client value to its originating session; ordinary server notifications
         * normalize, store, and send it through the retained paths.
         */
        @Override
        protected void applyServer(Object value) {
            // The originating client already owns this value, so update its local
            // session mirror without sending the same observable notification back.
            if (suppressOutbound) {
                updateValue(value);
                return;
            }
            super.applyServer(value);
        }
    }

    /**
     * Converts property data to the scalar representation expected by the client binding. Strings
     * use {@code String.valueOf} with {@code null} becoming empty text, booleans accept only
     * {@code Boolean}, and numbers use {@code longValue}; unsupported boolean or numeric shapes
     * become zero-like defaults.
     */
    private static Object normalize(PropertyType type, Object value) {
        // Data-store updates can carry mismatched scalar shapes; normalize them
        // to the binding's configured representation before callbacks or outbound encoding.
        return switch (type) {
            case STRING -> value == null ? "" : String.valueOf(value);
            case BOOLEAN -> value instanceof Boolean bool ? bool : false;
            case LONG -> value instanceof Number number ? number.longValue() : 0L;
        };
    }

    /**
     * {@code PropertyBinding} captures an initial value together with its optional shared
     * observable source.
     *
     * @param value initial literal or current observable value
     * @param observable shared source reference, or {@code null} for a literal property
     */
    private record PropertyBinding<T>(T value, Observable<T> observable) {
    }
}
