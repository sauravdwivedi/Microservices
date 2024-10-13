using Microsoft.EntityFrameworkCore;

namespace TransactionManagement.Models;

public class DataBase : DbContext
{
    protected override void OnConfiguring(DbContextOptionsBuilder options) =>
       options.UseSqlite("DataSource=transaction_management.sqlite; Cache=Shared");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Account>()
            .HasMany<Transaction>()
            .WithOne();
    }
    public DbSet<Account> Accounts { get; set; }
    public DbSet<Transaction> Transactions { get; set; }
}