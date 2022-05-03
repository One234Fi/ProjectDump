/* 

List the nickname, value and total apartment area of each old building.  The total apartment area 
for  a building is  computed of  the  sum  of  the  areas  of the floor plans  of  its  apartments.   Sort  the  
list by total area in ascending order. 

*/



Select distinct Nickname, Value, TotalArea
from Building natural join Apartment
	natural join (	select BuildingNumber, sum(area) TotalArea
			from FloorPlan natural join Has
			group by BuildingNumber
	)
where BuildingNumber NOT in (select BuildingNumber from New)
order by TotalArea;