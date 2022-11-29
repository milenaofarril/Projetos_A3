const { Model, DataTypes } = require('sequelize');
const Users = require('./User');

class Figurinhas extends Model {
    static init(sequelize) {
        super.init({
            name: DataTypes.STRING,
            figurinhas: DataTypes.INTEGER
        }, {
            sequelize
        })
    }

    static associate(models) {
        this.belongsTo(models.Users, { foreignKey: 'user_id', as: 'Users' });
    }
}

module.exports = Figurinhas