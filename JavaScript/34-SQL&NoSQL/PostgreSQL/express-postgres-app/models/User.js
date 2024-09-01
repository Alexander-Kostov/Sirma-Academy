const {sequelize} = require('../config/db')
const {DataTypes} = require('sequelize')

const User = sequelize.define('User', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true
    },
    age: {
        type: DataTypes.INTEGER,
        allowNull: false
    }
}, 
{
    freezeTableName: true,
    timestamps: false
}

)

module.exports = User;