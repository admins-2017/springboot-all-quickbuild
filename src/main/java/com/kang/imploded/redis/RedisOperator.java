package com.kang.imploded.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Description: 使用redisTemplate的操作实现类
 */
@Component
public class RedisOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String类型
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * String类型
     * 返回剩余过期时间并且指定时间单位
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key,TimeUnit timeUnit) {
        return redisTemplate.getExpire(key,timeUnit);
    }

    /**
     * String类型
     * 将key持久化保存
     * @param key
     * @return
     */
    public Boolean persistKey(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * String 类型
     * 实现命令：exists 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * String 类型
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * String 类型
     * 实现命令：INCR key，增加key一次
     * 增加整数（正值则自增，负值则自减）
     *
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * String 类型
     * 实现命令：INCR key，增加key一次
     * 增加整数（正值则自增，负值则自减）
     *
     * @param key
     * @return
     */
    public Double incrByDouble(String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }


    /**
     * String类型
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void delKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * String类型
     * 实现命令：DEL keys，删除多个key
     *
     * @param keys
     */
    public void delKeys(Set<String> keys) {
        redisTemplate.delete(keys);
    }


    /**
     * 设置string类型 的key和value
     * 实现命令：/置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * String类型
     * 修改redis中key的名称
     */
    public void renameKey(String oldKeyName, String newKeyName){
        redisTemplate.rename(oldKeyName,newKeyName);
    }

    /**
     * 所有类型通用
     * 查询key的类型
     */
    public DataType getKeyType(String key){
        return redisTemplate.type(key);
    }

    /**
     * 设置string类型 的key和 int类型的 value
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void set(String key, int value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置string类型 的key和 int类型的 value
     *
     * @param key
     * @param value
     *            （以秒为单位）
     */
    public void set(String key, int value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     * 设置string类型 的key和 String类型的 value
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void setObj(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.HOURS);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void setObjSeconds(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }


    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     */
    public void setObj(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * String 类型
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String getString(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     * String 类型
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * String 类型
     * 批量获取值
     *
     * @param keys
     * @return value
     */
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * String类型
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public int getInt(String key) {
        return (int)redisTemplate.opsForValue().get(key);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    //---------------------------- Hash（哈希表）---------------------------------

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：以map集合的形式添加键值对
     *
     * @param key
     * @param maps
     */
    public void hPutAll(String key, Map<String, Object> maps) {
        redisTemplate.opsForHash().putAll(key,maps);
    }

    /**
     * 实现命令：仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public boolean hashPutIfAbsent(String key, String hashKey, String value)  {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }


    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     * 获取key的map中field对应的值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 实现命令：查看hash表中指定字段是否存在
     * @param key
     * @param field
     * @return
     */
    public boolean hashExists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key,field);
    }

    /**
     * 实现命令：获取所有hash表中字段
     * @param key
     * @return
     */
    public Set<String> hashExists(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 实现命令：获取hash表中字段的数量
     * @param key
     * @return
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 实现命令：获取hash表中存在的所有的值
     * @param key
     * @return
     */
    public List<Object> hValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 实现命令：给哈希表key中的指定字段的整数值加上增量increment
     * @param key
     * @param field
     * @return
     */
    public Long hashIncrBy(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 实现命令：给哈希表key中的指定字段的浮点数值加上增量increment
     * @param key
     * @param field
     * @return
     */
    public Double hIncrByDouble(String key, Object field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定的字段，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<String, Object> hGetStringAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    //---------------------------- Hash（哈希表）---------------------------------

    //---------------------------- List（列表）---------------------------------

    /**
     * 实现命令：通过索引获取列表中的元素
     *
     * @param key
     * @param index
     * @return 返回对应索引的元素
     */
    public Object getKeyByIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key,index);
    }

    /**
     * 实现命令：获取列表指定范围内的元素(start开始位置, 0是开始位置，end 结束位置, -1返回所有)
     *
     * @param key
     * @param start
     * @param end
     * @return 返回对应索引的元素
     */
    public List<Object> getKeyByIndex(String key, Long start ,Long end) {
        return redisTemplate.opsForList().range(key,start,end);
    }

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的最后
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 存储在list的头部，即添加一个就把它放在最前面的索引处
     *
     * @param key
     * @return 列表key的头元素。
     */
    public Long leftPush(String key,String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 把多个值存入List中(value可以是多个值，也可以是一个Collection value)
     *
     * @param key
     * @return 列表key的头元素。
     */
    public Long leftPushAll(String key,String... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 如果pivot处值存在则在pivot前面添加
     *
     * @param key
     * @return 列表key的头元素。
     */
    public Long leftPush(String key,String pivot,String value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }


    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public String lpop(String key) {
        return (String)redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：获取当前key的List列表长度
     *
     * @param key
     * @return 列表key的头元素。
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    //---------------------------- List（列表）---------------------------------

    //---------------------------- Set类型 （元素唯一 不可以重复）-------------------------------------

    /**
     * 实现命令：添加元素
     * @param key
     * @param values 多个元素
     */
    public Long addSet(String key,Object...values){
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 实现命令：移除元素(单个值、多个值)
     * @param key
     * @param values 多个元素
     */
    public Long removeSet(String key,Object...values){
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 实现命令：获取集合的大小
     * @param key
     */
    public Long getSetSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 实现命令：判断集合是否包含value
     * @param key
     */
    public Boolean isMember(String key,String value){
        return redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 实现命令：获取两个集合的交集(key对应的无序集合与otherKey对应的无序集合求交集)
     * 交集的功能：常用在共同好友 圈子等功能实现
     * @param key
     */
    public Set<Object> intersect(String key,String otherKey){
        return redisTemplate.opsForSet().intersect(key,otherKey);
    }

    /**
     * 实现命令：获取多个集合的交集(Collection )
     * @param key
     */
    public Set<Object> intersects(String key,Collection<Object> otherKey){
        return redisTemplate.opsForSet().intersect(key,otherKey);
    }

    /**
     * 实现命令：key集合与多个集合的交集存储到destKey无序集合中
     * @param key
     */
    public Long intersects(String key,Collection<Object> otherKey,String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }


    /**
     * 实现命令：获取两个或者多个集合的并集(otherKeys可以为单个值或者是集合)
     * @param key
     */
    public Set<Object> union(String key,String destKey){
        return redisTemplate.opsForSet().union(key, destKey);
    }

    /**
     * 实现命令：获取两个或者多个集合的并集(otherKeys可以为单个值或者是集合)
     * @param key
     */
    public Set<Object> union(String key,Collection<Object> otherKey){
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * 实现命令：key集合与otherKey集合的并集存储到destKey中(otherKeys可以为单个值或者是集合)
     * @param key
     */
    public Long unionAndStore(String key,String otherKey,String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }


    /**
     * 实现命令：获取两个或者多个集合的差集(otherKeys可以为单个值或者是集合)
     * @param key
     */
    public Set<Object> difference(String key,Collection<Object> otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * 实现命令：获取两个或者多个集合的差集(otherKeys可以为单个值或者是集合)
     * @param key
     */
    public Set<Object> difference(String key,String otherKeys){
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }


    /**
     * 实现命令：差集存储到destKey中(otherKeys可以为单个值或者集合)
     * @param key
     */
    public Long differenceAndStore(String key,String otherKey,String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * 实现命令：随机获取集合中的一个元素
     * @param key
     */
    public Object randomMember(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 实现命令：获取集合中的所有元素
     * @param key
     */
    public Set<Object> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 实现命令：随机获取集合中count个元素
     * @param key
     */
    public List<Object> randomMembers(String key,Long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 实现命令：获取多个key无序集合中的元素（去重），count表示个数
     * @param key
     */
    public Set<Object> distinctRandomMembers(String key,Long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 实现命令：遍历set类似于Interator(ScanOptions.NONE为显示所有的)
     * @param key
     */
    public Cursor distinctRandomMembers(String key, ScanOptions options) {
        return redisTemplate.opsForSet().scan(key, options);
    }

    //---------------------------- Set类型-------------------------------------

    //---------------------------- zSet类型 有序集合 常用到排行榜中 积分，成绩，票数等排名-------------------------------------

    /**
     * 实现命令：ZSetOperations提供了一系列方法对有序集合进行操作
     * 添加元素(有序集合是按照元素的score值由小到大进行排列)
     * @param key
     * @param value
     * @param score
     */
    public boolean addZset(String key,String value,Double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 实现命令：ZSetOperations提供了一系列方法对有序集合进行操作
     * 添加多个元素
     * @param key
     * @param tuples
     */
    public Long addZsets(String key,Set<ZSetOperations.TypedTuple<Object>> tuples){

        return redisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 实现命令：删除对应的value,value可以为多个值
     * @param key
     * @param values
     */
    public Long removeZsets(String key,String... values){
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 实现命令：修改变量中元素的分数值
     * @param key
     * @param value
     */
    public Double incrementScore(String key,String value,double delta){
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    /**
     * 实现命令：返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     * @param key
     * @param value
     */
    public Long rankSet(String key,String value){
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 实现命令：返回元素在集合的排名,按元素的score值由大到小排列
     * @param key
     * @param value
     */
    public Long reverseRankZset(String key,String value){
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 实现命令：获取集合中给定区间的元素(start 开始位置，end 结束位置, -1查询所有)
     * @param key
     * @param start
     * @param end
     */
    public Set<ZSetOperations.TypedTuple<String>> reverseRankZset(String key, long start, long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start,end);
    }

    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     *
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值  zrevrange  0 -1 表示获取全部的集合内容
     *
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> revRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }



    /**
     * 实现命令：按照Score值查询集合中的元素，结果从小到大排序
     * @param key
     * @param min
     * @param max
     */
    public Set<Object> reverseRangeByScore(String key,double min,double max){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 实现命令：从高到低的排序集中获取分数在最小和最大值之间的元素
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     */
    public Set<Object> reverseRangeByScore(String key,double min,double max,long start,long end){
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 实现命令：根据score值获取集合元素数量
     * @param key
     * @param min
     * @param max
     */
    public Long countZset(String key,double min,double max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 实现命令：获取集合的大小
     * @param key
     */
    public Long gZsetSize(String key){
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 实现命令：获取集合中key、value元素对应的score值
     * @param key
     * @param value
     */
    public Double gZsetSize(String key,String value){
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 实现命令：移除指定索引位置处的成员
     * @param key
     * @param start
     * @param end
     */
    public Long removeRange(String key,Long start,Long end){
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 实现命令：移除指定score范围的集合成员
     * @param key
     * @param min
     * @param max
     */
    public Long removeRangeByScore(String key,double min,double max){
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 实现命令：获取key和otherKey的并集并存储在destKey中（其中otherKeys可以为单个字符串或者字符串集合）
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long unionAndStoreZsets(String key,String otherKey,String destKey){
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 实现命令：获取key和otherKey的交集并存储在destKey中（其中otherKeys可以为单个字符串或者字符串集合）
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long intersectAndStore(String key,String otherKey,String destKey){
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }


}
