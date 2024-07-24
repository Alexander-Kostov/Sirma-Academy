function Person(name) {
    this.name = name;
  }
  
  function Employee(name, job) {
    Person.call(this, name);
    this.job = job;
  }
  
  const emp = new Employee('Alice', 'Engineer');
  console.log(emp);
  
  