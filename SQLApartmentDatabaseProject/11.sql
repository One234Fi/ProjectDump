/* 

Remove (delete) the special feature that appears in the most apartments of newer buildings. 

*/


--I can't even read this I did this on mistake and it just worked idk
delete from Features
where Feature in 
	(select Feature from (select Feature, FeatureNum
from (select Feature, count(*) FeatureNum
	from Features
	group by Feature)
where FeatureNum in (select Max(FeatureNum) from (select Feature, count(*) FeatureNum
	from Features
	group by Feature)))
);