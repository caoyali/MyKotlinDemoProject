package Data.Dao;

import Data.Dao.Subtype.User;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    //似乎纯操作User 和 返回User的相关指令才会自动触发观察模式
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(int userId);
    //像这种数据，对LiveData来说，似乎是不伦不类。那么得到的就不会触发观察者。
    //至于为何会触发观察者我就很无语了，我也不知道是怎么触发的！！！
    @Query("SELECT * FROM user")
    List<User> loadAll();

    @Update(onConflict = REPLACE)
    void update(User user);
}
