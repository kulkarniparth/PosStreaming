# PosStreaming

It has multiple producers acting as different Point of Sales terminals sending invoices to the Kafka Broker at an interval of 10s and 7s respectively. 

These invoices are processed through the Stream processing application using Kafka Streams and added as a dump to a new topic post business logic filtering.
