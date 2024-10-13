using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TransactionManagement.Models;

namespace TransactionManagement.Controllers;

[ApiController]
[Route("api/[controller]")]
public class TransactionsController : ControllerBase
{
    private readonly DataBase _context = new DataBase();

    [HttpPost]
    public async Task<int> PostTransaction(TransactionSchema payload)
    {
        var account = await _context.Accounts.FindAsync(payload.AccountId);
        account.Balance += payload.Amount;
        var transaction = new Transaction();
        transaction.Amount = payload.Amount;
        transaction.CreatedAt = DateTime.Now;
        transaction.AccountId = payload.AccountId;
        _context.Transactions.Add(transaction);
        await _context.SaveChangesAsync();

        return 201;
    }

    [HttpGet("{accountId}")]
    public async Task<ActionResult<List<Transaction>>> GetTransactionByAccountId(Guid accountId)
    {
        var transaction = await _context.Transactions.Where(transaction => transaction.AccountId == accountId).ToListAsync();

        if (transaction == null)
        {
            return NotFound();
        }

        return transaction;
    }

    [HttpGet]
    public async Task<ActionResult<Transaction>> GetTransactionById(int id)
    {
        var transaction = await _context.Transactions.FindAsync(id);

        if (transaction == null)
        {
            return NotFound();
        }

        return transaction;
    }

    [HttpGet("all")]
    public async Task<ActionResult<IEnumerable<Transaction>>> GetAllTransactions()
    {
        return await _context.Transactions
            .ToListAsync();
    }
}
