function getFormvalue(event) {
    event.preventDefault();
    const firstNameElement = document.querySelector('input[name=fname]');
    const lastNameElement = document.querySelector('input[name=lname]');

    console.log(firstNameElement.value);
    console.log(lastNameElement.value);
}