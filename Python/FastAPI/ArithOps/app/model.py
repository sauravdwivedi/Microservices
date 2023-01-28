class ArithmeticOperation:
    def __init__(self, first_number, second_number, operation) -> None:
        self.first_number = first_number
        self.second_number = second_number
        self.operation = operation
        self.result = 0.0

    def arithmetic_operation(self) -> float:
        if self.operation == "ADD":
            self.result = self.first_number + self.second_number
        elif self.operation == "SUBTRACT":
            self.result = self.first_number - self.second_number
        elif self.operation == "MULTIPLY":
            self.result = self.first_number * self.second_number
        elif self.operation == "DIVIDE":
            try:
                self.result = self.first_number / self.second_number
            except Exception as e:
                raise ZeroDivisionError("Division by zero in not possible!")

        return self.result
