const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    userID     : { type: Number, required: true },
    listTypeID : { type: Number, required: true },
    description: { type: String, default : null }
});

schema.set('toJSON', { virtuals: true });

module.exports = mongoose.model('List', schema);