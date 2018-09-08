
import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import com.typesafe.config.ConfigFactory

object KafkaProducerExample {

  def main(args: Array[String]): Unit = {

    val conf = ConfigFactory.load()
    val envProp = conf.getConfig(args(0))

    val prop = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envProp.getString("bootstrap.server"))

    prop.put(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProducerExample")
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](prop)
    val data = new ProducerRecord[String,String]("kafka-testing","key1", "value1")
    producer.send(data)

    println("done")
    
    println("changes done by other")
  }

}
