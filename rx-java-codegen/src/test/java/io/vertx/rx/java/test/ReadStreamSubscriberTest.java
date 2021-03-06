package io.vertx.rx.java.test;

import java.util.function.Function;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ReadStreamSubscriberTest extends ReadStreamSubscriberTestBase {

  @Override
  protected ReadStreamSubscriberTestBase.Sender sender() {
    return new ReadStreamSubscriberTestBase.Sender() {

      private io.vertx.rx.java.ReadStreamSubscriber<String, String> subscriber = new io.vertx.rx.java.ReadStreamSubscriber<>(Function.identity());

      {
        stream = subscriber;
        subscriber.setProducer(n -> requested += n);
      }

      protected void emit() {
        subscriber.onNext("" + seq++);
      }

      protected void complete() {
        subscriber.onCompleted();
      }

      protected void fail(Throwable cause) {
        subscriber.onError(cause);
      }

    };
  }
}
