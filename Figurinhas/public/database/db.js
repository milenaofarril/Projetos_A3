const { Sequelize } = require('sequelize')
const dbConfig = require('../config/config')

const User = require('../models/User')
const Figurinhas = require('../models/Figurinhas')
const Users = require('../models/User')


const connection = new Sequelize(dbConfig.development)

User.init(connection)
Figurinhas.init(connection)

Figurinhas.associate(connection.models);
Users.associate(connection.models)


module.exports = connection