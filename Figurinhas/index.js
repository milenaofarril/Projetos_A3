const express = require('express')
const bodyParse = require('body-parser')
const router = require('./public/router')

require('./public/database/db')


const app = express()

app.set('view engine', 'ejs')
app.set('views', './public/views')
app.use(express.static('public'))
app.use('/css', express.static(__dirname + './public/css'))
app.use(bodyParse.json())
app.use(express.json())
app.use(router)

app.get('/album', (req, res) => {
    res.render('album')
})

app.get('/login', (req, res) => {
    res.render('login')
})
app.get('/', (req, res) => {
    res.redirect('/login')
})
app.get('/cadastro', (req, res) => {
    res.render('cadastro')
})

app.get('/home', (req, res) => {
    res.render('home')
})


app.listen(3000, () => {
    console.log("rodando...")
})
