const express = require('express');
const router = express.Router();
const listService = require('./list.service');
const nodemailer = require("nodemailer");

// routes
router.get('/:id', getById);
router.get('/list', getByType);
router.post('/create', create);
router.put('/:id', update);
router.post('/share', share);
router.delete('/:id', _delete);

module.exports = router;

function getById(req, res, next) {
    listService.getById(req.params.id)
        .then(items => res.json(items))
        .catch(err => next(err));
}

function getByType(req, res, next) {
    listService.getByType(req.body)
        .then(lists => res.json(lists))
        .catch(err => next(err));
}

function create(req, res, next) {
    listService.create(req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}

function update(req, res, next) {
    listService.update(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}

function share(req, res, next) {
    listService.share(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}

function _delete(req, res, next) {
    listService.delete(req.params.id, req.body)
        .then(() => res.json({}))
        .catch(err => next(err));
}