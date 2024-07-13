function getAttributes() {
    const anchorTag = document.querySelector('a');

    console.log(anchorTag.getAttribute('id'));
    console.log(anchorTag.getAttribute('type'));
    console.log(anchorTag.getAttribute('hreflang'));
    console.log(anchorTag.getAttribute('rel'));
    console.log(anchorTag.getAttribute('target'));
    console.log(anchorTag.getAttribute('href'));
}