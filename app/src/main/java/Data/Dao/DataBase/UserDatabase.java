package Data.Dao.DataBase;

import Data.Dao.Subtype.User;
import Data.Dao.UserDao;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
