using Microsoft.EntityFrameworkCore;

namespace TransactionManagement.Models;

[PrimaryKey(nameof(TransactionId))]
public class Transaction
{
    public int TransactionId { get; set; }
    public double Amount { get; set; }
    public DateTime CreatedAt { get; set; }
    public Guid AccountId { get; set; }
}
