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

- https://www.telerik.com/blogs/aspnet-core-basics-working-database
  
### Add package examples

```bash
dotnet tool install --global dotnet-ef
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.Sqlite
```