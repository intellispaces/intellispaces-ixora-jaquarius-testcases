package tech.intellispaces.ixora.testcases.rdb.query;

import tech.intellispaces.commons.action.Action;
import tech.intellispaces.commons.action.Actions;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.statement.MovableResultSet;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactory;
import tech.intellispaces.ixora.rdb.transaction.TransactionalAction;
import tech.intellispaces.ixora.rdb.transaction.Transactions;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

import static tech.intellispaces.ixora.testcases.rdb.query.QueryBookSql.SELECT_BOOK_COUNT;

/**
 * This testcase demonstrates querying from the database.
 */
@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesToDataGuide.class
})
public abstract class QueryBookCountTestcase5 {

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param transactionFactory the value of the module projection named 'transactionFactory' (transaction factory).
   * @param console the value of the module projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableTransactionFactory transactionFactory, @Inject MovableConsole console) {
    Action action = Actions.get(() -> {
      MovableResultSet rs = Transactions.current().query(SELECT_BOOK_COUNT);
      rs.next();
      console.print("Number books: ");
      console.println(rs.integer32Value("count"));
    });
    new TransactionalAction(transactionFactory, action).execute();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(QueryBookCountTestcase5.class, args).start();
  }
}
