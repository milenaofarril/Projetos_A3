const express = require('express')
const bodyParse = require('body-parser')
const router = express.Router()


const UserController = require('./Controlles/UserController')
const Figurinhas = require('./Controlles/FigurinhasController')
const Users = require('./models/User')

router.post('/users', UserController.store)
router.get('/users', UserController.checkToken, UserController.index)
router.post('/users/auth', UserController.login)
router.get('/users/:id', UserController.checkToken, UserController.auth)
router.delete('/users/:id', UserController.checkToken, UserController.delete)


router.post('/users/:user_id/figurinhas',UserController.checkToken, Figurinhas.store)
router.get('/users/:user_id/figurinhas',UserController.checkToken, Figurinhas.index)


module.exports = router

