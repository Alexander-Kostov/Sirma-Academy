const stringManipulator = {
    value: '',

    setValue: function (newValue) {
        this.value = newValue;
        return this;
    },

    toUpperCase: function () {
        this.value = this.value.toUpperCase();
        return this;
    },

    toLowerCase: function () {
        this.value = this.value.toLowerCase();
        return this;
    },

    print: function () {
        console.log(this.value);
        return this;
    }
};


stringManipulator.setValue('Hello')
    .toUpperCase()
    .print()
    .toLowerCase()
    .print();
