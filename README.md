# 📝 TodoApp5 - Jetpack Compose Görev Takip Uygulaması

---

## 🚀 Proje Amacı

Uygulamanın temel amacı:
- Kullanıcının yapılacak görevlerini (todo) oluşturabilmesi
- Bu görevleri listeleyip, tamamlandı olarak işaretleyebilmesi
- İsterse silebilmesi ve detaylarını görebilmesi
- Ayrıca uygulama içi ayarlar yapabilmesini sağlamaktır.

---

## 📦 Uygulama Özellikleri ve Aşamaları

### ✅ 1. MVVM Mimarisi ile Başlangıç
- `ViewModel` oluşturularak görev verileri burada tutuldu.
- State yönetimini `mutableStateOf` ve `collectAsState()` ile sağladım.
- UI tarafında ViewModel'den gelen state'ler görüntüleniyor.

📌 *Not: MVVM, kodun modülerliğini artırdığı için daha sürdürülebilir projeler geliştirmenin temel taşı.*

---

### 🏠 2. Ana Sayfa ve Görev Listeleme
- Görevler `LazyColumn` içinde listeleniyor.
- Her görev bir `Card` bileşeni içinde gösteriliyor.
- Kart içinde:
  - `Checkbox`: Görev tamamlandı mı kontrol etmek için
  - Başlık ve açıklama
  - Tarih ve saat (varsa)
  - `IconButton`: Silmek için

🧠 *Not: `LazyColumn`, RecyclerView'ın Compose versiyonudur. Daha sade bir kullanımı vardır.*

---

### 🗂️ 3. Görev Detayları Sayfası
- Her karta tıklandığında detay sayfasına gidiliyor.
- Navigasyon `Screen.TodoDetails.createRoute(id)` ile id taşıyor.
- Bu sayfada daha sonra güncelleme vs. gibi işlemler yapılabilir.

📌 *Not: Compose'da `NavHost` ve `composable()` ile sayfalar arasında geçiş yapılıyor.*

---

### ➕ 4. Yeni Görev Ekleme Diyaloğu
- Ekranın sağ alt köşesindeki FAB'e tıklanınca `AlertDialog` açılıyor.
- Dialog içinde:
  - Başlık, açıklama, tarih ve saat inputları
  - "Ekle" ve "İptal" butonları
- Eklenen görev anında listeye düşüyor.

💡 *Not: Jetpack Compose'da `AlertDialog` modallar için çok işlevsel.*

---

### 📊 5. StatCard ile Görev Sayısı Gösterimi
- `StatCard` adında özel bir composable ile:
  - Görev sayısı ve başlık şık bir kart içinde gösterildi.
- Bu, kullanıcıya genel durum bilgisi sunuyor.

📌 *Not: Custom composable oluşturmak, kodu parçalayarak okunabilirliği artırır.*

---

### 🧭 6. Navigation & Bottom Bar ile Çoklu Sayfa Yapısı
- 4 sayfa: Ana Sayfa, Görevler, Ayarlar, Görev Detay
- `BottomNavigationBar` kullanarak bu sayfalara geçiş sağlandı.
- Seçili olan sayfa ikonla vurgulanıyor.
- `popUpTo`, `launchSingleTop` gibi ayarlar sayesinde backstack kontrolü yapıldı.

🧠 *Not: `rememberNavController()` ile controller oluşturulup `NavHost` içinde kullanılmalı.*

---

### ⚙️ 7. Ayarlar (Settings) Sayfası
- 4 ana ayar bölümü eklendi:
  - Profil: Placeholder
  - Bildirimler: Switch ile aç/kapa
  - Karanlık Mod: Switch ile aktif etme
  - Hakkında: Versiyon bilgisi
- Her bölüm `SettingsSection`, `SettingsItem` gibi özel bileşenlerle modülerleştirildi.

📌 *Not: Switch gibi etkileşimli UI bileşenleri için `remember { mutableStateOf(...) }` ile state tutulmalı.*

---

## 🧪 Kullanılan Jetpack Compose Yapıları

| Yapı | Amaç |
|------|------|
| `Scaffold` | Genel sayfa düzeni ve FAB için kullanıldı |
| `NavHost`, `composable()` | Sayfa geçişleri için |
| `LazyColumn` | Listeleme için |
| `Card`, `Row`, `Column` | UI düzeni için |
| `ViewModel` | Veri yönetimi ve mantık için |
| `State`, `remember` | UI'da dinamik tepki vermek için |

---

## 📚 Notlar

- Compose'da her şey bir `@Composable` fonksiyonu, bu yüzden UI'yi fonksiyonel olarak düşünmek gerekiyor.
- `Modifier` zincirleme kullanılabiliyor ve UI'nın esnekliğini sağlıyor.
- Navigation'da rotaları String sabitlerle tanımlamak iyi bir pratik.
- UI'daki state'leri ViewModel'e taşıyarak "tek kaynak" kuralına uymak, özellikle büyük projelerde çok önemli.
- Material3 temasıyla tasarım tutarlılığı çok kolay sağlanıyor.

---




