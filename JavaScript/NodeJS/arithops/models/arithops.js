class ArithmeticOperation {
  constructor() {
    this.firstNumber = 0.0;
    this.secondNumber = 0.0;
    this.operation = '+';
    this.result = 0.0;
  }

  doArithOps() {
    try {
      if (this.operation == 'ADD') {
        this.result = this.firstNumber + this.secondNumber;
        this.operation = '+';
      } else if (this.operation == 'SUBTRACT') {
        this.result = this.firstNumber - this.secondNumber;
        this.operation = '-';
      } else if (this.operation == 'MULTIPLY') {
        this.result = this.firstNumber * this.secondNumber;
        this.operation = '*';
      } else if (this.operation == 'DIVIDE') {
        this.operation = '/';
        if (this.secondNumber != 0) {
          this.result = this.firstNumber / this.secondNumber;
        } else {
          throw new Error('Division by zero in not possible!');
        }
      } else {
        throw new Error(`Invalid operation! Operation must be one of 'ADD', 'SUBTRACT', 'MULTIPLY', and 'DIVIDE'`);
      }
    } catch (err) {
      console.error(err.message);
      this.result = err.message;
    }
  }
}

module.exports = ArithmeticOperation;