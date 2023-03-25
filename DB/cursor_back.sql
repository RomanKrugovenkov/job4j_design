BEGIN;

DECLARE
cursor_products cursor for
select *
from products;

--переход и получение 20 строки
MOVE FORWARD 19 FROM cursor_products;
FETCH NEXT FROM cursor_products;

--переход и получение 15 строки
move backward 4 from cursor_products;
fetch prior from cursor_products;

--переход и получение 7 строки
move backward 7 from cursor_products;
fetch prior from cursor_products;

--переход и получение 2 и 1 строки
move backward 4 from cursor_products;
fetch prior from cursor_products;
fetch prior from cursor_products;

CLOSE cursor_products;

COMMIT;