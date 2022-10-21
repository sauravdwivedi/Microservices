/**
 * Application implements basic arithmetic operations such as "ADD", "SUBTRACT",
 * "MULTIPLY", and "DIVIDE" to be executed on an endpoint using POST method.
 *
 * Endpoint: /arithops
 *
 * Payload:
 * {
 *     "first_number": 100,
 * 	    "second_number": 200,
 * 	    "operation": "ADD"
 * }
 *
 * Response:
 * {
 * 	    "result": 300
 * }
 */

package main

import (
	"errors"
	"example/arithmetic-operations/docs"
	"net/http"

	"github.com/gin-gonic/gin"
	log "github.com/sirupsen/logrus"

	swaggerFiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

// Schema to unmarshal payload from JSON
// Fields must start with capital letter!
type payloadSchema struct {
	FirstNumber  float64 `json:"first_number,omitempty"`
	SecondNumber float64 `json:"second_number,omitempty"`
	// Operation must be one of 'ADD', 'SUBTRACT', 'MULTIPLY', and 'DIVIDE'"
	Operation string `json:"operation,omitempty"`
}

// Schema to marshal response to JSON
type responseSchema struct {
	Result float64 `json:"result"`
}

// @Summary endpoint to execute arithmetic operations
// @Produce json
// @Param data body payloadSchema true "Aithmetic Operation"
// @Success 200 {object} responseSchema
// @Failure 400 {object} responseSchema
// @Router /arithops [post]
func doArithOps(payoad payloadSchema) (result float64, message string, err error) {
	if payoad.Operation == "ADD" {
		result = payoad.FirstNumber + payoad.SecondNumber
	} else if payoad.Operation == "SUBTRACT" {
		result = payoad.FirstNumber - payoad.SecondNumber
	} else if payoad.Operation == "MULTIPLY" {
		result = payoad.FirstNumber * payoad.SecondNumber
	} else if payoad.Operation == "DIVIDE" {
		if payoad.SecondNumber != 0 {
			result = payoad.FirstNumber / payoad.SecondNumber
		} else {
			message = "Division by zero in not possible!"
			err = errors.New("Can't divide by zero")
			return
		}
	} else {
		message = "Invalid operation! Operation must be one of 'ADD', 'SUBTRACT', 'MULTIPLY', and 'DIVIDE'"
		err = errors.New("Operation not valid")
		return
	}
	return
}

// POST method at endpoint '/arithops'
func postArithOps(this *gin.Context) {
	var payoad payloadSchema
	var response responseSchema
	err := this.BindJSON(&payoad)
	if err != nil {
		log.Error("Malformed payload!")
		this.IndentedJSON(http.StatusBadRequest, "Malformed payload!")
	} else {
		var result, errMessage, err = doArithOps(payoad)
		response.Result = result
		if err != nil {
			log.Error("An error occurred during arithmetic operation: ", err.Error())
			this.IndentedJSON(http.StatusBadRequest, errMessage)
		} else {
			this.IndentedJSON(http.StatusOK, response)
		}
	}
}

func main() {
	// programmatically set swagger info
	docs.SwaggerInfo.Title = "Swagger ArithOps API"
	docs.SwaggerInfo.Description = "API implements basic arithmetic operations."
	docs.SwaggerInfo.Version = "1.0"
	docs.SwaggerInfo.Host = "localhost:8080"
	docs.SwaggerInfo.BasePath = ""
	docs.SwaggerInfo.Schemes = []string{"http", "https"}

	router := gin.Default()
	router.POST("/arithops", postArithOps)
	router.GET("/docs/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))
	router.Run("localhost:8080")
}
