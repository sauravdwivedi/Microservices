var express = require('express');
var router = express.Router();
var ArithmeticOperationModel = require('../models/arithops');

arithOps = new ArithmeticOperationModel();

router.post('/arithops', function(req, res, next) {
  arithOps.firstNumber = req.body.first_number;
  arithOps.secondNumber = req.body.second_number;
  arithOps.operation = req.body.operation;
  arithOps.doArithOps();
  let result = arithOps.result;
  res.json({'result': result});
});

module.exports = router;
module.exports.arithOps = arithOps;
