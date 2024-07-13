function insertRow() {
    const tableElement = document.querySelector('tbody');

    const rowCount = tableElement.children.length;

    const firstTableDataElement = document.createElement('td');
    firstTableDataElement.innerText = `Row${rowCount + 1} cell1`

    const secondTableDataElement = document.createElement('td');
    secondTableDataElement.innerText = `Row${rowCount + 1} cell2`

    const tableRowElement = document.createElement('tr');
    tableRowElement.append(firstTableDataElement);
    tableRowElement.append(secondTableDataElement);
    
    tableElement.append(tableRowElement);

}