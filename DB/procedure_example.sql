--создание хранимой процедуры вставки данных в таблицу
create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    language 'plpgsql'
as
$$
BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END
$$;

--вызов хранимой процедуры вставки
call insert_data('product_2', 'producer_2', 15, 32);

--создание хр процедуры изменения данных таблицы по id
create or replace procedure update_data(u_count integer, tax float, u_id integer)
    language 'plpgsql'
as $$
BEGIN
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
    end if;
    if tax > 0 THEN
        update products set price = price + price * tax;
    end if;
END;
$$;

--вызов процедуры изменения
call update_data(10, 0, 1);

call update_data(0, 0.2, 0);

--обнуление данных таблицы и счетчика id
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

--создание хранимой процедуры удаление из таблицы по id или count
create or replace procedure del_data(d_id integer, d_count_less integer)
    language 'plpgsql'
as $$
BEGIN
    if d_id > 0 THEN
        delete from products where id = d_id;
    end if;
    if d_count_less > 0 THEN
        delete from products where count < d_count_less;
    end if;
END;
$$;

--вызов хранимой процедуры удаления
call del_data(1, 10);