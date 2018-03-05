-- 秒杀执行存储过程
DELIMITER $$ -- $$表示程序结束
-- 定义存储过程
CREATE PROCEDURE `seckill`.`execute_seckill`
  (in v_seckill_id bigint, in v_phone bigint,
    in v_kill_time timestamp, out r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION;
    insert ignore into success_killed
      (seckill_id, user_phone, create_time, state)
      values (v_seckill_id, v_phone, v_kill_time, 0);
    select ROW_COUNT() into insert_count;
    IF (insert_count = 0) THEN
      ROLLBACK;
      set r_result = 2;
    ELSEIF (insert_count <0) THEN
      ROLLBACK;
      set r_result = -1;
    ELSE
     update seckill
     set number = number - 1
     where seckill_id = v_seckill_id
        AND end_time>v_kill_time
        AND start_time<v_kill_time
        and number > 0;
     select ROW_COUNT() INTO insert_count;
     IF (insert_count < 0) THEN
      ROLLBACK;
      set r_result = -1;
     ELSEIF (insert_count = 0) THEN
      rollback;
      set r_result = 1;
     ELSE
      commit;
      set r_result = 0;
     END IF;
    END IF;
  END
$$

delimiter ;
-- set @r_result = -3;
-- call execute_seckill(1007, 15700077502, now(), @r_result);
-- select @r_result;

-- 存储过程
-- 事务行级锁持有的时间简短，不要过度依赖，简单逻辑可以执行
