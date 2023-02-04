create view show_books_with_active_status
as
select o.id, o.active, b.name Book, a.name Автор, s.name Студент
from orders o
         left join books b on o.book_id = b.id
         left join authors a on b.author_id = a.id
         left join students s on o.student_id = s.id
where o.active IS true
AND s.name IS NOT NULL;

select *
from show_books_with_active_status;

alter view show_books_with_active_status
rename to show_books_with_active;

create view show_books_without_gogol
as
select o.id, o.active, b.name Book, a.name Автор, s.name Студент
from orders o
         left join books b on o.book_id = b.id
         left join authors a on b.author_id = a.id
         left join students s on o.student_id = s.id
group by o.id, o.active, b.name, a.name, s.name
having a.name != 'Николай Гоголь';

select *
from show_books_without_gogol;

drop view show_books_without_gogol;