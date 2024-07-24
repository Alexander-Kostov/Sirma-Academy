function getFibonacci() {
    let a = 0;
    let b = 1;
    
    return function() {
        let next = a + b;
        a = b;
        b = next;
        return a;
    };
}

let fibonacci = getFibonacci();
console.log(fibonacci()); 
console.log(fibonacci()); 
console.log(fibonacci()); 
console.log(fibonacci()); 
console.log(fibonacci()); 
console.log(fibonacci());
console.log(fibonacci()); 
console.log(fibonacci());
