package tech.intellispaces.ixora.testcases.http.simple.testcase2;

import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuideImpl;
import tech.intellispaces.ixora.internet.uri.JoinBasePathStringWithEndpointStringGuideImpl;
import tech.intellispaces.ixora.internet.uri.SplitUriPathStringToPartsGuideImpl;
import tech.intellispaces.ixora.jetty.JettyServerPorts;
import tech.intellispaces.ixora.testcases.http.simple.AbstractSimpleHttpSample;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    JoinBasePathStringWithEndpointStringGuideImpl.class,
    SplitUriPathStringToPartsGuideImpl.class,
    GetUriQueryParamGuideImpl.class,
    CliConfiguration.class,
    SimpleHttpPortGuideImpl.class
})
public class SimpleHttpSample2 extends AbstractSimpleHttpSample {

  @Override
  protected MovableInboundHttpPortHandle getInboundPort(int portNumber) {
    MovableInboundHttpPortHandle operativePort = JettyServerPorts.get(
        portNumber, SimplePortExchangeChannel.class
    ).asInboundHttpPort();
    MovableSimpleHttpPortHandle logicalPort = SimpleHttpPorts.getAndLink(operativePort);
    return logicalPort.asInboundHttpPort();
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.load(SimpleHttpSample2.class, args).start();
  }
}
