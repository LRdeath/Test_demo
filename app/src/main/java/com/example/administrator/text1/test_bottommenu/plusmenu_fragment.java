package com.example.administrator.text1.test_bottommenu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.text1.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class plusmenu_fragment extends Fragment implements View.OnClickListener {
    private View menu1;
    private View menu2;
    private View menu3;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottommenu_plus_menu, null);
        menu1 = view.findViewById(R.id.plus_menu_1);
        menu1.setOnClickListener(this);
        menu2 = view.findViewById(R.id.plus_menu_2);
        menu2.setOnClickListener(this);
        menu3 = view.findViewById(R.id.plus_menu_3);
        menu3.setOnClickListener(this);
        view.findViewById(R.id.qwer).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus_menu_1:
                Toast.makeText(getActivity(),"联系客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plus_menu_2:
                Toast.makeText(getActivity(),"机器人",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plus_menu_3:
                Toast.makeText(getActivity(),"紧急呼叫",Toast.LENGTH_SHORT).show();
                break;

            default:
                dismiss();
                break;

        }
    }

    private void dismiss() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
