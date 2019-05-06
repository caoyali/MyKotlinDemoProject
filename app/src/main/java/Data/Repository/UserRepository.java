package Data.Repository;

import Data.Dao.UserDao;
import Data.Dao.Subtype.User;
import androidx.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserRepository {

    private UserDao userDao;
    @Inject
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }
    public LiveData<User> getUser(int userId) {
        if (null == userDao.load(0)){
            refreshData();
        }
        return userDao.load(userId);
    }

    public void changeuserName(User user){
        userDao.update(user);
    }

    public void insertData(User user){
        userDao.save(user);
    }

    public List<User> getAlluser(){
        return userDao.loadAll();
    }

    public void refreshData(){
        User user = new User();
        user.setAge(16);
        user.setGender("女");
        user.setName("默认的名字");
        user.setPhone("838283475974");
        List<User> users = new ArrayList<>();
//        user.setUsers(users);
        userDao.save(user);
    }
}
