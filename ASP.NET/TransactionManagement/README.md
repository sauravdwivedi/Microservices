<img src=pic.PNG alt="Swagger UI">

### Execution

```bash
gh repo clone sauravdwivedi/Microservices
cd Microservices && cd ASP.NET && cd TransactionManagement
# Create database schema
dotnet ef migrations add InitialModel
# Create database 
dotnet ef database update
# Run the web service
dotnet run
```

### Swagger UI

- http://localhost:5231/swagger/index.html

### Tutorials 

- https://learn.microsoft.com/en-us/aspnet/core/tutorials/first-web-api
- https://learn.microsoft.com/en-us/ef/core/modeling/relationships/one-to-many
  