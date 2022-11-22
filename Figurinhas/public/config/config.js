const path = require('path')
require('dotenv').config()

module.exports = {
  "development": {
    "username": process.env.USERNAME_BD,
    "password": process.env.PASSWORD_BD,
    "database": process.env.NOME_BANCO,
    "host": "127.0.0.1",
    "dialect": "mysql",
    "migration-path": path.resolve(__dirname, 'migrations')
  }
}
