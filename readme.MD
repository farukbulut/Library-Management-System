# Kütüphane Yönetim Sistemi 📚

Bu proje, basit bir konsol tabanlı kütüphane yönetim sistemidir. Java ile geliştirilmiş olup, kütüphanedeki kitapları, yazarları ve ödünç alma işlemlerini yönetmek için tasarlanmıştır.

## 🚀 Özellikler

### Yazar Yönetimi
- ✅ Yazar ekleme
- ✅ Yazar güncelleme
- ✅ Yazar silme
- ✅ Yazarları listeleme
- ✅ Yazar arama (ad/soyad ile)

### Kitap Yönetimi
- 📖 Kitap ekleme (mevcut yazarlar ile)
- 📖 Kitap güncelleme
- 📖 Kitap silme
- 📖 Kitapları listeleme
- 📖 Kitap arama (başlık ile)
- 📖 Kitap durumu görüntüleme

### Ödünç Alma Sistemi
- 🔄 Kitap ödünç verme
- 🔄 Kitap iade alma
- 🔄 Ödünçte olan kitapları listeleme
- 🔄 Gecikme kontrolü (14 gün limiti)
- 🔄 Kitap durum sorgulama

## 📁 Proje Yapısı

```
com.bulut/
├── Main.java                    # Ana giriş noktası
├── controller/
│   ├── AuthorController.java    # Yazar işlemleri kontrolü
│   ├── BookController.java      # Kitap işlemleri kontrolü
│   └── LoanController.java      # Ödünç alma/verme kontrolü
├── dao/
│   ├── AuthorDao.java          # Yazar veri erişim katmanı
│   └── BookDao.java            # Kitap veri erişim katmanı
├── model/
│   ├── Author.java             # Yazar veri modeli
│   └── Book.java               # Kitap veri modeli
└── ui/
    ├── MenuManager.java        # Menü yönetimi
    └── InputManager.java       # Kullanıcı girdisi yönetimi
```

## 📋 Kullanım

### Ana Menü
Program başladığında aşağıdaki ana menü görüntülenir:

```
========================================
           ANA MENÜ
========================================
1. Yazar İşlemleri
2. Kitap İşlemleri  
3. Ödünç İşlemleri
4. Çıkış
========================================
```

### Örnek Kullanım Senaryoları

#### Yeni Yazar Ekleme
1. Ana menüden "1" seçin
2. Yazar işlemleri menüsünden "1" seçin
3. Yazar bilgilerini girin:
    - ID (benzersiz olmalı)
    - Ad
    - Soyad
    - Doğum tarihi (dd-MM-yyyy formatında)

#### Kitap Ekleme
1. Ana menüden "2" seçin
2. Kitap işlemleri menüsünden "1" seçin
3. Mevcut yazarlar listesinden birini seçin
4. Kitap bilgilerini girin:
    - Başlık
    - ISBN (benzersiz olmalı)

#### Kitap Ödünç Verme
1. Ana menüden "3" seçin
2. Ödünç işlemleri menüsünden "1" seçin
3. Mevcut kitaplar arasından ISBN girin
4. Sistem otomatik olarak ödünç tarihi kaydeder

## 📊 Örnek Veriler

Sistem ilk başlatıldığında otomatik olarak örnek veriler yüklenir:

**Yazarlar:**
- Orhan Pamuk
- Yaşar Kemal
- Sabahattin Ali
- Nazım Hikmet
- Aziz Nesin

**Kitaplar:**
- Kar (Orhan Pamuk)
- Benim Adım Kırmızı (Orhan Pamuk)
- İnce Memed (Yaşar Kemal)
- Kürk Mantolu Madonna (Sabahattin Ali)
- Şu Çılgın Türkler (Nazım Hikmet)
- Zübük (Aziz Nesin)

## 🔧 Özellikler Detayı

### Veri Doğrulama
- **Tarih Kontrolü:** Doğum tarihi gelecekte olamaz ve 1800'den önce olamaz
- **Boş Alan Kontrolü:** Tüm zorunlu alanlar doldurulmalıdır
- **Sayısal Giriş:** ID alanları için sadece geçerli sayılar kabul edilir
- **Benzersizlik:** Yazar ID'leri ve kitap ISBN'leri benzersiz olmalıdır

### Ödünç Alma Kuralları
- ✅ Sadece mevcut (ödünçte olmayan) kitaplar ödünç verilebilir
- ✅ Ödünçte olan kitaplar silinemez
- ✅ 14 gün ödünç süresi limiti
- ✅ Geciken kitaplar için uyarı mesajları
- ✅ Otomatik ödünç süresi hesaplama

### Arama Özellikleri
- **Yazar Arama:** Ad veya soyad ile kısmi eşleşme
- **Kitap Arama:** Başlık ile kısmi eşleşme
- **Büyük/küçük harf duyarsız** arama


---