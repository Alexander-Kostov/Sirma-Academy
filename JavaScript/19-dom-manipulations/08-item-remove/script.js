function removeColor() {
    const element = document.querySelector('#colorSelect');
    
    console.log(element.selectedIndex);
    element.remove(element.selectedIndex)
}