const express = require('express');
const router = express.Router();
const User = require('../models/User.js')

router.get('/', async (req, res) => {
    try {
        let users = await User.find();
        res.status(200).json(users);
        console.log(res.body)
        
    } catch (error) {
        res.status(500).send("Error while retrieving users");
    }
});

router.post('/', async (req, res) => {
    const {name, email, age} = req.body;
    console.log(req.body)
    try {
        let newUser = new User({
            name,
            email,
            age
        })

        let user = await newUser.save();
        res.json(user)
    } catch (error) {
        res.status(500).send("Error while saving new user").send("Error while saving user" + error.message)
    }
})

router.put('/', async (req, res) => {

})

router.delete('/delete/:id', async (req, res) => {

})

router.delete('/delete/:username', async (req, res) => {

})

module.exports = router;