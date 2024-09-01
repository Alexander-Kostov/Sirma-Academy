const {Sequelize} = require('sequelize')
const sequelize = new Sequelize('express-postgres-app', 'postgres', 'root1234', {
    host: 'localhost',
    dialect: 'postgres'
})

const connectDB = async () => {
    try {
        await sequelize.authenticate();
        // await User.sync()
        console.log('Postgres db connected')
    } catch(error) {
        console.log("Error while connecting" + error.message)
    }
}

module.exports={sequelize, connectDB};
