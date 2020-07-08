package vn.edu.ntu.nguyentruonglong.dangkydichvu;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class DangKyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText edtTen, edtNgaySinh, edtSDT, edtDiaChi;
    RadioGroup rdgThanhToan;
    RadioButton rdbTienMat, rdbNganHang, rdbViDienTu;
    ImageView imvDate;
    Spinner spnDichVu;
    NavController navController;
    Button btnDangKy;
    ArrayAdapter<String> adapterDichVu;
    String[] dsDichVu;
    String dichVu = "";

    public static final String sharePrefName = "my_share_preferense";
    public static final String key_str  = "ten_str";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dangky, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addView();
    }

    private void addView() {
        edtTen = ((MainActivity)getActivity()).findViewById(R.id.edtTen);
        edtNgaySinh = ((MainActivity)getActivity()).findViewById(R.id.edtNgaySinh);
        edtSDT = ((MainActivity)getActivity()).findViewById(R.id.edtSDT);
        edtDiaChi = ((MainActivity)getActivity()).findViewById(R.id.edtDiaChi);
        rdgThanhToan = ((MainActivity)getActivity()).findViewById(R.id.rdgThanhToan);
        rdbTienMat = ((MainActivity)getActivity()).findViewById(R.id.rdbTienMat);
        rdbNganHang = ((MainActivity)getActivity()).findViewById(R.id.rdbNganHang);
        rdbViDienTu = ((MainActivity)getActivity()).findViewById(R.id.rdbViDienTu);
        imvDate = ((MainActivity)getActivity()).findViewById(R.id.imvDate);
        spnDichVu = ((MainActivity)getActivity()).findViewById(R.id.spnDichVu);
        btnDangKy = ((MainActivity)getActivity()).findViewById(R.id.btnDangKy);

        dsDichVu = getResources().getStringArray(R.array.dichvu);
        adapterDichVu = new ArrayAdapter<>(((MainActivity)getActivity()), android.R.layout.simple_list_item_1, dsDichVu);
        spnDichVu.setAdapter(adapterDichVu);

        spnDichVu.setOnItemSelectedListener(this); // implement AdapterView.OnItemSelectedListener sẽ override 2 method onItemSelected, onNothingSelected
        imvDate.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dichVu = dsDichVu[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).navController = navController;
    }

    private void dangKy() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Chúc mừng khách hàng: ")
                .append(edtTen.getText())
                .append(";\n")
                .append("Ngày sinh: ")
                .append(edtNgaySinh.getText())
                .append(";\n")
                .append("Số điện thoại liên lạc: ")
                .append(edtSDT.getText())
                .append(";\n")
                .append("Đã đăng ký thành công dịch vụ: ")
                .append(dichVu)
                .append(";\n")
                .append("Hình thức thanh toán: ");


                switch (rdgThanhToan.getCheckedRadioButtonId()) {
                    case R.id.rdbTienMat:
                        builder.append("Bằng tiền mặt; \n");
                        break;
                    case R.id.rdbNganHang:
                        builder.append("Bằng chuyển khoản ngân hàng; \n");
                        break;
                    case R.id.rdbViDienTu:
                        builder.append("Bằng ví điện tử; \n");
                        break;
                }


        builder.append("Chúng tôi sẽ liên lạc cho bạn theo số điện thoại được cung cấp, cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.");

        Bundle bundle = new Bundle();
        bundle.putString("info", builder.toString());

        NavHostFragment.findNavController(DangKyFragment.this).navigate(R.id.action_dangKyFragment_to_thongBaoFragment, bundle);
    }

    private void chonNgay() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);

                StringBuilder builder = new StringBuilder();
                builder.append(dayOfMonth)
                        .append("-")
                        .append(month)
                        .append("-")
                        .append(year);
                edtNgaySinh.setText(builder.toString());
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog((MainActivity)getActivity(),
                listener, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnDangKy: dangKy(); break;
            case R.id.imvDate: chonNgay(); break;
        }
    }
}
