package org.allaymc.api.form;

import org.allaymc.api.form.element.Button;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.ModalForm;
import org.allaymc.api.form.type.SimpleForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code FormTest} verifies form builders, callback dispatch, response storage, and serialized
 * layout fields.
 *
 * @author daoge_cmd
 */
class FormTest {

    /**
     * {@code SimpleFormTests} verifies simple-form configuration, button mapping, callback
     * dispatch, and serialization.
     */
    @Nested
    class SimpleFormTests {

        /**
         * Fresh form fixture for each test.
         */
        SimpleForm form;

        /**
         * Creates a fresh {@code SimpleForm} fixture before each test method.
         */
        @BeforeEach
        void setUp() {
            form = Forms.simple();
        }

        /**
         * Configures a simple form with a title and body. Its getters must return the supplied
         * values, protecting the fluent builder's property storage behavior.
         */
        @Test
        void testBasicProperties() {
            form.title("Test Title")
                    .content("Test Content");

            assertEquals("Test Title", form.getTitle());
            assertEquals("Test Content", form.getContent());
        }

        /**
         * Submits index zero to a form containing one button. Both callbacks must receive the
         * selection, and the stored response must reference that selected button after dispatch.
         */
        @Test
        void testHandleResponseWithSingleButton() {
            AtomicBoolean buttonClicked = new AtomicBoolean(false);
            AtomicReference<Button> responseButton = new AtomicReference<>();

            form.button("Click Me").onClick(btn -> buttonClicked.set(true));
            form.onResponse(responseButton::set);

            form.handleResponse("0");

            assertTrue(buttonClicked.get());
            assertNotNull(responseButton.get());
            assertEquals("Click Me", responseButton.get().getText());
            assertEquals(responseButton.get(), form.getResponse());
        }

        /**
         * Submits the middle button index to a three-button form. The matching button callback and
         * shared response must identify that entry rather than the first or final button.
         */
        @Test
        void testHandleResponseWithMultipleButtons() {
            AtomicInteger clickedIndex = new AtomicInteger(-1);
            AtomicReference<Button> responseButton = new AtomicReference<>();

            form.button("Button 0").onClick(btn -> clickedIndex.set(0));
            form.button("Button 1").onClick(btn -> clickedIndex.set(1));
            form.button("Button 2").onClick(btn -> clickedIndex.set(2));
            form.onResponse(responseButton::set);

            form.handleResponse("1");

            assertEquals(1, clickedIndex.get());
            assertNotNull(responseButton.get());
            assertEquals("Button 1", responseButton.get().getText());
        }

        /**
         * Mixes decorative entries with selectable buttons before submitting a button ordinal. The
         * returned selection must count only buttons, protecting index translation when headings,
         * labels, and dividers are present.
         */
        @Test
        void testHandleResponseWithLabelsAndHeaders() {
            AtomicReference<Button> responseButton = new AtomicReference<>();

            form.header("Header");
            form.button("Button 0");
            form.label("Label");
            form.button("Button 1");
            form.divider();
            form.button("Button 2");
            form.onResponse(responseButton::set);

            // The response uses a button ordinal, so decorative layout entries
            // must not associate it with a different retained button instance.
            form.handleResponse("1");

            assertNotNull(responseButton.get());
            assertEquals("Button 1", responseButton.get().getText());
        }

        /**
         * Submits an index beyond a single-button form's available choices. Neither callback may
         * run, and the stored response must remain absent after the invalid selection.
         */
        @Test
        void testHandleResponseWithInvalidIndex() {
            AtomicBoolean callbackCalled = new AtomicBoolean(false);

            form.button("Only Button").onClick(btn -> callbackCalled.set(true));
            form.onResponse(btn -> callbackCalled.set(true));

            // Index out of range should not call callbacks
            form.handleResponse("5");

            assertFalse(callbackCalled.get());
            assertNull(form.getResponse());
        }

        /**
         * Submits nonnumeric data to a form with a button callback. The handler must return without
         * invoking that callback, protecting malformed-response rejection before button dispatch.
         */
        @Test
        void testHandleResponseWithInvalidData() {
            AtomicBoolean callbackCalled = new AtomicBoolean(false);

            form.button("Button").onClick(btn -> callbackCalled.set(true));

            // Invalid data should not call callbacks
            form.handleResponse("invalid");

            assertFalse(callbackCalled.get());
        }

        /**
         * Closes a simple form with the explicit closed reason. The registered reason callback must
         * receive that enum value, protecting cancellation forwarding independently of button
         * selection.
         */
        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.CLOSED);

            assertEquals(FormCancelReason.CLOSED, closeReason.get());
        }

        /**
         * Closes a simple form with the busy reason. The reason-aware callback must receive that
         * exact enum value, preserving the distinction from deliberate user dismissal.
         */
        @Test
        void testHandleCloseWithBusyReason() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.BUSY);

            assertEquals(FormCancelReason.BUSY, closeReason.get());
        }

        /**
         * Registers the reason-independent close overload on a simple form. A later closed
         * notification must invoke the action, protecting the wrapper that discards the
         * cancellation reason.
         */
        @Test
        void testOnCloseWithRunnable() {
            AtomicBoolean closeCalled = new AtomicBoolean(false);

            form.onClose(() -> closeCalled.set(true));

            form.handleClose(FormCancelReason.CLOSED);

            assertTrue(closeCalled.get());
        }

        /**
         * Adds a simple-form button using a resource-pack image path. The button must retain an
         * image reference and the supplied label, protecting that image-builder overload.
         */
        @Test
        void testButtonWithImage() {
            var button = form.buttonWithPathImage("Image Button", "textures/items/diamond");

            assertNotNull(button.getImage());
            assertEquals("Image Button", button.getText());
        }

        /**
         * Serializes a simple form containing title, body, and a button. The output must contain
         * the simple layout discriminator and configured title and body fields.
         */
        @Test
        void testToJson() {
            form.title("Title")
                    .content("Content")
                    .button("Button");

            String json = form.toJson();

            assertTrue(json.contains("\"type\":\"form\""));
            assertTrue(json.contains("\"title\":\"Title\""));
            assertTrue(json.contains("\"content\":\"Content\""));
        }
    }

    /**
     * {@code ModalFormTests} verifies modal-form choice dispatch, initial response values, and
     * serialization.
     */
    @Nested
    class ModalFormTests {

        /**
         * Fresh form fixture for each test.
         */
        ModalForm form;

        /**
         * Creates a fresh {@code ModalForm} fixture before each test method.
         */
        @BeforeEach
        void setUp() {
            form = Forms.modal();
        }

        /**
         * Configures a modal form with title, body, and both button labels. The corresponding
         * getters must retain each value, protecting the fluent property configuration chain.
         */
        @Test
        void testBasicProperties() {
            form.title("Confirm")
                    .content("Are you sure?")
                    .trueButton("Yes")
                    .falseButton("No");

            assertEquals("Confirm", form.getTitle());
            assertEquals("Are you sure?", form.getContent());
            assertEquals("Yes", form.getTrueButton());
            assertEquals("No", form.getFalseButton());
        }

        /**
         * Submits the exact {@code "true"} token after registering both choice actions and a shared
         * callback. Only the first action must run, and the callback and stored response must
         * retain that token.
         */
        @Test
        void testHandleResponseTrue() {
            AtomicBoolean trueCalled = new AtomicBoolean(false);
            AtomicBoolean falseCalled = new AtomicBoolean(false);
            AtomicReference<String> responseValue = new AtomicReference<>();

            form.trueButton("Yes", () -> trueCalled.set(true))
                    .falseButton("No", () -> falseCalled.set(true))
                    .onResponse(responseValue::set);

            form.handleResponse("true");

            assertTrue(trueCalled.get());
            assertFalse(falseCalled.get());
            assertEquals("true", responseValue.get());
            assertEquals("true", form.getResponse());
        }

        /**
         * Submits the {@code "false"} token after registering both choice actions and a shared
         * callback. Only the second action must run, and the callback and stored response must
         * retain that token.
         */
        @Test
        void testHandleResponseFalse() {
            AtomicBoolean trueCalled = new AtomicBoolean(false);
            AtomicBoolean falseCalled = new AtomicBoolean(false);
            AtomicReference<String> responseValue = new AtomicReference<>();

            form.trueButton("Yes", () -> trueCalled.set(true))
                    .falseButton("No", () -> falseCalled.set(true))
                    .onResponse(responseValue::set);

            form.handleResponse("false");

            assertFalse(trueCalled.get());
            assertTrue(falseCalled.get());
            assertEquals("false", responseValue.get());
            assertEquals("false", form.getResponse());
        }

        /**
         * Registers modal choice actions independently of their labels, then submits
         * {@code "true"}. The first action must run while the second remains untouched, protecting
         * the separate callback setters.
         */
        @Test
        void testHandleResponseWithOnTrueOnFalse() {
            AtomicBoolean trueCalled = new AtomicBoolean(false);
            AtomicBoolean falseCalled = new AtomicBoolean(false);

            form.onTrue(() -> trueCalled.set(true))
                    .onFalse(() -> falseCalled.set(true));

            form.handleResponse("true");

            assertTrue(trueCalled.get());
            assertFalse(falseCalled.get());
        }

        /**
         * Closes a modal form with the explicit closed reason. The registered callback must receive
         * the same value, protecting cancellation forwarding independently of either choice action.
         */
        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.CLOSED);

            assertEquals(FormCancelReason.CLOSED, closeReason.get());
        }

        /**
         * Reads a modal form response before any input is handled. The accessor must return the
         * literal string {@code "null"}, protecting its special initial response representation.
         */
        @Test
        void testGetResponseBeforeHandle() {
            assertEquals("null", form.getResponse());
        }

        /**
         * Serializes a modal form with both choice labels. The output must contain its layout
         * discriminator, title, and client button field names with the configured labels.
         */
        @Test
        void testToJson() {
            form.title("Title")
                    .content("Content")
                    .trueButton("Yes")
                    .falseButton("No");

            String json = form.toJson();

            assertTrue(json.contains("\"type\":\"modal\""));
            assertTrue(json.contains("\"title\":\"Title\""));
            assertTrue(json.contains("\"button1\":\"Yes\""));
            assertTrue(json.contains("\"button2\":\"No\""));
        }
    }

    /**
     * {@code CustomFormTests} verifies custom-form control insertion, decoded response lists, and
     * serialization.
     */
    @Nested
    class CustomFormTests {

        /**
         * Fresh form fixture for each test.
         */
        CustomForm form;

        /**
         * Creates a fresh {@code CustomForm} fixture before each test method.
         */
        @BeforeEach
        void setUp() {
            form = Forms.custom();
        }

        /**
         * Configures a custom form with a title. Its getter must retain the supplied text,
         * protecting the basic fluent title setter before controls are added.
         */
        @Test
        void testBasicProperties() {
            form.title("Registration Form");

            assertEquals("Registration Form", form.getTitle());
        }

        /**
         * Submits two JSON text values to a custom form with input controls. The callback and
         * stored response must expose the decoded strings in their original order.
         */
        @Test
        void testHandleResponseWithInput() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.input("Username", "Enter username...")
                    .input("Email", "Enter email...")
                    .onResponse(responseData::set);

            form.handleResponse("[\"testuser\", \"test@example.com\"]");

            assertNotNull(responseData.get());
            assertEquals(2, responseData.get().size());
            assertEquals("testuser", responseData.get().get(0));
            assertEquals("test@example.com", responseData.get().get(1));
            assertEquals(responseData.get(), form.getResponse());
        }

        /**
         * Submits a selected dropdown index represented as a JSON string. The custom response
         * callback must receive that index text, protecting forwarding of the supplied selection
         * value.
         */
        @Test
        void testHandleResponseWithDropdown() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.dropdown("Select Rank", "Bronze", "Silver", "Gold")
                    .onResponse(responseData::set);

            // Dropdown returns the selected index as string
            form.handleResponse("[\"1\"]");

            assertNotNull(responseData.get());
            assertEquals("1", responseData.get().getFirst());
        }

        /**
         * Submits two toggle values represented as JSON strings. The callback must retain their
         * text and order, protecting response-list forwarding for multiple boolean controls.
         */
        @Test
        void testHandleResponseWithToggle() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.toggle("Enable Notifications", false)
                    .toggle("Enable PvP", true)
                    .onResponse(responseData::set);

            form.handleResponse("[\"true\", \"false\"]");

            assertNotNull(responseData.get());
            assertEquals("true", responseData.get().get(0));
            assertEquals("false", responseData.get().get(1));
        }

        /**
         * Submits a numeric slider value represented as a JSON string. The callback must retain the
         * decimal text, which the assertion also checks as a float with tolerance.
         */
        @Test
        void testHandleResponseWithSlider() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.slider("Volume", 0, 100, 1, 50)
                    .onResponse(responseData::set);

            form.handleResponse("[\"75.0\"]");

            assertNotNull(responseData.get());
            assertEquals("75.0", responseData.get().getFirst());
            // Allow a hundredth of a unit for the parsed float comparison.
            assertEquals(75.0f, Float.parseFloat(responseData.get().getFirst()), 0.01f);
        }

        /**
         * Submits a step-slider index represented as a JSON string. The callback must receive that
         * index text, protecting forwarding of the supplied step selection value.
         */
        @Test
        void testHandleResponseWithStepSlider() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.stepSlider("Difficulty", "Easy", "Normal", "Hard")
                    .onResponse(responseData::set);

            // StepSlider returns the selected index as string
            form.handleResponse("[\"2\"]");

            assertNotNull(responseData.get());
            assertEquals("2", responseData.get().getFirst());
        }

        /**
         * Submits values for input, dropdown, toggle, slider, and step-slider controls together.
         * The callback must retain all five positions and string values without reordering the
         * mixed response list.
         */
        @Test
        void testHandleResponseWithMixedElements() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.input("Name", "Enter name...")
                    .dropdown("Country", "USA", "UK", "Japan")
                    .toggle("Subscribe")
                    .slider("Age", 18, 100)
                    .stepSlider("Experience", "Beginner", "Intermediate", "Expert")
                    .onResponse(responseData::set);

            form.handleResponse("[\"John\", \"2\", \"true\", \"25.0\", \"1\"]");

            assertNotNull(responseData.get());
            assertEquals(5, responseData.get().size());
            assertEquals("John", responseData.get().get(0));
            assertEquals("2", responseData.get().get(1));  // Japan (index 2)
            assertEquals("true", responseData.get().get(2));
            assertEquals("25.0", responseData.get().get(3));
            assertEquals("1", responseData.get().get(4));  // Intermediate (index 1)
        }

        /**
         * Supplies a short response containing only the editable values despite decorative content
         * in the form. The handler must forward that list unchanged; this case does not test
         * positional default synchronization.
         */
        @Test
        void testHandleResponseWithNonResponseElements() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            // This fixture omits decorative positions to test response forwarding,
            // not the separate server-settings operation that indexes content positions.
            form.header("Personal Info")
                    .input("Name")
                    .label("Please enter your real name")
                    .divider()
                    .toggle("Agree to terms")
                    .onResponse(responseData::set);

            // Supply the two values this fixture expects the callback to receive.
            form.handleResponse("[\"Alice\", \"true\"]");

            assertNotNull(responseData.get());
            assertEquals(2, responseData.get().size());
            assertEquals("Alice", responseData.get().get(0));
            assertEquals("true", responseData.get().get(1));
        }

        /**
         * Closes a custom form with the busy reason. The registered callback must receive that
         * exact reason, protecting cancellation forwarding independently of submitted control
         * values.
         */
        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.BUSY);

            assertEquals(FormCancelReason.BUSY, closeReason.get());
        }

        /**
         * Reads a custom form response before any submission is handled. The accessor must return a
         * missing reference, protecting the initial distinction from an empty submitted list.
         */
        @Test
        void testGetResponseBeforeHandle() {
            assertNull(form.getResponse());
        }

        /**
         * Configures a custom-form icon using a resource-pack path. The form must retain a
         * nonmissing image reference, protecting the path-icon convenience overload's assignment
         * behavior.
         */
        @Test
        void testIconWithPath() {
            form.pathIcon("textures/ui/icon");

            assertNotNull(form.getIcon());
        }

        /**
         * Configures a custom-form icon using a remote URL. The form must retain a nonmissing image
         * reference, protecting the URL-icon convenience overload's assignment behavior.
         */
        @Test
        void testIconWithUrl() {
            form.urlIcon("https://example.com/icon.png");

            assertNotNull(form.getIcon());
        }

        /**
         * Serializes a custom form containing input and toggle controls. The output must contain
         * the custom layout discriminator and configured title, protecting the common serialized
         * layout fields.
         */
        @Test
        void testToJson() {
            form.title("Form Title")
                    .input("Name")
                    .toggle("Enable");

            String json = form.toJson();

            assertTrue(json.contains("\"type\":\"custom_form\""));
            assertTrue(json.contains("\"title\":\"Form Title\""));
        }

        /**
         * Adds a dropdown using the overload with an explicit default index. The content list must
         * gain one entry, protecting that builder path's element insertion.
         */
        @Test
        void testDropdownWithDefaultIndex() {
            form.dropdown("Select", List.of("A", "B", "C"), 2);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        /**
         * Adds a slider using the overload with an explicit step and initial value. The content
         * list must gain one entry, protecting that builder path's element insertion.
         */
        @Test
        void testSliderWithStep() {
            form.slider("Value", 0, 100, 5, 50);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        /**
         * Adds a step slider using the overload with an explicit default step. The content list
         * must gain one entry, protecting that builder path's element insertion.
         */
        @Test
        void testStepSliderWithDefaultStep() {
            form.stepSlider("Level", List.of("Low", "Medium", "High"), 1);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        /**
         * Adds a toggle using the overload with an explicit default state. The content list must
         * gain one entry, protecting that builder path's element insertion.
         */
        @Test
        void testToggleWithDefaultValue() {
            form.toggle("Enabled", true);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        /**
         * Adds a text input with a placeholder and default text. The content list must gain one
         * entry, protecting that builder overload's element insertion behavior.
         */
        @Test
        void testInputWithPlaceholderAndDefault() {
            form.input("Field", "placeholder", "default value");

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        /**
         * Submits an empty JSON array to a custom form. The response callback must receive an
         * existing empty list, preserving the distinction between empty submission and missing
         * response.
         */
        @Test
        void testHandleResponseWithEmptyArray() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.onResponse(responseData::set);

            form.handleResponse("[]");

            assertNotNull(responseData.get());
            assertTrue(responseData.get().isEmpty());
        }
    }

    /**
     * {@code FormFactoryTests} verifies factory creation of each supported JSON form layout.
     */
    @Nested
    class FormFactoryTests {

        /**
         * Creates a form through the simple factory entry point. The result must exist and have the
         * simple-form runtime type, protecting the factory's concrete layout selection.
         */
        @Test
        void testFormsSimple() {
            var form = Forms.simple();
            assertNotNull(form);
            assertInstanceOf(SimpleForm.class, form);
        }

        /**
         * Creates a form through the modal factory entry point. The result must exist and have the
         * modal-form runtime type, protecting the factory's concrete layout selection.
         */
        @Test
        void testFormsModal() {
            var form = Forms.modal();
            assertNotNull(form);
            assertInstanceOf(ModalForm.class, form);
        }

        /**
         * Creates a form through the custom factory entry point. The result must exist and have the
         * custom-form runtime type, protecting the factory's concrete layout selection.
         */
        @Test
        void testFormsCustom() {
            var form = Forms.custom();
            assertNotNull(form);
            assertInstanceOf(CustomForm.class, form);
        }
    }

    /**
     * {@code FormCancelReasonTests} verifies the defined cancellation reason constants and their
     * enum names.
     */
    @Nested
    class FormCancelReasonTests {

        /**
         * Inspects the cancellation enum and resolves both defined names. The assertions protect
         * its two-value set and the identifiers used when callers distinguish closure from a busy
         * viewer.
         */
        @Test
        void testCancelReasons() {
            assertEquals(2, FormCancelReason.values().length);
            assertEquals(FormCancelReason.CLOSED, FormCancelReason.valueOf("CLOSED"));
            assertEquals(FormCancelReason.BUSY, FormCancelReason.valueOf("BUSY"));
        }
    }
}
