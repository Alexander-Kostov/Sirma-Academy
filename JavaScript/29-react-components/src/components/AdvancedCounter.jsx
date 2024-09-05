import { useState } from "react";

export default function AdvancedCounter() {
    const [count, setCount] = useState(0);

    const clickHandler = () => {
        setCount(count + 1);
    }

    const clearCount = () => {
        setCount(0);
    }

    const decrementCount = () => {
        setCount(count - 1);
    }

    if (count > 15) {
        return <h2 onClick={clearCount}>Game Over</h2>
    }

    let countingNumber;

    switch (count) {
        case 0:
            countingNumber = <em>Begin</em>;
            break;
        case 1:
            countingNumber = <em>One</em>;
            break;
        case 2:
            countingNumber = <em>Two</em>;
            break;
        case 3:
            countingNumber = <em>Three</em>;
            break;
    }
    return (
        <>
            <h2>Advanced Counter</h2>
            {countingNumber}
            <div>{count}</div>
            <button onClick={clickHandler}>increment</button>
            {count !== 0 
            ? <button onClick={clearCount}>Reset</button> 
            : null}

            {count > 0 && <button onClick={decrementCount}>Decrement</button>}
        </>
    );
}