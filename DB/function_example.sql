--создание хранимой функции вставки данных в таблицу
create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    returns void
    language 'plpgsql'
as
$$
begin
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
end;
$$;

--вызов хр функции вставки
select f_insert_data('product_1', 'producer_1', 25, 50);

--создание хр функции изменения данных таблицы по id
create or replace function f_update_data(u_count integer, tax float, u_id integer)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
        select into result count from products where id = u_id;
    end if;
    if tax > 0 THEN
        update products set price = price + price * tax;
        select into result sum(price) from products;
    end if;
    return result;
end;
$$;

--вызов хр функции изменения
select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

--вызов хр функции (обновление цен +20%)
select f_update_data(0, 0.2, 0);

--создание хр функции удаления данных из таблицы по id или count
create or replace function f_del_data(d_id integer, d_count_less integer)
    returns varchar
    language 'plpgsql'
as
$$
declare
    result varchar;
begin
    if d_id > 0 THEN
        select into result name from products where id = d_id;
        delete from products where id = d_id;
    end if;
    if d_count_less > 0 THEN
        select into result count(*) from products where count < d_count_less;
        delete from products where count < d_count_less;
    end if;
    return result;
end;
$$;

--вызов хр функции удаления
select f_del_data(1, 0);
select f_del_data(0, 10);