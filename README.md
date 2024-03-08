# Word Vault

## Yabancı Dil Uygulaması

Yabancı dil öğrenirken sıkça karşılaşılan sorunlardan biri, bilinmeyen kelimeleri ve önemli noktaları not almak ve daha sonra bu bilgileri tekrarlamak ve hafızada tutmaktır. Bu uygulama, bilinmeyen kelimeleri ekleyip daha sonra tekrar etmek için otomatik sınavlar oluşturmanıza olanak tanır. Aynı kelimelere doğru cevap verdiğinizde, uygulama bu kelimeleri öğrenildi olarak işaretler ve daha az sormayı hedefler.

Uygulama ayrıca önemli konuları not almanıza ve bu notlara kolayca ulaşmanıza olanak sağlar. Öğrenilmiş konuları ve önemli detayları arayabilir, sıralayabilir ve filtreleyebilirsiniz.

En güzel yanı, bilgi düzenleme işlemlerini kolaylaştırmak için tasarlanmış basit bir arayüze sahip olmasıdır. Bilmediğiniz kelimeleri ve önemli konuları kaydetmek, örnek cümleleri eklemek ve bu bilgileri organize etmek artık çok daha kolay.

Kendi kelimeleriniz dışında sistem tarafından eklenmiş veya diğer kullanıcıların eklediği onaylanmış kelimelerden test olabilirsiniz.

## Modeller

- **User:** name, surname, elo
- **Word:** tr, en, it, alm, userId, isApproved, elo, proficiency_level
- **Subject:** subject, description
- **Note:** note, subject_id
- **Sample Sentences:** sentence, subject_id

## Yapılacaklar

- Kullanıcılar kendi bilmedikleri kelimeleri ekleyebilir; bunlar onaylanmamış kelime olarak eklenecektir.
- Config eklenmeli.
- Kelimelere doğru veya yanlış cevapların elo ve proficiency_level’i nasıl etkileyeceğinin belirlenmesi ve bildirim sıklığı.
- Sistem tarafından eklenen kelimeler onaylanmış kelime olarak eklenecektir.
- Kullanıcılar bilmediği konuları ekleyebilir.
- Kullanıcılar konular hakkında notlar ekleyebilir.
- Kullanıcılar konulara örnek cümleler ekleyebilir.
- Kullanıcıya belli aralıklarla tekrar teste girmesi gerektiğine dair bildirim atılacaktır.
- Aksatılmadan girilen testler bir zincir başlatacaktır.
- İstatistiklerin olduğu profil sayfası tasarlanacak.
- Otomatik sınav oluşturma.
  - İki tür sınav modu bulunmaktadır:
    - Onaylanmış kelimelerle sınav: Kullanıcının seviyesine göre sınav oluşturulur.
    - Kendi eklediğimiz kelimelerle sınav: Not aldığımız kelimeleri tekrar edip öğrenmek için kullanılır.
- Kelimelerin öğrenme katsayılarını belirleyen katsayılar vardır. Uygulama kullanılmaya devam edildikçe, bütün kullanıcıların cevaplarına göre doğru cevaplar kelimenin seviyesini düşürür ve yanlış cevaplar kelimenin zor olduğunu varsayıp seviyesini arttırır. Bu seviye, kullanıcıya uygun kelimeler sunmamızı sağlar.

## Yapım Aşamaları

- Kelime ekleme
  - Kelime entity, dto ve repository’si oluşturma.
  - Kullanıcının kelimesini kaydedebileceği bir endpoint.
  - Kullanıcının kendi eklediği kelimeleri görebileceği endpoint.
  - Kullanıcının tüm onaylanmış kelimeleri görebileceği endpoint.
- Authentication
- Sınav modu ekleme
- Elo ve proficiency level işlevsel hale getirme
- Konu ekleme
- Konulara not ve örnek cümle ekleme
- Profil sayfası
