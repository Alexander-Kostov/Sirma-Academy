import { addOrder, getOrder, deleteOrder } from "./firestore.js";

const addOrderBtn = document.getElementById("add-order")
const getOrderBtn = document.getElementById("get-order")
const deleteOrderBtn = document.getElementById("delete-order")

const handleAddOrder = () => {
    const order = document.getElementById("order-value").value.trim();
    addOrder(order)
    console.log(order)
}

const handleGetOrder = () => {
    const order = document.getElementById("order-value").value.trim();
    getOrder(order)
    console.log(order)
}

const handleDeleteOrder = () => {
    const id = document.getElementById("order-value").value.trim();
    deleteOrder(id)
}

addOrderBtn.addEventListener("click", handleAddOrder)
getOrderBtn.addEventListener("click", handleGetOrder)
deleteOrderBtn.addEventListener("click", handleDeleteOrder)