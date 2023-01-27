var express = require('express');
var router = express.Router();
var firstNumber = 0.0;
var secondNumber = 0.0;
var result = 0.0;
var operation = '+';

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { 
    firstNumber: firstNumber, 
    secondNumber: secondNumber, 
    operation: operation, 
    result: result
  });
});

router.post('/api/v1/arithops', function(req, res, next) {
  try{
    firstNumber = req.body.first_number;
    secondNumber = req.body.second_number;
    operation = req.body.operation;

    if (operation == 'ADD') {
      result = firstNumber + secondNumber;
      operation = '+'
    } else if (operation == 'SUBTRACT') {
      result = firstNumber - secondNumber;
      operation = '-'
    } else if (operation == 'MULTIPLY') {
      result = firstNumber * secondNumber;
      operation = '*'
    } else if (operation == 'DIVIDE') {
      if (secondNumber != 0) {
        result = firstNumber / secondNumber;
        operation = '/'
      } else {
        throw new Error('Division by zero in not possible!');
      }
    } else {
      throw new Error(`Invalid operation! Operation must be one of 'ADD', 'SUBTRACT', 'MULTIPLY', and 'DIVIDE'`)
    }

    res.json({'result': result})
  } catch (err) {
    console.error(err.message);
    res.json(err.message)
  } 
});

module.exports = router;
