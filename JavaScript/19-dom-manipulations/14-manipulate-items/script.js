function handleOperation() {
    const buttonElement = document.querySelector('button');
    buttonElement.textContent = 'Create New'

    const itemElement = document.querySelector('input[type=text]');

    const itemValue = itemElement.value;

    const liElement = document.createElement('li');
    liElement.addEventListener('click', editElement)
    liElement.textContent = itemValue;

    const ulElement = document.querySelector('#items');
    ulElement.appendChild(liElement);

    itemElement.value = ''; 
}

function editElement(event) {
    const buttonElement = document.querySelector('button')
    
    buttonElement.textContent = 'Edit'

    const itemElement = document.querySelector('input[type=text]');
    const liElement = event.currentTarget;
    itemElement.value = liElement.textContent;

    liElement.remove();
}