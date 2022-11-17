const { Model, DataTypes } = require('sequelize')

class Users extends Model {
    static init(sequelize) {
        super.init({
            name: DataTypes.STRING,
            email: DataTypes.STRING,
            password: DataTypes.STRING
        }, {
            sequelize
        })
    }

    static associate(models) {
        this.hasMany(models.Figurinhas, { foreignKey: 'user_id', as: 'Figurinhas' });
    }
}

module.exports = Users