package vn.edu.ntu.nguyentruonglong.dangkydichvu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ThongBaoFragment extends Fragment {

    TextView txtInfo;
    Button btnXacNhan;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thongbao, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtInfo = view.findViewById(R.id.txtInfo);
        btnXacNhan = view.findViewById(R.id.btnXacNhan);
        Bundle bundle = getArguments();
        if (bundle != null) {
            txtInfo.setText(bundle.getString("info"));
        }
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ThongBaoFragment.this)
                        .navigateUp();
            }
        });
    }
}
