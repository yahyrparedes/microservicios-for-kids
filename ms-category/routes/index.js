var express = require('express');
var router = express.Router();

const db = [
    {
        id: 1,
        name: 'Polo',
        description: 'Los mejores polos'
    },
    {
        id: 2,
        name: 'Camisa',
        description: 'Las mejores camisas'
    },
    {
        id: 3,
        name: 'Pantalon',
        description: 'Los mejores pantalones'
    },
    {
        id: 4,
        name: 'Poleras',
        description: 'Las mejor poleras'
    }
]


/* GET ping. */
router.get('/ping', function (req, res, next) {
    res.json({status: 'ok', ms: 'ms-category'})
})

router.get('/categories', function (req, res, next) {
    res.json(db)
})

router.get('/categories/:id', function (req, res, next) {
    const cat = db.find(r => r.id == req.params.id)
    res.json(cat);
})


module.exports = router;
