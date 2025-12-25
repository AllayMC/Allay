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
 * @author daoge_cmd
 */
class FormTest {

    @Nested
    class SimpleFormTests {

        SimpleForm form;

        @BeforeEach
        void setUp() {
            form = Forms.simple();
        }

        @Test
        void testBasicProperties() {
            form.title("Test Title")
                    .content("Test Content");

            assertEquals("Test Title", form.getTitle());
            assertEquals("Test Content", form.getContent());
        }

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

            // Button indices are counted only for buttons, not labels/headers/dividers
            form.handleResponse("1");

            assertNotNull(responseButton.get());
            assertEquals("Button 1", responseButton.get().getText());
        }

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

        @Test
        void testHandleResponseWithInvalidData() {
            AtomicBoolean callbackCalled = new AtomicBoolean(false);

            form.button("Button").onClick(btn -> callbackCalled.set(true));

            // Invalid data should not call callbacks
            form.handleResponse("invalid");

            assertFalse(callbackCalled.get());
        }

        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.CLOSED);

            assertEquals(FormCancelReason.CLOSED, closeReason.get());
        }

        @Test
        void testHandleCloseWithBusyReason() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.BUSY);

            assertEquals(FormCancelReason.BUSY, closeReason.get());
        }

        @Test
        void testOnCloseWithRunnable() {
            AtomicBoolean closeCalled = new AtomicBoolean(false);

            form.onClose(() -> closeCalled.set(true));

            form.handleClose(FormCancelReason.CLOSED);

            assertTrue(closeCalled.get());
        }

        @Test
        void testButtonWithImage() {
            var button = form.buttonWithPathImage("Image Button", "textures/items/diamond");

            assertNotNull(button.getImage());
            assertEquals("Image Button", button.getText());
        }

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

    @Nested
    class ModalFormTests {

        ModalForm form;

        @BeforeEach
        void setUp() {
            form = Forms.modal();
        }

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

        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.CLOSED);

            assertEquals(FormCancelReason.CLOSED, closeReason.get());
        }

        @Test
        void testGetResponseBeforeHandle() {
            assertEquals("null", form.getResponse());
        }

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

    @Nested
    class CustomFormTests {

        CustomForm form;

        @BeforeEach
        void setUp() {
            form = Forms.custom();
        }

        @Test
        void testBasicProperties() {
            form.title("Registration Form");

            assertEquals("Registration Form", form.getTitle());
        }

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

        @Test
        void testHandleResponseWithSlider() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.slider("Volume", 0, 100, 1, 50)
                    .onResponse(responseData::set);

            form.handleResponse("[\"75.0\"]");

            assertNotNull(responseData.get());
            assertEquals("75.0", responseData.get().getFirst());
            assertEquals(75.0f, Float.parseFloat(responseData.get().getFirst()), 0.01f);
        }

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

        @Test
        void testHandleResponseWithNonResponseElements() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            // Label, Header, and Divider do NOT produce responses
            form.header("Personal Info")
                    .input("Name")
                    .label("Please enter your real name")
                    .divider()
                    .toggle("Agree to terms")
                    .onResponse(responseData::set);

            // Only input and toggle produce responses
            form.handleResponse("[\"Alice\", \"true\"]");

            assertNotNull(responseData.get());
            assertEquals(2, responseData.get().size());
            assertEquals("Alice", responseData.get().get(0));
            assertEquals("true", responseData.get().get(1));
        }

        @Test
        void testHandleClose() {
            AtomicReference<FormCancelReason> closeReason = new AtomicReference<>();

            form.onClose(closeReason::set);

            form.handleClose(FormCancelReason.BUSY);

            assertEquals(FormCancelReason.BUSY, closeReason.get());
        }

        @Test
        void testGetResponseBeforeHandle() {
            assertNull(form.getResponse());
        }

        @Test
        void testIconWithPath() {
            form.pathIcon("textures/ui/icon");

            assertNotNull(form.getIcon());
        }

        @Test
        void testIconWithUrl() {
            form.urlIcon("https://example.com/icon.png");

            assertNotNull(form.getIcon());
        }

        @Test
        void testToJson() {
            form.title("Form Title")
                    .input("Name")
                    .toggle("Enable");

            String json = form.toJson();

            assertTrue(json.contains("\"type\":\"custom_form\""));
            assertTrue(json.contains("\"title\":\"Form Title\""));
        }

        @Test
        void testDropdownWithDefaultIndex() {
            form.dropdown("Select", List.of("A", "B", "C"), 2);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        @Test
        void testSliderWithStep() {
            form.slider("Value", 0, 100, 5, 50);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        @Test
        void testStepSliderWithDefaultStep() {
            form.stepSlider("Level", List.of("Low", "Medium", "High"), 1);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        @Test
        void testToggleWithDefaultValue() {
            form.toggle("Enabled", true);

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        @Test
        void testInputWithPlaceholderAndDefault() {
            form.input("Field", "placeholder", "default value");

            var elements = form.getContent();
            assertEquals(1, elements.size());
        }

        @Test
        void testHandleResponseWithEmptyArray() {
            AtomicReference<List<String>> responseData = new AtomicReference<>();

            form.onResponse(responseData::set);

            form.handleResponse("[]");

            assertNotNull(responseData.get());
            assertTrue(responseData.get().isEmpty());
        }
    }

    @Nested
    class FormFactoryTests {

        @Test
        void testFormsSimple() {
            var form = Forms.simple();
            assertNotNull(form);
            assertInstanceOf(SimpleForm.class, form);
        }

        @Test
        void testFormsModal() {
            var form = Forms.modal();
            assertNotNull(form);
            assertInstanceOf(ModalForm.class, form);
        }

        @Test
        void testFormsCustom() {
            var form = Forms.custom();
            assertNotNull(form);
            assertInstanceOf(CustomForm.class, form);
        }
    }

    @Nested
    class FormCancelReasonTests {

        @Test
        void testCancelReasons() {
            assertEquals(2, FormCancelReason.values().length);
            assertEquals(FormCancelReason.CLOSED, FormCancelReason.valueOf("CLOSED"));
            assertEquals(FormCancelReason.BUSY, FormCancelReason.valueOf("BUSY"));
        }
    }
}
