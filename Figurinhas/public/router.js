const express = require('express')
const bodyParse = require('body-parser')
const router = express.Router()


const UserController = require('./Controlles/UserController')
const Figurinhas = require('./Controlles/FigurinhasController')
const Users = require('./models/User')

router.post('/users', UserController.store)
router.get('/users', UserController.index)

router.post('/users/:user_id/figurinhas', Figurinhas.store)
router.get('/users/:user_id/figurinhas', Figurinhas.index)


module.exports = router

