package  com.kazi.test.data.db

import androidx.room.*
import com.kazi.test.data.db.entities.MovieResultsItem

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:List<MovieResultsItem>): List<Long>

    @Query("SELECT * from MovieResultsItem")
    suspend fun getAllEmployee(): List<MovieResultsItem>

    @Update
    suspend fun update(employee: MovieResultsItem) : Int


}