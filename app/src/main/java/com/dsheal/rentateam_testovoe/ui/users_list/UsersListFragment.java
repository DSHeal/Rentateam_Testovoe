package com.dsheal.rentateam_testovoe.ui.users_list;

import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.dsheal.rentateam_testovoe.R;
import com.dsheal.rentateam_testovoe.adapters.UserAdapter;
import com.dsheal.rentateam_testovoe.model.pojo.User;
import java.util.ArrayList;
import java.util.List;

public class UsersListFragment extends Fragment {

    private UsersListViewModel viewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProgressBar progressBarLoading;

    public static UsersListFragment newInstance() {
        return new UsersListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_list_fragment, container, false);
        progressBarLoading = view.findViewById(R.id.progressBarLoading);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        userAdapter = new UserAdapter();
        userAdapter.setUsers(new ArrayList<>());
        recyclerView.setAdapter(userAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       viewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
        //подписываемся на изменения в базе данных, getUsers() возвращает тип liveData
       viewModel.getUsers().observe(this.getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(@io.reactivex.annotations.Nullable List<User> users) {
                userAdapter.setUsers(users);
            }
        });
        viewModel.getErrors().observe(this.getViewLifecycleOwner(), new Observer<Throwable>() {
            @Override
            public void onChanged(@io.reactivex.annotations.Nullable Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(getContext(), "Ошибка загрузки данных!", Toast.LENGTH_LONG).show();
                    viewModel.clearErrors();
                }
            }
        });

        progressBarLoading.setVisibility(View.VISIBLE);
        viewModel.loadData();
        progressBarLoading.setVisibility(View.INVISIBLE);
    }


}
