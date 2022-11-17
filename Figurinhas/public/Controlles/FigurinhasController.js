const Figurinhas = require('../models/Figurinhas');
const Users = require('../models/User');

module.exports = {
    async index(req, res){
        const {user_id} = req.params

        const user = await Users.findByPk(user_id, {
            include: { association: 'Figurinhas'}
        })

        return res.json(user)

    },
    async store(req, res) {
        const { user_id } = req.params 
        const { name, figurinhas } = req.body

        const user = await Users.findByPk(user_id);

        if (!user) {
            return res.status(400).json({ error: "User not found" })
        }

        const figurinhasCriadas = await Figurinhas.create({
            name,
            figurinhas,
            user_id
        })

        return res.json(figurinhasCriadas)

    }
};