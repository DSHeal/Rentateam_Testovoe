package com.dsheal.rentateam_testovoe.ui.users_list;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsheal.rentateam_testovoe.api.ApiFactory;
import com.dsheal.rentateam_testovoe.api.ApiService;
import com.dsheal.rentateam_testovoe.model.data.AppDatabase;
import com.dsheal.rentateam_testovoe.model.pojo.User;
import com.dsheal.rentateam_testovoe.model.pojo.UserResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UsersListViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private LiveData<List<User>> users;
    private MutableLiveData<Throwable> errors;
    private CompositeDisposable compositeDisposable;

    public UsersListViewModel(@NonNull Application application) {
        super(application);
        // нужно создать объекты, на которые будет подписываться View
        db = AppDatabase.getInstance(application);
        users = db.usersDao().getAllUsers();
        errors = new MutableLiveData<>();
    }
    // чтобы активити могла подписаться на users, создаем его геттер
    // теперь View может вызвать метод getUsers, получить объект LiveData и подписаться на него
    public LiveData<List<User>> getUsers() {
        return users;
    }
    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors() {
        errors.setValue(null);
    }

    @SuppressWarnings("unchecked")
    public void insertUsers(List<User> users) {
        new UsersListViewModel.InsertUsersTask().execute(users);
    }

    //загружать список также лучше в другом потоке
    @SuppressWarnings("deprecation")
    private static class InsertUsersTask extends AsyncTask<List<User>, Void, Void> {
        @Override
        protected Void doInBackground(List<User>... lists) {
            if (lists != null && lists.length > 0){
                db.usersDao().insertUsers(lists[0]);
            }
            return null;
        }
    }

    public void deleteAllUsers() {
        new UsersListViewModel.DeleteAllUsersTask().execute();
    }
    private static class DeleteAllUsersTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.usersDao().deleteAllUsers();
            return null;
        }
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        //этот метод возвращает тип Observable
        Disposable disposable = apiService.getUsers()
                // эти первые две строчки указываются практически во всех случаях: в каком потоке загружать и в каком потоке принимать
                .subscribeOn(Schedulers.io()) // показывает, в каком потоке совершать все обращения к БД или интернету
                .observeOn(AndroidSchedulers.mainThread()) // в каком потоке мы будем принимать данные - в главном, результат выполнения производился уже в главном потоке
                // теперь указываем, что делать, когда мы получим данные
                .subscribe(new Consumer<UserResponse>() {
                    @Override
                    public void accept(UserResponse userResponse) throws Exception {
                        deleteAllUsers();
                        insertUsers(userResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("Peach", throwable.getMessage());
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);
    }
    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

}