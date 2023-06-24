# Fragment
Fragment tương ứng với một thành phần gao diện của activity nhưng có vòng đời riêng bị ảnh hưởng trực tiếp bởi vòng đời của activity cha. Nó hỗ trợ rất nhiều trong việc **tối ưu cho các loại màn hình**, đồng thời dễ dàng được quản lý bởi activity cha, có thể **sử dụng lại, kết hợp và bố trí theo ý muốn**.

Khi activity cha bị **stop** hoặc **destroy**, tất cả các **fragment** trong nó cũng bị **stop** hoặc **destoy** theo.

Khi activity được **resume** chúng ta có thể thêm hoặc bớt fragment trong nó.

Một số fragment có thể **không cần có giao diện**

# Fragment Lifecycles!

**onAttack()**: Callback này được gọi ngay sau khi fragment được gắn vào activity cha và được gọi **một lần duy nhất** trong suốt vòng đời của fragment.

**onCreate()**: Callback này được gọi khi fragment bắt đầu khởi tạo dữ liệu đầu vào. Khác với activity, giao diện của fragment không được khởi tạo ở đây. Hàm này cũng được gọi **một lần duy nhất** và thường được tận dụng để lấy dữ liệu từ bên ngoài truyền vào.

**onCreateView()**: Callback này được gọi khi fragment bắt đầu vẽ UI. Chúng ta sẽ dùng callback này cho các thiết lập về giao diện. Ngoài ra nó còn được gọi khi fragment được gỡ ra khỏi activity và đưa vào back stack sau đó được hiện thị lại.
*onCreateView()* trả về một View, hoặc cũng có thể trả về null nếu fragment không có giao diện.

``` kotlin
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        initComponents(rootView);
        return rootView;
    }

    private void initComponents(View rootView) {
        mTextMessage = rootView.findViewById(R.id.text_message);
        mButtonSend = rootView.findViewById(R.id.button_send);
    }

```
- **LayoutInflater** giúp chúng ta chuyển giao diện từ file **xml** thành **View**(java code). Các tham số cần cung cấp:
- **int resource**: file giao diện **xml** mà chúng ta cần chuyển thành **View**
  **ViewGroup parent**: ViewGroup mà fragment có thể được nhúng vào. Giao diện khi chuyển đổi sẽ sử dụng các thuộc tính phù hợp với **parent**
- **bool attachToRoot**: Khi chuyển đổi xong có thêm ngay giao diện vào ViewGroup hay không.

**onCreatedView()**: Giao diện fragment đã được khởi tạo hoàn toàn
**onStart()**: Fragment bắt đầu được người dùng nhìn thấy và chuẩn bị nhận tương tác
**onResume()**: Người dùng có thể tương tác với fragment
**onPause()**: Khi activity bị che một phần thi nó sẽ tiến vào **onPause()** kéo theo fragment cũng gọi **onPause()**. Chúng ta nên thực hiện các sao lưu dữ liệu cần thiết ở hàm này vì kể cả khi app bị kill bởi hệ thống nó cũng sẽ nhảy vào đây trước.
**onStop()**: Fragment không còn được nhìn thấy nữa
**onDestroyView()**: View bị hủy. Nếu fragment được thêm vào back stack thì nó sẽ được gọi lại **onCreateView()** khi cần thiết
**onDestroy()**: Fragment sắp bị gỡ khỏi activity
**onDeTach()**: Fragment bị gỡ khỏi activity và kết thúc vòng đời

# FragmentManager
Lớp này được sử dụng để quản lý các fragment, nó được tích hợp sẵn vào các activity giúp chúng ta có thể dễ dàng thêm, xóa hay thay thế một fragment thông qua **FragmentTransaction**
```kotlin
supportFragmentManager.beginTransaction().add(  
  R.id.fragment_container_view,  
  NewFragment()  
).commit()
```
### FragmentTransaction
**add()**: Khi FrameLayout còn rỗng, chúng ta có thể dùng hàm này để thêm một fragment vào. Nếu tiếp tục thêm khi fragment đã tồn tại thì 2 fragment được chạy song song và fragment sau sẽ che đi fragment trước.

**replace()**: Thay thế một fragment có sẵn bằng một fragment khác

**remove()**: Gỡ fragment ra khỏi layout nào đó

**accToBackStack()**: Nếu phương thức được gọi trước khi commit, fragment hiện tại sẽ được thêm vào back stack và không bị xóa khỏi hệ thống. Nếu người dùng bấm nút back sau đó, fragment có thể quay lại.

**commit()**: Sau khi đã thực hiện các thao tác, **commit** để **FragmentTransaction** thực hiện.

