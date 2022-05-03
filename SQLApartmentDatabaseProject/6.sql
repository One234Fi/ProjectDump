/* 

List the apartments that are not yet rented (currently have no tenants).  For each such apartment, 
give its number, direction, building nickname, floor number, rental price, and color scheme.  Sort 
the list by building nickname and rental price in decreasing order. 

The  rental  price  of  an  apartment  is  computed  as  the  base  price  plus  $100  
multiplied by the number of special features it contains. 

*/

--Abomination query incoming...

Select distinct AptNumber Apt#, Direction D, Nickname, cast(AptNumber / 100 as int) FloorNum, RentalPrice, 
(BathroomColor ||' ' || CarpetColor || ' ' || KitchenAppColor) ColorScheme 
from Apartment natural join Building natural join FloorPlan natural join Features
	natural join (
	select AptNumber, BuildingNumber, BasePrice + count(Feature) * 100 RentalPrice
	from Apartment natural join Features natural join Has natural join FloorPlan 
	group by AptNumber, BuildingNumber, BasePrice
	)
where (AptNumber, BuildingNumber) NOT in 
(select AptNumber, BuildingNumber from Lease)
order by Nickname, RentalPrice desc;

--I know I can use views but I think this is funnier





