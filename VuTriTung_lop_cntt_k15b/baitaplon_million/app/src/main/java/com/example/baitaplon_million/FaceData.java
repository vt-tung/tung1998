package com.example.baitaplon_million;

import android.content.Context;

import com.example.baitaplon_million.sql.DBManager;

import java.util.ArrayList;
import java.util.Random;

public class FaceData {

    public FaceData(Context ct) {
        layCauHoiSqlite(ct);
    }

    public void layCauHoiSqlite(Context ct){
        DBManager d = new DBManager(ct);
        d.open();
        arrCauHoi =  new ArrayList<>(d.getcauhoi());
        d.close();
    }

    public CauHoi taoCauHoi(int capDo){
        Random r = new Random();
        ArrayList<CauHoi> arr =arrCauHoi.get(capDo-1);
        return arr.get(r.nextInt(arr.size()));
    }


    ArrayList<ArrayList<CauHoi>> arrCauHoi = new ArrayList<>();

    public void taolistcauhoi(){
        //Câu hỏi 1
        ArrayList<CauHoi> arrCauSo1 = new ArrayList<>();
        arrCauSo1.add(taoCauHoi("Điền từ còn thiếu vào câu ca dao: \"Gần mực thì đen, gần đèn thì...\"gì", "Sáng", "Chói", "Lóa", "Tối"));
        arrCauSo1.add(taoCauHoi("Mạnh vì..., bạo vì tiền","Gạo", "Thóc", "Lúa", "Mì"));
        arrCauSo1.add(taoCauHoi("Ngôi chùa được đúc hoàn toàn bằng đồng tại Việt Nam","Chùa Đồng","Chùa Keo","Chùa Hương","Chùa Lim"));
        arrCauHoi.add(arrCauSo1);

        //Câu số 2
        ArrayList<CauHoi> arrCauSo2 = new ArrayList<>();
        arrCauSo2.add(taoCauHoi("Từ nào sau đây khác nghĩa với 3 từ còn lại", "Sinh Viên", "Sinh Nở", "Sinh Sôi", "Sinh Trưởng"));
        arrCauSo2.add(taoCauHoi("Công thức hóa học của đá vôi","CaCo3","CaCo2", "CaCo1", "Cu2Cl3"));
        arrCauSo2.add(taoCauHoi("Câu nói: \"Đầu thần chưa rơi xuống đất, xin bệ hạ đừng lo\" là của ai","Trần Thủ Độ","Trần Quốc Toản", "Trần Thủ Đức", "Trần Văn Đoàn"));
        arrCauHoi.add(arrCauSo2);

        //Câu số 3
        ArrayList<CauHoi> arrCauSo3 = new ArrayList<>();
        arrCauSo3.add(taoCauHoi("Chị em dâu như bầu...", "Nước Lã", "Nước Đường", "Nước Cất", "Nước Me"));
        arrCauSo3.add(taoCauHoi("Loại vật liệu dùng trong sản xuất thủy tinh","Cát trắng","Đất", "Đá", "Bùn"));
        arrCauSo3.add(taoCauHoi("Ao... nước đọng","Tù","Sâu", "Cạn", "Nông"));
        arrCauHoi.add(arrCauSo3);

        //Câu số 4
        ArrayList<CauHoi> arrCauSo4 = new ArrayList<>();
        arrCauSo4.add(taoCauHoi("Đơn vị đo dung lượng bộ nhớ nào lớn nhất", "TB", "GB", "KB", "MB"));
        arrCauSo4.add(taoCauHoi("Hội hát quan họ nào được tổ chức từ 11 - 13 tháng Giêng Âm lịch hàng năm","Hội Lim","Hội Hương", "Hội Keo", "Hội Hát"));
        arrCauSo4.add(taoCauHoi("Sat trong tiếng Anh là thứ mấy trong tuần","Thứ bảy","Thứ sáu", "Chủ Nhật", "Thứ Năm"));
        arrCauHoi.add(arrCauSo4);

        //Câu số 5
        ArrayList<CauHoi> arrCauSo5 = new ArrayList<>();
        arrCauSo5.add(taoCauHoi("Người đẹp vì lụa, ... tốt vì phân", "Lúa", "Thóc", "Gạo", "Mì"));
        arrCauSo5.add(taoCauHoi("Người ta thường gọi quốc gia nào là đất nước mặt trời mọc","Nhật Bản","Anh", "Pháp", "Trung Quốc"));
        arrCauSo5.add(taoCauHoi("Nóc nhà Đông Dương","Đỉnh Phanxipang","Đỉnh Pu Ta Leng", "Đỉnh Pu Si Lung", "Đỉnh Bạch Mộc Lương Tử"));
        arrCauHoi.add(arrCauSo5);

        //Câu số 6
        ArrayList<CauHoi> arrCauSo6 = new ArrayList<>();
        arrCauSo6.add(taoCauHoi("Vua nào đặt nhiều niên hiệu nhất lịch sử nước ta", "Lý Nhân Tông", "Lý Thường Kiệt", "Lý Chiêu Hoàng", "Lý Thái Tổ"));
        arrCauSo6.add(taoCauHoi("Ngày bầu cử quốc hội khóa 12","20-05-2007","20-04-2007", "20-03-2007", "20-02-2007"));
        arrCauSo6.add(taoCauHoi("Tên vũ khí của thổ dân Úc có khả năng bay lại về vị trí cũ","Bomerang","Dao Quắm", "Máy Bay", "Chong Chóng"));
        arrCauHoi.add(arrCauSo6);

        //Câu số 7
        ArrayList<CauHoi> arrCauSo7 = new ArrayList<>();
        arrCauSo7.add(taoCauHoi("Biển có nồng độ muối lớn nhất thế giới", "Biển Chết", "Biển Đông", "Biền Tây", "Biển Nha Trang"));
        arrCauSo7.add(taoCauHoi("Kinh thành trà kiệu thuộc tỉnh nào","Quảng Nam","Quảng Bình", "Quảng Ngãi", "Quảng Ninh"));
        arrCauSo7.add(taoCauHoi("Nhạc sĩ Sô Panh gắn liền với nhạc cụ nào","Đàn Piano","Đàn Violo", "Kèn saxophone", "Kèn trumpet"));
        arrCauHoi.add(arrCauSo7);

        //Câu số 8
        ArrayList<CauHoi> arrCauSo8 = new ArrayList<>();
        arrCauSo8.add(taoCauHoi("AFC Asian Cup 2011 được tổ chức tại quốc gia nào?", "Qatar", "Hàn Quốc", "Nhật Bản", "Trung Quốc"));
        arrCauSo8.add(taoCauHoi("Ai là nhạc sĩ Việt Nam đầu tiên viết opera với tác phẩm “Cô sao” và sau đó là “Người tạc tượng”","Đỗ Nhuận","Hoàng Vân", "Trần Hoàn", "Trọng Đài"));
        arrCauSo8.add(taoCauHoi("Sông Bến Hải và sông Thạch Hãn nằm ở tỉnh nào?","Quảng Trị","Quảng Bình", "Quảng Ngãi", "Quảng Ninh"));
        arrCauHoi.add(arrCauSo8);

        //Câu số 9
        ArrayList<CauHoi> arrCauSo9 = new ArrayList<>();
        arrCauSo9.add(taoCauHoi(" Đại Ngu là quốc hiệu của triều đại nào?", "Triều Nhà Hồ", "Triều Nhà Ngô", "Triều Nhà Trần", "Triều Nhà Lê"));
        arrCauSo9.add(taoCauHoi("An Dương Vương đặt quốc hiệu nước ta là gì","Âu Lạc","Mân Việt", "Văn Lang", "Lạc Việt"));
        arrCauSo9.add(taoCauHoi("Bộ phim \"Chị Dậu\" được chuyển thể từ tác phẩm văn học nào","Tắt đèn","Người mẹ cầm súng", "Vợ chồng A Phủ", "Tuổi thơ dữ dội"));
        arrCauHoi.add(arrCauSo9);

        //Câu số 10
        ArrayList<CauHoi> arrCauSo10 = new ArrayList<>();
        arrCauSo10.add(taoCauHoi(" Vườn quốc gia nào hiện không nằm trong danh sách Vườn di sản ASEAN", "Vườn quốc gia Tam Đảo", "Vườn quốc gia Kon Ka Kinh","Vườn quốc gia Chư Mom Ray", "Vườn quốc gia Bái Tử Long"));
        arrCauSo10.add(taoCauHoi("Hoa hậu Hòa bình Quốc tế 2017 dự kiến sẽ được tổ chức tại quốc gia nào","Việt Nam"," Thái Lan", "Nhật", "Trung Quốc"));
        arrCauSo10.add(taoCauHoi("Nhạc sĩ nào là người phổ nhạc ca khúc Cây đàn sinh viên","Quốc An","Bảo Chấn", "Trịnh Công Sơn", "Trần Tiến"));
        arrCauHoi.add(arrCauSo10);

        //Câu số 11
        ArrayList<CauHoi> arrCauSo11 = new ArrayList<>();
        arrCauSo11.add(taoCauHoi("Theo bạn 'Bọ chét' là biệt danh của cầu thủ bóng đá nào", "L. Messi", "R. Carlos", "A. Ortega", "A. Di Maria"));
        arrCauSo11.add(taoCauHoi("Bảy chú lùn trong truyện cổ tích 'Nàng bạch tuyết và bảy chú lùn làm' làm nghề gì","Thợ mỏ","Thợ Rèn", "Thợ Săn", "Thợ Thủ Công"));
        arrCauSo11.add(taoCauHoi("Tên ngọn thác Đambri nổi tiếng của núi rừng Tây Nguyên có nghĩa là gì","Đợi Chờ","Chung Thủy", "Mong Mỏi", "Nhớ Nhung"));
        arrCauHoi.add(arrCauSo11);

        //Câu số 12
        ArrayList<CauHoi> arrCauSo12 = new ArrayList<>();
        arrCauSo12.add(taoCauHoi("Đất nước nào là quê hương của ông già tuyết", "Phần Lan", "Hà Lan", "Anh", "Mỹ"));
        arrCauSo12.add(taoCauHoi("Ở Chùa Bộc, ngoài thờ phật, nhân dân còn thờ vị vua nào?","Vua Quang Trung","Vua Lý Thái Tổ", "Vua Trần Nhân Tông", "Vua Hùng"));
        arrCauSo12.add(taoCauHoi("Hệ nhị phân cho mã hóa mấy kí tự","2","1", "3", "4"));
        arrCauHoi.add(arrCauSo12);

        //Câu số 13
        ArrayList<CauHoi> arrCauSo13 = new ArrayList<>();
        arrCauSo13.add(taoCauHoi("Đất đai ở ven các hoang mạc, bán hoang mạc ở châu Phi, nhiều nơi bị hoang mạc hóa là do", "Rừng bị khai phá quá mức", "Quá trình xói mòn, rửa trôi xảy ra mạnh ", "Quá trình xâm thực diễn ra mạnh mẽ", "Khí hậu khô hạn"));
        arrCauSo13.add(taoCauHoi("Ở Chùa Bộc, ngoài thờ phật, nhân dân còn thờ vị vua nào","Vua Quang Trung","Vua Lý Thái Tổ", "Vua Trần Nhân Tông", "Vua Hùng"));
        arrCauSo13.add(taoCauHoi("Cameroon là quốc gia thuộc châu lục nào","Châu Phi","Châu Á", "Châu Âu", "Châu Mỹ"));
        arrCauHoi.add(arrCauSo13);

        //Câu số 14
        ArrayList<CauHoi> arrCauSo14 = new ArrayList<>();
        arrCauSo14.add(taoCauHoi("Bãi biển Mỹ Khê ở đâu","Đà Nẵng","Đà Lạt", "Nghệ An", "Khánh Hoà"));
        arrCauSo14.add(taoCauHoi("Quốc gia nào sau đây của Châu Âu không giáp với biển","Áo","Anh", "Đức", "Bồ Đào Nha"));
        arrCauSo14.add(taoCauHoi("Một người trọng tài đang thổi còi trong một trận đấu bóng đá Nguồn âm ở đây là gì","Viên bi và luồng khí bên trong còi","Chiếc còi", "Miệng của người trọng tài", "Dây thanh đới của người trọng tài"));
        arrCauHoi.add(arrCauSo14);

        //Câu số 15
        ArrayList<CauHoi> arrCauSo15 = new ArrayList<>();
        arrCauSo15.add(taoCauHoi("Phim hoạt hình đầu tiên được công chiếu vào thời gian nào","28-10-1892","22-02-1992", "28-10-1992", "22-02-1892"));
        arrCauSo15.add(taoCauHoi("Truyện ngắn 'Nước Mắt' là sáng tác của nhà văn nào sau đây?","Nam Cao","Tô Hoài", "Thanh Tịnh", "Thạch Lam"));
        arrCauSo15.add(taoCauHoi("Danh họa 'Luis de Carvajal' là người nước nào","Tây Ban Nha","Bồ Đào Nha", "Italia", "Hà Lan"));
        arrCauHoi.add(arrCauSo15);
    }

    public CauHoi taoCauHoi(String s1, String s2, String s3, String s4, String s5){
        CauHoi setCH = new CauHoi();
        setCH.setNoiDung(s1);
        setCH.setDapAnDung(s2);
        setCH.setDapAnSai1(s3);
        setCH.setDapAnSai2(s4);
        setCH.setDapAnSai3(s5);
        return setCH;
    }
}
