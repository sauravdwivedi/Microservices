pub struct ArithmeticOperation {
    pub first_number: f32,
    pub second_number: f32,
    pub operation: String,
}

impl ArithmeticOperation {
    pub fn arithops(self: &mut ArithmeticOperation) -> f32 {
        let mut result: f32 = 0.0;
        if self.operation == "ADD" {
            result = self.first_number + self.second_number;
        } else if self.operation == "SUBTRACT" {
            result = self.first_number - self.second_number;
        } else if self.operation == "MULTIPLY" {
            result = self.first_number * self.second_number;
        } else if self.operation == "DIVIDE" {
            result = self.first_number / self.second_number;
        }

        return result;
    }
}
