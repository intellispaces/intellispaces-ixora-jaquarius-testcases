package tech.intellispaces.ixora.testcases.rdb.fetch;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactory;
import tech.intellispaces.ixora.rdb.transaction.TransactionFunctions;
import tech.intellispaces.ixora.rdb.transaction.Transactions;
import tech.intellispaces.ixora.testcases.rdb.BookCrudGuide;
import tech.intellispaces.ixora.testcases.rdb.Book;
import tech.intellispaces.ixora.testcases.rdb.DefaultBookCrudGuide;
import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

/**
 * This testcase demonstrates getting a persisted entity from the database.
 * <p>
 * The CRUD auto guide is used for this. The specific guide will be selected automatically.
 */
@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookTestcase3 {

  /**
   * The book CRUD auto guide.
   */
  @Inject
  @AutoGuide
  abstract BookCrudGuide bookCrudGuide();

  /**
   * This method returns module projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableTransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The transaction factory {@link #transactionFactory()} is used to create a transaction.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the module projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    MovableTransactionFactory transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      int bookId = 2;
      Book book = bookCrudGuide().getById(tx, bookId);

      console.print("Book title: ");
      console.println(book.title());

      console.print("Book author: ");
      console.println(book.author());
    });
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(FetchBookTestcase3.class, args).start();
  }
}
