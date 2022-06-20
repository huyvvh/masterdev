-- Question 1
select *
from users u 
where u.usersname like 'ghtk';

-- Question 2
select *
from users u 
where u.province like '%Thanh pho Ho Chi Minh'
order by u.age desc
limit 10;
