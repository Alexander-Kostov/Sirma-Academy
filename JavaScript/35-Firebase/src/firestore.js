import { db, auth } from "../firebase-config.js";
import {
    collection,
    addDoc,
    deleteDoc,
    setDoc,
    getDocs,
    doc,
    where,
    query,
} from "https://www.gstatic.com/firebasejs/10.13.1/firebase-firestore.js";

const ordersCollection = collection(db, "orders");

export const addOrder = async (order) => {
    try {
        const user = auth.currentUser;
        console.log(user)
        if (user) {
            var orderDoc = await addDoc(ordersCollection, {
                order,
                userId: user.uid,
                created: new Date()
            })

            // order.get().then((doc) => {
            //     if (doc.exists) {

            //         console.log("Document data: ", doc.data())
            //     } else {
            //         console.log("No such document")
            //     }
            // })
            console.log("Order created", orderDoc)
        }
    } catch (error) {
        console.log(error)
    }
}

export const getOrder = async (order) => {
    const q = query(ordersCollection, where("order", "==", order))
    const querySnapshot = await getDocs(q)

   querySnapshot.forEach((doc) => {
    console.log(doc.id, " => ", doc.data())
   })

}

export const addOrderBuilderPattern = async (order) => {
    const orderDocRef = ordersCollection.doc();
    console.log()
    const user = auth.currentUser;
    const data = {
        order,
        userId: user.uid,
        created: new Date()
    }
    orderDocRef.set(data)
}

export const deleteOrder = async (id) => {
    await deleteDoc(doc(db, "orders", id))
}