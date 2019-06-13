package app.infrastructure.doma.dao;

import app.infrastructure.doma.entity.EventEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface EventEntityDao {
    @Select
    List<EventEntity> selectAll();
}
