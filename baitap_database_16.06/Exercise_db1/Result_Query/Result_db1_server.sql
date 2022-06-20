-- Qusestion 1:
SELECT AVG(subcribe.score) AS average
FROM subcribe;

-- Question 2:
SELECT (
	(SELECT COUNT(subcribe.`rank`)
	FROM subcribe
	WHERE subcribe.`rank` LIKE 'Truot')*100/
	(SELECT COUNT(*)
	FROM subcribe)) AS FailPer	
	
SELECT (
	(SELECT COUNT(subcribe.`rank`)
	FROM subcribe
	WHERE subcribe.`rank` LIKE 'Trungbinh')*100/
	(SELECT COUNT(*)
	FROM subcribe)) AS OrdinaryPer	
	
SELECT (
	(SELECT COUNT(subcribe.`rank`)
	FROM subcribe
	WHERE subcribe.`rank` LIKE 'Kha')*100/
	(SELECT COUNT(*)
	FROM subcribe)) AS GoodPer		
	
SELECT (
	(SELECT COUNT(subcribe.`rank`)
	FROM subcribe
	WHERE subcribe.`rank` LIKE 'Gioi')*100/
	(SELECT COUNT(*)
	FROM subcribe)) AS VeryGoodPer
	
SELECT (
	(SELECT COUNT(subcribe.`rank`)
	FROM subcribe
	WHERE subcribe.`rank` LIKE 'Xuat Sac')*100/
	(SELECT COUNT(*)
	FROM subcribe)) AS ExcellentPer

-- Question 3
SELECT s.id_subject , s.subject_name , AVG(s2.score) AS MaxAverageScoreBySubject
FROM subject s
LEFT JOIN subcribe s2 
ON s.id_subject  = s2.id_subject
GROUP BY s.id_subject
ORDER BY MaxAverageScoreBySubject DESC
LIMIT 1;

-- Question 4
SELECT c.id_class , c.class_name , AVG(s.score) AS MaxAverageScoreByClass
FROM class c
LEFT JOIN subcribe s 
ON s.id_class = c.id_class 
GROUP BY c.id_class 
ORDER BY MaxAverageScoreByClass DESC
LIMIT 1;

-- Question 5
SELECT s.id_student , s.fullname , s.gender , s.birthday , AVG(s2.score) AS MaxAverageScoreByStudent
FROM student s INNER JOIN subcribe s2 
ON s.id_student = s2.id_student 
GROUP BY s.id_student 
ORDER BY MaxAverageScoreByStudent DESC
LIMIT 1;

-- Question 6
SELECT
*
FROM
	(
	SELECT
		subject_name,
		fail_per
	FROM
		(
		SELECT
			p.id_subject ,
			count_fail_score * 100 / COUNT(score) AS fail_per
		FROM
			(
			SELECT
				id_subject ,
				COUNT(fail_score) AS count_fail_score
			FROM
				(
				SELECT
					id_subject,
					score AS fail_score
				FROM
					subcribe
				WHERE
					score < 4) AS fail_score_lst
			GROUP BY
				fail_score_lst.id_subject) p
		INNER JOIN subcribe
ON
			p.id_subject = subcribe.id_subject
		GROUP BY
			id_subject) m
		INNER JOIN subject s
ON
			m.id_subject = s.id_subject) AS b6
ORDER BY
fail_per DESC
LIMIT 1;

-- Question 7
select s.`rank` , s2.fullname , s.id_student 
from subcribe s 
inner join student s2 
on s.id_student = s2.id_student
where s.`rank` not like 'Truot'
group by s.id_student;

-- Question 8
SELECT
*
FROM
	(
	SELECT
		subject_name,
		fail_per
	FROM
		(
		SELECT
			p.id_subject ,
			count_fail_score * 100 / COUNT(score) AS fail_per
		FROM
			(
			SELECT
				id_subject ,
				COUNT(fail_score) AS count_fail_score
			FROM
				(
				SELECT
					id_subject,
					score AS fail_score
				FROM
					subcribe
				WHERE
					score < 4) AS fail_score_lst
			GROUP BY
				fail_score_lst.id_subject) p
		INNER JOIN subcribe
ON
			p.id_subject = subcribe.id_subject
		GROUP BY
			id_subject) m
		INNER JOIN subject s
ON
			m.id_subject = s.id_subject) AS b6
ORDER BY
fail_per DESC
LIMIT 10;


