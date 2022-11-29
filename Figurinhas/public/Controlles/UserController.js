const User = require('../models/User');
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
require('dotenv').config()

module.exports = {

    async index(req, res) {
        const user = await User.findAll();
        return res.status(200).json({ user })
    },

    async store(req, res) {
        const { name, email, password } = req.body
        const error = []

        if (!name) {
            error.push('O nome é obrigatório!')
        }

        if (!email) {
            error.push('O email é obrigatório!')
        }

        if (!password) {
            error.push('A senha é obrigatório!')
        }

        if (error.length > 0) {
            return res.status(400).json({ msg: { erros: error } })
        }

        const userExists = await User.findOne({ where: { email } });

        if (userExists) {
            return res.status(422).json({ msg: "Por favor, utilize outro e-mail!" });
        }

        const salt = await bcrypt.genSalt(12);
        const passwordHash = await bcrypt.hash(password, salt);

        const user = await User.create({ name, email, password: passwordHash });

        try {
            await user.save()
            return res.status(201).json({ msg: "Usuário criado com sucesso!" })
        } catch (error) {
            return res.status(500).json(error.message)
        }

    },

    async login(req, res) {
        const { email, password } = req.body
            

        if (!email) {
            return res.status(422).json({ msg: "O email é obrigatório!" });
        }

        if (!password) {
            return res.status(422).json({ msg: "A senha é obrigatória!" });
        }

        const user = await User.findOne({ where: { email } });

        if (!user) {
            return res.status(404).json({ msg: "Usuário não encontrado!" });
        }

        const checkPassword = await bcrypt.compare(password, user.password);

        if (!checkPassword) {
            return res.status(422).json({ msg: "Senha inválida" });
        }

        try {
            const secret = process.env.SECRET_KEY

            const token = jwt.sign(
                {
                    id: user.id
                },
                secret
            );

            res.status(200).json({ msg: "Autenticação realizada com sucesso!", token: token });
        } catch (error) {
            res.status(500).json({ msg: error });
        }
    },

    async auth(req, res) {
        const id = req.params.id;

        const user = await User.findAll({
            where: { id },
            attributes: { exclude: ['password'] }
        });

        if (!user) {
            return res.status(404).json({ msg: "Usuário não encontrado!" });
        }

        res.status(200).json({ user });

    },

    async checkToken(req, res, next) {
        const authHeader = req.headers["authorization"];
        const token = authHeader && authHeader.split(" ")[1];

        if (!token) return res.status(401).json({ msg: "Acesso negado!" });

        try {
            const secret = process.env.SECRET_KEY;

            jwt.verify(token, secret);

            next();
        } catch (err) {
            res.status(400).json({ msg: "O Token é inválido!" });
        }

    },

    async delete(req, res) {
        const id = req.params.id;

        const user = await User.findOne({ where: { id } });

        if (!user) {
            return res.status(404).json({ msg: "Usuário não encontrado!" });
        }

        User.destroy({ where: { id } })

        try {
            res.status(200).json({ msg: "Usuário deletado com sucesso!" });

        } catch (error) {
            res.status(500).json({ msg: error });
        }

    },
    async atualizar(req, res) {
        try {
            const id = req.params.id;
            const { name, password } = req.body;

            const user = await User.findOne({
                where: { id }
            });

            if (!user) {
                return res.status(404).json({ msg: "Usuário não encontrado!" });
            } else {
                const userUpdate = User.update({ name, password }, { where: { id } })
                return res.status(200).json({ msg: "Atualizado com sucesso" })
            }

        } catch (error) {
            res.status(500).json({ msg: error });
        }

    }
}