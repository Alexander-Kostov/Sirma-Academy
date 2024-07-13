document.addEventListener('DOMContentLoaded', function () {
    const paragraph = document.getElementById('dimensions')

    function updateDimensions() {
        const width = window.innerWidth;
        const height = window.innerHeight;

        paragraph.textContent = `Window dimensions: ${width}px x ${height}px`
    }

    window.addEventListener('resize', updateDimensions);

    updateDimensions();
})