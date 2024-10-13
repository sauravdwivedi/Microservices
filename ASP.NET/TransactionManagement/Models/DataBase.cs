using Microsoft.EntityFrameworkCore;

namespace TransactionManagement.Models;

public class DataBase : DbContext
{
    protected override void OnConfiguring(DbContextOptionsBuilder options) =>
       options.UseSqlite("DataSource=transaction_management.sqlite; Cache=Shared");

    public DbSet<Account> Accounts { get; set; }
}