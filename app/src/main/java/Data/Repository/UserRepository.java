package Data.Repository;

import Data.Dao.UserDao;
import Data.Dao.Subtype.User;
import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }
    public LiveData<User> getUser(int userId) {
        refreshData();
        return userDao.load(userId);
    }

    public void refreshData(){
        User user = new User();
        user.setAge(16);
        user.setGender("女");
        user.setName("yayali");
        user.setPhone("838283475974");
        userDao.save(user);
    }
}
