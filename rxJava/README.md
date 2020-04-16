Reactive Programming == Functional Programming++

  Java 8 Stream             vs.       Reactive Stream
  functional composition              functional composition
  pipeline                            pipeline
  lazy evaluation                     lazy evaluation

  data only                           data also
  How do we deal with exception?
  good luck                           Deal with it downstream
  Exception handling is an imperative
  style of programming idea

  one channel (data)                  three channels
                                      -----> data
                                      -----> error
                                      -----> complete

                                      data comes through data channel
                                      when an error emerges from error channel
                                        or a complete signal comes from the complete channel
                                        the data channel closes forever (based on the concept of circuit breaker)

  serial vs. parallel                 synchronous vs. asynchronous

  no forking                          can have multiple subscribers