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
        _context.Accounts.Add(account);
        await _context.SaveChangesAsync();

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
