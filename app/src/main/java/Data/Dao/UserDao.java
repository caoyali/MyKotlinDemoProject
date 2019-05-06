package Data.Dao;

import Data.Dao.Subtype.User;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(int userId);

    @Update(onConflict = REPLACE)
    void update(User user);
}
