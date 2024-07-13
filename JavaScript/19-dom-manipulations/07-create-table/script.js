function createTable(rows, cols) {
    const table = document.querySelector('#myTable');
    const tbody = document.createElement('tbody');
    table.appendChild(tbody)

    for (let i = 0; i < rows; i++) {
        const row = document.createElement('tr');
        
        for (let j = 0; j < cols; j++) {
            const data = document.createElement('td');
            data.innerText = `Row-${i+1} Col-${j+1}`;
            row.appendChild(data);
        }

        tbody.appendChild(row);
    }

}

document.querySelector('input[type=button]').addEventListener('click', function() {
    createTable(2, 2);
});