document.addEventListener("DOMContentLoaded", function() {
    // Select all elements with the class "typewriter"
    const textElements = document.querySelectorAll(".typewriter");

    // Initialize animationsEnabled variable based on the value obtained from the server
    let animationsEnabled = document.getElementById("animationsEnabled").value === "true";
    let speedUp = 1; // Variable to control speed factor
    let spaceBarPressed = false; // Variable to track space bar state

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
                timeout = currentChar === ',' ? 100 / speedUp : 250 / speedUp; // Change speed for commas
            } else {
                timeout = 50 / speedUp; // Default typing speed for non-punctuation characters
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

    // Event listener for keydown event
    document.addEventListener("keydown", function(event) {
        // Check if the pressed key is the space bar
        if (event.code === "Space") {
            spaceBarPressed = true;
            speedUp *= 2; // Double the speed factor
        }
    });

    // Event listener for keyup event
    document.addEventListener("keyup", function(event) {
        // Check if the released key is the space bar
        if (event.code === "Space") {
            spaceBarPressed = false;
            speedUp = 1; // Reset the speed factor
        }
    });
});