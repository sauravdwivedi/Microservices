using Microsoft.AspNetCore.Mvc;
using TransactionManagement.Models;

namespace TransactionManagement.Controllers;

[ApiController]
[Route("[controller]")]
public class AccountsController : ControllerBase
{
    [HttpGet("{path}")]
    public IEnumerable<Account> GetAll(string path)
    {
        path = "all";

        return Enumerable.Range(1, 5).Select(index => new Account
        {
            Id = Guid.NewGuid(),
            Balance = Random.Shared.Next(1000000, 10000000),
        })
        .ToArray();
    }

    [HttpGet()]
    public ActionResult<Account> GetById(Guid id)
    {
        var account = new Account
        {
            Id = id,
            Balance = Random.Shared.Next(1000000, 10000000),
        };

        if (account == null)
            return NotFound();

        return account;
    }
}
