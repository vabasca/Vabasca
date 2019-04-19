const bcrypt = require('bcryptjs');
const db = require('_helper/db');
const List = db.List;
const Item = db.Item;
const User = db.User;

module.exports = {
    getById,
    getByType,
    create,
    update,
    share,
    delete: _delete
};

async function getById(id) {
    return await Item.find({listID: id}).select('*');
}

async function getByType(listType) {
    return await List.find({listTypeID: listType.listTypeID, userID: listType.userID}).select('*');
}

async function create(listType) {
    // validate
    if (await List.findOne({listTypeID: listType.listTypeID, userID: listType.userID})) {
        if (listType.listTypeID < 5)
            throw 'This Type CANNOT Have More Than One List!';
    }

    const list = new List(listType);

    // save list
    await list.save();
}

async function update(id, listType) {
    const list = await List.findById(id);

    // validate
    if (!list) throw 'User not found';

    // copy listType properties to list
    Object.assign(list, listType);

    await list.save();
}

async function share(id, reqParam) {
    let testAccount = await nodemailer.createTestAccount();

    // create reusable transporter object using the default SMTP transport
    let transporter = nodemailer.createTransport({
        host: "smtp.ethereal.email",
        port: 587,
        secure: false, // true for 465, false for other ports
        auth: {
            user: testAccount.user, // generated ethereal user
            pass: testAccount.pass // generated ethereal password
        }
    });

    let userEmail = await User.findById(id).select('Email');

    let items = getById(id);

    // send mail with defined transport object
    let info = await transporter.sendMail({
        from: userEmail, // sender address
        to: reqParam.email, // list of receivers
        subject: "Grocery Items", // Subject line
        text: items, // plain text body
        // html: "<b>Hello world?</b>" // html body
    });

    console.log("Message sent: %s", info.messageId);
    // Message sent: <b658f8ca-6296-ccf4-8306-87d57a0b4321@example.com>

    // Preview only available when sending through an Ethereal account
    console.log("Preview URL: %s", nodemailer.getTestMessageUrl(info));
    // Preview URL: https://ethereal.email/message/WaQKMgKddxQDoou...
}

async function _delete(id, listType) {
    if (await List.findOne({listTypeID: listType.listTypeID, userID: listType.userID})) {
        await Item.remove({listID: id});

        if (listType.listTypeID >= 5)
            await List.findByIdAndRemove(id);
    }
}