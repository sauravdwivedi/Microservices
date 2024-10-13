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

        if (account != null)
        {
            account.Balance += payload.Amount;
            var transaction = new Transaction();
            transaction.Amount = payload.Amount;
            transaction.CreatedAt = DateTime.Now;
            transaction.AccountId = payload.AccountId;
            _context.Transactions.Add(transaction);
            await _context.SaveChangesAsync();
        }
        else
        {
            return 400;
        }

        return 201;
    }

    [HttpGet("{accountId}")]
    public async Task<ActionResult<List<Transaction>>> GetTransactionsByAccountId(Guid accountId)
    {
        var transactions = await _context.Transactions.Where(transaction => transaction.AccountId == accountId).ToListAsync();

        if (transactions == null)
        {
            return NotFound();
        }

        return transactions;
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
