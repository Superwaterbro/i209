package com.example.anan.AAChartCore.ChartsDemo.MainContent.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.ScrollableChartActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.AlarmShowActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.CustomStyleChartActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.JianActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Button button;
    private Button button3;
    private Button button2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard1, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button=(Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), JianActivity.class);
                startActivity(intent);
            }
        });
        button3=(Button) getActivity().findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), ScrollableChartActivity.class);
                startActivity(intent);
            }
        });

        button2=(Button) getActivity().findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), AlarmShowActivity.class);
                startActivity(intent);
            }
        });
    }
}