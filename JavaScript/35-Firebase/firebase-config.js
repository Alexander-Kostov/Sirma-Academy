// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.13.1/firebase-app.js";
import { getFirestore } from "https://www.gstatic.com/firebasejs/10.13.1/firebase-firestore.js"
import { getAuth, signInAnonymously } from "https://www.gstatic.com/firebasejs/10.13/firebase-auth.js"



// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyC2k3D_OWbqWvlaegb2D7FYep_thWPNcB4",
    authDomain: "sirma-academy.firebaseapp.com",
    projectId: "sirma-academy",
    storageBucket: "sirma-academy.appspot.com",
    messagingSenderId: "708309779414",
    appId: "1:708309779414:web:51155115377c7e54f8da77",
    measurementId: "G-CZN7GBXVED"
};

// Initialize Firebase
const app = await initializeApp(firebaseConfig);
const auth = getAuth(app);
signInAnonymously(auth)
    .then(() => {
        console.log("works")
    })
    .catch((error) => {
        console.log(error.message)
    })
const db = getFirestore(app)


export { auth, db }