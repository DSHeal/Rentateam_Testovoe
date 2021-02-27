package com.dsheal.rentateam_testovoe.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavController.OnDestinationChangedListener;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import com.dsheal.rentateam_testovoe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navInit();
    }

    public void navInit () {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_container);
        // получаем контроллер из контейнера
        NavController navController = navHostFragment.getNavController();
        // добавили нижнюю навигационную панель
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
        // добавили тулбар
        Toolbar toolbar = findViewById(R.id.toolBar);
        NavigationUI.setupWithNavController(toolbar, navController);
        // меняем видимость нав.деталей в зависимости от экрана
        navController.addOnDestinationChangedListener(new OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.users_list_fragment) {
                    toolbar.setVisibility(View.VISIBLE);
                    bottomNav.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    bottomNav.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}


