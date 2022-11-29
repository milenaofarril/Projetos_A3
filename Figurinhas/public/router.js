const bodyParse = require('body-parser')
const express = require('express')
const router = express.Router()



const UserController = require('./Controlles/UserController')
const Figurinhas = require('./Controlles/FigurinhasController')

router.use(bodyParse.json())
router.use(express.json())
router.use(bodyParse.urlencoded({ extended: true }))


router.post('/users', UserController.store)
router.get('/users', UserController.checkToken, UserController.index)
router.post('/users/auth',UserController.login)
router.get('/users/:id', UserController.checkToken, UserController.auth)
router.delete('/users/:id', UserController.checkToken, UserController.delete)
router.put('/users/:id', UserController.checkToken, UserController.atualizar)


router.post('/users/:user_id/figurinhas', UserController.checkToken, Figurinhas.store)
router.get('/users/:user_id/figurinhas', UserController.checkToken, Figurinhas.index)
router.put('/users/:user_id/figurinhas', UserController.checkToken, Figurinhas.atualizar)

module.exports = router

