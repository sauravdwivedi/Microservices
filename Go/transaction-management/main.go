package main

import (
	"net/http"

	chi "github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/go-chi/render"
)

var ErrBadRequest = &ErrResponse{HTTPStatusCode: 400, StatusText: "Bad request"}

type ErrResponse struct {
	Err            error `json:"-"` // low-level runtime error
	HTTPStatusCode int   `json:"-"` // http response status code

	StatusText string `json:"status"`          // user-level status message
	AppCode    int64  `json:"code,omitempty"`  // application-specific error code
	ErrorText  string `json:"error,omitempty"` // application-level error message, for debugging
}

func (e *ErrResponse) Render(w http.ResponseWriter, r *http.Request) error {
	render.Status(r, e.HTTPStatusCode)
	return nil
}

type requestSchema struct {
	AccountId int     `json:"account_id,omitempty"`
	Balance   float64 `json:"balance,omitempty"`
}

type responseSchema struct {
	AccountId int     `json:"account_id,omitempty"`
	Balance   float64 `json:"balance,omitempty"`
	Message   string  `json:"message"`
}

func (payload *requestSchema) Bind(r *http.Request) error {
	return nil
}

func (response *responseSchema) Render(w http.ResponseWriter, r *http.Request) error {
	return nil
}

func openAccount(w http.ResponseWriter, r *http.Request) {
	payload := &requestSchema{}
	if err := render.Bind(r, payload); err != nil {
		render.Render(w, r, ErrBadRequest)
		return
	}
	response := &responseSchema{
		AccountId: payload.AccountId,
		Balance:   payload.Balance,
		Message:   "The account has been created",
	}
	render.Status(r, http.StatusCreated)
	render.Render(w, r, response)
}

func main() {
	router := chi.NewRouter()
	router.Use(middleware.RequestID)
	router.Use(middleware.Logger)
	router.Use(middleware.Recoverer)
	router.Post("/accounts", openAccount)
	http.ListenAndServe(":3333", router)
}
