document.addEventListener("DOMContentLoaded", function() {
    // Select all elements with the class "typewriter"
    const textElements = document.querySelectorAll(".typewriter");

    // Initialize animationsEnabled variable based on the value obtained from the server
    const animationsEnabled = document.getElementById("animationsEnabled").value === "true";

    // Function to execute typewriter animation
    function typeWriter(textElement, text, i = 0) {
        // If animations are enabled and there are characters left to type...
        if (animationsEnabled && i < text.length) {
            // Append the next character to the text content of the element
            textElement.textContent += text.charAt(i);

            // Increment the index to move to the next character
            i++;

            // Check if the current character is a punctuation mark
            const currentChar = text.charAt(i - 1);
            let timeout;

            // Set the timeout based on the type of punctuation mark
            if (/[.,\/#!$%\^&\*;:{}=\_`~()?]/.test(currentChar)) {
                timeout = currentChar === ',' ? 100 : 250; // Change speed for commas
            } else {
                timeout = 50; // Default typing speed for non-punctuation characters
            }

            // Schedule the next iteration of the typewriter effect after a delay
            setTimeout(() => typeWriter(textElement, text, i), timeout);
        }
    }

    // Function to toggle animations on/off
    function toggleAnimations() {
        // For each element with the class "typewriter"...
        textElements.forEach(textElement => {
            // Get the text content of the element and trim any leading/trailing whitespace
            const text = textElement.textContent.trim();

            // Clear the text content of the element
            textElement.textContent = '';

            // Make the text visible before starting the typewriting effect by removing the "hidden" class
            textElement.classList.remove('hidden');

            // Start the typewriter effect after a delay
            setTimeout(() => typeWriter(textElement, text), 250);
        });
    }

    // Initial setup
    // Trigger toggleAnimations() only if animations are enabled
    if (animationsEnabled) {
        toggleAnimations();
    }
});
