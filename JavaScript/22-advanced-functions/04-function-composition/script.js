function double(num) {
    return num * 2;
}

function square(num) {
    return num * num;
}

function compose(double, square) {
    return function (value) {
        return square(double(value));
    };
}

const doubleThenSquare = compose(double, square);
console.log(doubleThenSquare(3))