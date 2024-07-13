function calculate(event) {
    event.preventDefault()
    const radiusElement = document.querySelector('input[name=radius]');
    const volumeElement = document.querySelector('input[name=volume]');

    const radius = Number(radiusElement.value);

    const volume = (4 / 3) * Math.PI * Math.pow(radius, 3);

    volumeElement.value = volume;
}