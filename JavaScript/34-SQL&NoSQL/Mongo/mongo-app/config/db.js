const mongoose = require('mongoose');

const connectDB = async () => {
    try {
        await mongoose.connect('mongodb://127.0.0.1:27017/mongo-app')

        console.log('Mongo db connected')
    } catch(error) {
        console.log("Error while connecting" + error.message)
    }
}

module.exports=connectDB;
