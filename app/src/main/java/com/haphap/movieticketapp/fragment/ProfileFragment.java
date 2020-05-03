package com.haphap.movieticketapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.activity.LoginActivity;
import com.haphap.movieticketapp.model.User;
import com.haphap.movieticketapp.utils.SharePrefenceHelper;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.haphap.movieticketapp.utils.SharePrefenceHelper.USER_ID_KEY;
import static com.haphap.movieticketapp.utils.SharePrefenceHelper.getINSTANCE;

public class ProfileFragment extends Fragment {

    private TextView tvName
            , tvEmail
            , tvTicket
            , tvLogout;

    private SharePrefenceHelper sharePrefenceHelper;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = root.findViewById(R.id.tv_name);
        tvEmail = root.findViewById(R.id.tv_email);
        tvTicket = root.findViewById(R.id.tv_ticket);
        tvLogout = root.findViewById(R.id.tv_logout);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharePrefenceHelper = getINSTANCE(getContext());
        User user = (User) getActivity().getIntent().getExtras().get(USER_ID_KEY);

        tvName.setText(user.getFirstName() + " " + user.getLastName());
        tvEmail.setText(user.getEmail());

        tvLogout.setOnClickListener(view1 -> {
            sharePrefenceHelper.setLoggedUserId((long) -1);
            if (sharePrefenceHelper.getUserId() == -1) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }
}
