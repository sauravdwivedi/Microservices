namespace TransactionManagement.Models;

public class Account
{
    public Guid Id { get; set; }
    public double Balance { get; set; }

    public ICollection<Transaction>? Transactions { get; }
}
