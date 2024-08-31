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
    try {
        let newUser = new User({
            name,
            email,
            age
        })

        let user = await newUser.save();
        res.json(user)
    } catch (error) {
        res.status(500).send("Error while saving new user");
    }
})

router.put('/', async (req, res) => {

})

router.delete('/:id', async (req, res) => {
    let id = req.params.id
    const deleted = await User.deleteOne({_id : id})
    if (deleted.deletedCount > 0) {
        res.status(200).json({msg: 'User deleted'})
    } else {
        res.status(404).json({msg: 'User not found'})
    }
})

router.delete('/delete/:username', async (req, res) => {
    let username = req.params.username
    let user = await User.findOne({email: username});
    if (user) {
        res.status(200).json({msg: 'User deleted'})
    } else {
        res.status(404).json({msg: 'User not found'})
    }
})

module.exports = router;