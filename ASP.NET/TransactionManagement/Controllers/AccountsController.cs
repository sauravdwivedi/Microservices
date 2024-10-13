using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TransactionManagement.Models;

namespace TransactionManagement.Controllers;

[ApiController]
[Route("api/[controller]")]
public class AccountsController : ControllerBase
{
    private readonly DataBase _context = new DataBase();

    [HttpPost]
    public async Task<int> PostAccount(Account account)
    {
        var accounts = await _context.Accounts.FindAsync(account.Id);

        if (accounts == null)
        {
            _context.Accounts.Add(account);
            await _context.SaveChangesAsync();
        }
        else
        {
            return 400;
        }

        return 201;
    }

    [HttpGet]
    public async Task<ActionResult<Account>> GetAccountById(Guid id)
    {
        var account = await _context.Accounts.FindAsync(id);

        if (account == null)
        {
            return NotFound();
        }

        return account;
    }

    [HttpGet("all")]
    public async Task<ActionResult<IEnumerable<Account>>> GetAllAccounts()
    {
        return await _context.Accounts
            .ToListAsync();
    }
}
