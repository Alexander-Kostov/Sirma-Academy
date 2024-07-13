function changeContent(row, col, content) {
    const table = document.getElementById('myTable');
    
    table.rows[row].cells[col].innerHTML = content;
}

document.querySelector("input[type=button]").addEventListener("click", function() {
    changeContent(1, 1, "Updated content");
});