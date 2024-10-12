using Microsoft.AspNetCore.Mvc;
using TransactionManagement.Models;

namespace TransactionManagement.Controllers;

[ApiController]
[Route("[controller]")]
public class AccountsController : ControllerBase
{
    [HttpGet(Name = "GetAccount")]
    public IEnumerable<Account> Get()
    {
        return Enumerable.Range(1, 5).Select(index => new Account
        {
            Id = Guid.NewGuid(),
            Balance = Random.Shared.Next(1000000, 10000000),
        })
        .ToArray();
    }
}
