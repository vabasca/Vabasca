const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    listID     : { type: Number, required: true },
    description: { type: String, required: true },
    reminder   : { type: String, required: true },
    dateTime   : { type: Date  , default : null },
});

schema.set('toJSON', { virtuals: true });

module.exports = mongoose.model('Item', schema);