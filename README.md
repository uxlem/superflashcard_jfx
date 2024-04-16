# Siêu ứng dụng FlashCard
## Dịch và chạy siêu ứng dụng

Tham khảo https://openjfx.io/openjfx-docs/#introduction

1. Tải javafx-**sdk** tại https://gluonhq.com/products/javafx/ (chú ý chọn tải sdk), giải nén, trong đó có thư mục `lib`

    Mình dùng bản 21.0.2 LTS

2. Tạo một project trống bằng IDE của mọi người

3. Thêm các file trong thư mục src trên này vào project vừa tạo

4. Thêm thư viện javafx-sdk vào Classpath của project, thêm VM arguments khi chạy, hướng dẫn bên dưới, nếu đọc hướng dẫn khó hiểu quá hãy xem youtube nhé 🥺

### Với IDE IntellJ
Xem trang tham khảo đã ghi ở trên, có cả hướng dẫn cho Eclipse và NetBeans.

### Với IDE Eclipse
1. Thêm thư viện javafx vào "Classpath"

* Nếu chưa cài e(fx)clipse
    * Trên Menu Bar (thanh dưới thanh tiêu đề cửa sổ), chọn `Window > Preferences > Java > Build Path > User Libraries`
   
    * Chọn `New` và ghi tên thư viện mới của mình, ví dụ: `JavaFX21`
    * Bấm chọn thư viện vừa tạo, chọn `Add External JARs`, tìm đến thư mục `lib` trong thư mục chứa javafx-sdk, chọn thư mục lib đó hoặc tất cả các file .jar bên trong.
    * `Apply and Close`.

    * Mở `Project > Properties > Java Build Path > Libraries`
    * Bấm chọn `Classpath`, chọn `Add Library... > User Library` và chọn thư viện vừa tạo ở trên.
    * `Apply and Close`.

* Nếu đã cài e(fx)clipse
    * Mở `Window > Preferences > JavaFX`
    * Chỉnh phần `JavaFX 11 SDK` thành đường dẫn thư mục `lib` của javafx-sdk

2. Thêm VM Arguments
    * Mở `Run > Run Configurations`
        * Hoặc là chọn vào `Java Applications` rồi bấm `New Configurations`
        * Hoặc là chọn luôn Run Configurations đang có, như hình (đánh dấu số 1) 
        ![alt text](image.png)

    * Chọn thẻ `Arguments`, thêm vào phần `VM arguments` dòng sau:

             --module-path $PATH_TO_FX$ --add-modules javafx.controls,javafx.fxml

        trong đó `$PATH_TO_FX$` là địa chỉ của thư mục `lib` của javafx-sdk. Nếu đã có `$PATH_TO_FX$` sẵn trong `Environment Variables` thì không cần phải thay cụ thể như trong ảnh trên.

### Với IDE NetBeans




