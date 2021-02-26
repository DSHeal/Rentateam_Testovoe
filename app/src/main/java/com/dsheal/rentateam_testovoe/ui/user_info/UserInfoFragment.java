package com.dsheal.rentateam_testovoe.ui.user_info;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsheal.rentateam_testovoe.R;
import com.squareup.picasso.Picasso;

public class UserInfoFragment extends Fragment {

    private TextView name;
    private TextView lastName;
    private TextView email;
    private ImageView avatar;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_fragment, container, false);
        name = view.findViewById(R.id.textViewName);
        lastName = view.findViewById(R.id.textViewLastName);
        email = view.findViewById(R.id.textViewEmail);
        avatar = view.findViewById(R.id.imageViewAvatar);
        Picasso.get().load(getArguments().getString("avatar_path")).into(avatar);
        assert getArguments() != null;
        name.setText(getArguments().getString("name"));
        lastName.setText(getArguments().getString("surname"));
        email.setText(getArguments().getString("email"));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}