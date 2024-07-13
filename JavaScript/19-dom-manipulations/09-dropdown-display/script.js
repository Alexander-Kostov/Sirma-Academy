function getOptions() {
    const element = document.querySelector('#mySelect');
    const output = Array.from(element.options).map(o => o.value).join(",");
    console.log(output)
    alert(output)
}