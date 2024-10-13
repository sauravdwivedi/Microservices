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
    public async void PostAccount(Account account)
    {
        _context.Accounts.Add(account);
        await _context.SaveChangesAsync();

        return;
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
}