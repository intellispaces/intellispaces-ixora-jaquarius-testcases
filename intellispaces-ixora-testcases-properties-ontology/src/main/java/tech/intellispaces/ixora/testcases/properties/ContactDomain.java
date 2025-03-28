package tech.intellispaces.ixora.testcases.properties;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Dataset;
import tech.intellispaces.jaquarius.annotation.Domain;

@Dataset
@Domain("09abe89e-1528-41ff-9d7d-3ef93d03e3c4")
public interface ContactDomain {

  @Channel("3e90ff80-f11a-46c4-949d-0c5904b89edb")
  int home();

  @Channel("fbba4a1d-3757-4956-b491-6dd3c0de988c")
  int office();
}
