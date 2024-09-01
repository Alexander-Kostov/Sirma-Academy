const express = require('express');
const { connectDB, sequelize } = require('./config/db')
const bodyParser = require('body-parser')
const router = require('./route/users.js')
const User = require('./models/User.js')

const app = express();
connectDB();

sequelize.sync()
.then((result) => {
    console.log(result)
})
.catch((err) => {
    console.log(err)
})
app.use(bodyParser.json())

app.use('/api/users', router)

const PORT = process.env.port || 5001;

app.listen(PORT, () => {
    console.log(`Server started on port ${PORT}`)
})