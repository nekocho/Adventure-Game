// When the DOM content is fully loaded...
document.addEventListener("DOMContentLoaded", function() {
    // Select all elements with the class "typewriter"
    const textElements = document.querySelectorAll(".typewriter");

    // For each element with the class "typewriter"...
    textElements.forEach(textElement => {
        // Get the text content of the element and trim any leading/trailing whitespace
        const text = textElement.textContent.trim();

        // Clear the text content of the element
        textElement.textContent = '';

        // Define a function for the typewriter effect
        function typeWriter(text, i = 0) {
            // If there are characters left to type...
            if (i < text.length) {
                // Append the next character to the text content of the element
                textElement.textContent += text.charAt(i);
                // Increment the index to move to the next character
                i++;
                // Schedule the next iteration of the typewriter effect after a delay
                setTimeout(() => typeWriter(text, i), 50);
            }
        }

        // Make the text visible before starting the typewriting effect by removing the "hidden" class
        textElement.classList.remove('hidden');

        // Start the typewriter effect after a delay
        setTimeout(() => typeWriter(text), 250);
    });
});