var express = require('express');
var router = express.Router();
var api = require('./api');

/* GET home page. */
router.get('/', function (req, res, next) {
  let firstNumber = api.arithOps.firstNumber;
  let secondNumber = api.arithOps.secondNumber;
  let operation = api.arithOps.operation;
  let result = api.arithOps.result;

  res.render('index', {
    firstNumber: firstNumber,
    secondNumber: secondNumber,
    operation: operation,
    result: result
  });
});

module.exports = router;
