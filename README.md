# Vccorp_Week2
<h3>Kết quả bài 4</h3>


Kết quả một số lần test:

Thông số chung:
- Thời gian test: 10s
- Số luồng: 4
- Số connections: 567

</br>

Không sử dụng cache:
Thống số | Lần 1 | Lần 2 | Lần 3
--- | --- | --- | ---
Số request | 15474 | 20408 | 18730
Thời gian response trung bình | 2.227 ms | 1.706 ms | 1.86 ms
Độ lệch chuẩn | 8.662 ms | 8.714 ms | 9.626 ms
Requests/sec | 1556.144 | 2047.191 | 1881.1

</br>

Sử dụng cache:
Thống số | Lần 1 | Lần 2 | Lần 3
--- | --- | --- | ---
Số request | 10010 | 23365 | 28406
Thời gian response trung bình | 3.529 ms | 1.498 ms | 1.229 ms
Độ lệch chuẩn | 19.449 ms | 4.347 ms | 4.124 ms
Requests/sec | 1006.697 | 2341.5 | 2847.1


</br>
<h3>Cách chia connections cho các threads</h3>

- AtomicInteger maxThread : số luồng tối đa
- AtomicInteger maxCon: số connections tối đa

</br>

- Luồng mới được khởi tạo với số connect = maxCon / maxThread + maxCon % maxThread
- Khi luồng được khởi tạo, maxCon sẽ bị trừ một lượng bằng số connect trên, maxThread sẽ bị trừ đi 1
- Khi luồng thực thi xong, maxCon sẽ được cộng lại lượng đã trừ, maxThread sẽ tăng 1
