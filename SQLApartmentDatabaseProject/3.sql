/* 

List  the  value,  nickname,  and  number  of  open  apartments  of  each  building  that  is  new  or  
renovated  within  the  last  three  years. Sort  the  results  by  value  then  by  number  of  open  
apartments (both in decreasing order). 

*/


Select Value, Nickname, count(*) numOpenApts
from Apartment natural join Building 
	natural join (select BuildingNumber, Year
	from 
	(select BuildingNumber, ConstructionYear Year from Building
	where BuildingNumber not in (select BuildingNumber from Renovated)) 
	union 
	(select BuildingNumber, RenovatedYear Year from Building natural join Renovated))
where ((AptNumber, BuildingNumber) NOT in 
(select AptNumber, BuildingNumber from Lease)
AND Year >= 2019)
group by Value, Nickname
order by Value desc, numOpenApts desc;


























