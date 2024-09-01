const express = require('express');
const router = express.Router();
const User = require('../models/User.js')

router.get('/', async (req, res) => {
    try {
        let users = await User.findAll();
        res.status(200).json(users);
        console.log(res.body)
        
    } catch (error) {
        res.status(500).send("Error while retrieving users");
    }
});

router.post('/', async (req, res) => {
    const {name, email, age} = req.body;
    try {
        const user = await User.create({
            name, email, age
        })

        res.json(user)
    } catch (error) {
        res.status(500).send(error.errors[0].message);
    }
})

router.put('/', async (req, res) => {

})

router.delete('/:id', async (req, res) => {
    let id = req.params.id
    let user = await User.findByPk(id)

    if (!user) {
        res.status(404).json({msg: 'User not found'})
    } else {
        await user.destroy();
        res.status(200).json({msg: 'User deleted'})
    }   
})

router.delete('/delete/:email', async (req, res) => {
    let email = req.params.email
    let user = await User.findOne({where: {email: email} });
    if (user) {
        user.destroy()
        res.status(200).json({msg: 'User deleted'})
    } else {
        res.status(404).json({msg: 'User not found'})
    }
})

module.exports = router;