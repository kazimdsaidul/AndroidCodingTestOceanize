package  com.kazi.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazi.test.data.db.entities.Employee

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<Employee>): List<Long>

    @Query("SELECT * from Employee")
    suspend fun getAllEmployee(): List<Employee>


}