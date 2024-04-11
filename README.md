# WORD-VAULT

[Detaylı döküman için buraya tıklayın](https://docs.google.com/document/d/1MU2sROrm1WQASzx_I0PfWEEFq4QkEuYleCD0e_2FXnE/edit)


![DB diagram tasarımı](assets/dbdiagram.png)
[DB diagram text](assets/dbdiagram.txt)


## Technologies

---
- Java 17
- Spring Boot 3.2.2
- Elasticsearch
- Kafka
- Debezium
- Resilience4j
- Spring Cache
- Open API Documentation
- Spring Data JPA
- H2 In Memory Database
- Restful API
- Maven
- Junit5
- Integration Tests
- Docker
- Docker Compose
- Prometheus
- Grafana

Bir kelime kaydedildiğinde veritabanının ürettiği logları dinleyen Debezium bunları kafkaya yazıyor. Daha sonrasında bu topici okuyan listener veriyi elasticsearche kaydediyor. Böylelikle veri asenkron olarak elasticsearche geçirilmiş oluyor.

Test için kelimeleri veritabanından çekmek için özel bir SQL sorgusu kullanılıyor. WordRepository'nin içinde bulabilirsiniz.

Uygulama hala geliştirme aşamasında oldugu için sürekli güncellenecektir.

Word-Vault, yabancı dil öğrenirken sıkça karşılaşılan sorunları çözmek amacıyla tasarlanmış bir uygulamadır. Bu uygulama, bilinmeyen kelimeleri not almanıza ve daha sonra bu kelimeleri tekrar etmek için otomatik sınavlar oluşturmanıza olanak tanır. Aynı zamanda önemli konuları not almanızı sağlar ve bu notlara kolayca erişmenizi sağlar.




