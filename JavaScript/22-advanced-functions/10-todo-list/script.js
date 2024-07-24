document.getElementById('add-button').addEventListener('click', function() {
    const input = document.getElementById('todo-input');
    const task = input.value.trim();
  
    if (task !== "") {
      const li = document.createElement('li');
      li.textContent = task;
  
      const removeButton = document.createElement('button');
      removeButton.textContent = 'Remove';
      removeButton.classList.add('remove-button');
  
      removeButton.addEventListener('click', function() {
        li.remove();
      });
  
      li.appendChild(removeButton);
  
      document.getElementById('todo-list').appendChild(li);
  
      input.value = '';
    }
  });