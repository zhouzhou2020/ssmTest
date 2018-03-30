package cn.damonto.ssmTest.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BaseEntity {

    private Long id;

    /**
     * 将实体的ID作为key，实体作为value存入Map中
     * @param list 实体集合
     * @return 当list为空时，返回一个空Map
     */
    public static <T extends BaseEntity> Map<Long,T> idEntityMap(Collection<T> list){
        Map<Long,T> map = new HashMap<>();

        if(null == list || 0 == list.size()){
            return map;
        }

        for(T entity : list){
            map.put(entity.getId(), entity);
        }
        return map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}