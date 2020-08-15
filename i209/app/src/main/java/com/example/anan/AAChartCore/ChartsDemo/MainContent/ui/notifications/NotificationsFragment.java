package com.example.anan.AAChartCore.ChartsDemo.MainContent.ui.notifications;

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

import com.example.anan.AAChartCore.ChartsDemo.MainContent.Birth;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.BirthEditActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.BirthShowActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.CallshowActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MainActivity1;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Button button5;
    private Button button8;
    private Button button4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications1, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button5=(Button) getActivity().findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), CallshowActivity.class);
                startActivity(intent);
            }
        });

        button8=(Button) getActivity().findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), BirthShowActivity.class);
                startActivity(intent);
            }
        });
        button4=(Button) getActivity().findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), MainActivity1.class);
                startActivity(intent);
            }
        });
}}