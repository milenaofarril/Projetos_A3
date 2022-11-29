const path = require('path')

module.exports = {
  "development": {
    "username": "root",
    "password": "admin",
    "database": "Figurinhas",
    "host": "127.0.0.1",
    "dialect": "mysql",
    "migration-path": path.resolve(__dirname, 'migrations')
  }
}