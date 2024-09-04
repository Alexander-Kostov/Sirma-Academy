import { auth } from "../firebase-config.js";

import {
    createUserWithEmailAndPassword, 
    signInWithEmailAndPassword,
    signOut
} from "https://www.gstatic.com/firebasejs/10.13.1/firebase-auth.js";

const handleRegister = async () => {
    const email = document.getElementById("email").value
    const password = document.getElementById("password").value

    try {
        await createUserWithEmailAndPassword(auth, email, password)
    }catch (error) {

    }
}

const regBtn = document.getElementById("register");
regBtn.addEventListener("click", handleRegister);



const loginBtn = document.getElementById("login");
const handleLogin = async () => {
    const email = document.getElementById("email").value
    const password = document.getElementById("password").value

    try {
        await signInWithEmailAndPassword(auth, email, password)
    } catch (error) {
        console.log(error.message)
    }
}

loginBtn.addEventListener("click", handleLogin);
