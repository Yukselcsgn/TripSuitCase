# TripSuitCase

### Proje Tanımı : Bu proje ile seyahate çıkarken kendiniz arabanız ve çocuğunuz için yanınıza almanız gerekenler ve yapılacaklar listesi hazırlayabilirsiniz böylece unutmamanız gereken şeyleri unutmazsınız.

### Proje Kategorisi : Mobil seyahat Uygulaması

### Referans Uygulama : [SirtimdaNevar](https://github.com/Yukselcsgn/SirtimdaNeVar)

### Uygulama Adresi : [TripSuitCase](https://github.com/Yukselcsgn/TripSuitCase)

### Grup Adı : PANDAS

### Proje Ekibi : Yüksel COŞGUN

### Proje Gereksinim Açıklamaları: [Gereksinim](https://github.com/Yukselcsgn/TripSuitCase/blob/main/Pandas-Gereksinim_Detay.pdf)

### Proje Use Case : [UseCase](https://github.com/Yukselcsgn/TripSuitCase/blob/main/Pandas-UseCase.pdf)

### Proje Veritabanı Diyagramı: [VT Diagram](https://github.com/Yukselcsgn/TripSuitCase/blob/main/pandas_db_diagram.png)

### Proje Planı : [Plan](https://github.com/Yukselcsgn/TripSuitCase/blob/main/Pandas%20Plan.png)

#### 1. Kayıt olma
#### 2. Giriş yapma
#### 3. İhtiyaç kategorilerinin 
#### 4. Profil Düzenleme
#### 5. Basit İhtiyaçların Eklenmesi, Silinmesi ve işaretlenmesi
#### 6. Yanına Alınacak Giysilerin Eklenmesi, Silinmesi ve işaretlenmesi
#### 7. Kişisel İhtiyaçların Eklenmesi, Silinmesi ve işaretlenmesi
#### 8. Bebek İhtiyaçlarının Eklenmesi, Silinmesi ve işaretlenmesi
#### 9. Sağlık İhtiyaçlarının Eklenmesi, Silinmesi ve işaretlenmesi
#### 10. Teknolojik Aletlerin Eklenmesi, Silinmesi ve işaretlenmesi
#### 11. Yiyeceklerin Eklenmesi, Silinmesi ve işaretlenmesi
#### 12. Sahilde Kullanılacakların Eklenmesi, Silinmesi ve işaretlenmesi
#### 13. Araba için İhtiyaçların Eklenmesi, Silinmesi ve işaretlenmesi
#### 14. Yapılacaklar Listesinin Eklenmesi, Silinmesi ve işaretlenmesi
#### 15. Unutulan şeyleri hatırlatmak için telefona bildirim gönderilmesi
#### 16. Her sayfanın ayarlar bölümü

### Seyahete giderken yardımcı olacak size unuttuklarınızı hatırlatacak bir uygulama. Kendi listenizi internete bağlanma gereği duymadan düzenleyebilir ekleyip çıkartabilir ve yanınıza aldıklarınızı işaretleyebilirsiniz.

### Login Ekranı : Bu ekranda giriş yapabilirsiniz
### Register Ekranı : Eğre üyeliğiniz yyoksa üye olabilirsiniz.
### Kategoriler : İhtiyacınız olan kategoriler listelenir.
### CheckList Ekranı : Her kategorinin kendine ait bir cheklist ekranı bulunmaktadır.
### TripPlan  : Bir sonraki seyahatinizin tarihini seçebilirsiniz ve seyahat yaklaştığında size bildirim gönderir.

# Kullanılan Teknolojiler 
## Room Kütüphanesi:
### Verileri internete gerek duymadan düzenleyebilmek için lokal bir şekilde telefonda tutmaya yarayan bir local veritabanı kütüphanesidir.

## Firebase-Authantication
### Farklı giriş seçenekleri hakkında yardım sağlayan bir kütüphanedir. Google, Facebook ve mail aracılığıyla giriş yaparken oluşan kod karmaşasını ortadan kaldırır ve yazılımcıya yardımcı olur.

## Firebase-FireStore-Database
### No-Sql bir veritabanıdır. Uygulamada kullanıcıların yapacağı seyahetleri burada tutuyoruz.

## Firebase-RealTime Database
### Kullanıcı verilerini anlık olarak tutmak ve anlık olarak güncelleyebilmek için kullanıyoruz. Kayıt yapan kullanıcıların verileri anlık olarak buraya düşmektedir.

## Firebase-Messaging-Service(FCM)
### Service paketinin içerisinde bulunan TripNotificationService sınıfı ile bu servisi kullanarak kullanıcılara bildirim gönderiyoruz.TripPlanActivity ile seyahet terihi seçildiğinde bu tarih firestore-database içerisine kaydedilir ve düzenli olarak kaç gün kaldığı hesaplanarak seyahat günü yaklaştığında bu TripNotificationService sınıfı aracılığı ile bu servis kullanılarak kullanıcılara bildirim gönderilir.

`public void sendNotification(String title, String message) {

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("TripNotification", "Trip Notifications", NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "TripNotification");
        notificationBuilder.setSmallIcon(R.drawable.app_icon_two);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon_two));
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(12345, notificationBuilder.build());
    }`
<img src="https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/f06ff672-b359-49c4-830a-81365efb023e" alt="alt text" width="320" height="180">

![WhatsApp Image 2024-06-03 at 18 06 05](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/f06ff672-b359-49c4-830a-81365efb023e)

![WhatsApp Image 2024-06-03 at 18 06 05 (1)](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/35c2938b-c9dd-42ca-9f1d-066278278e6b)
![WhatsApp Image 2024-06-03 at 18 06 05 (2)](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/8cc2523d-0956-48a2-99f7-8e0da18ff800)
![WhatsApp Image 2024-06-03 at 18 06 06](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/01fd8cbc-5ca0-41b0-a73c-503c426db586)
![WhatsApp Image 2024-06-03 at 18 06 06 (1)](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/b4eeaa5c-9015-41fd-920e-09cfca6a6787)
![WhatsApp Image 2024-06-03 at 18 06 06 (1)](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/7c7ec536-22df-41eb-9c6b-d30aed30ed6f)
![WhatsApp Image 2024-06-03 at 18 06 06 (2)](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/2e9d5e9e-4933-4d90-a660-ebb43d5c63bd)
![Ekran görüntüsü 2024-06-03 195018](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/1aaaa385-536d-4c2a-ad86-fb7ec5c10a08)
![Ekran görüntüsü 2024-06-03 195235](https://github.com/Yukselcsgn/TripSuitCase/assets/31345859/9aecfd87-a8ce-40ac-ba7a-bc7cf2643a30)
