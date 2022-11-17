const User = require('../models/User');

module.exports = {

    async index(req, res) {
        const user = await User.findAll();

        return res.json(user);
    },

    async store(req, res) {
        const { name, email, password } = req.body
        try {
            const user = await User.create({ name, email, password });
            return res.status(201).json({ user })
        } catch (error) {
            return res.status(500).json(error.message)
        }

    }
}