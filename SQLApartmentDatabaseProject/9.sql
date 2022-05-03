/* 

For  each  apartment  that  has  at  least  one  tenant  with  no  references,  list  the  apartment’s  floor  
plan, number of bedrooms, number of bathrooms, rental price, and area.  Sort the list by area in 
decreasing order. 

*/

select NumBedrooms || Letter FloorPlan, NumBedrooms, NumBathrooms, RentalPrice, Area 
from Apartment natural join Has natural join FloorPlan natural join Lease natural join
	(
	Select FirstName, LastName, MidInitial
	from Tenant
	where (FirstName, LastName, MidInitial) NOT in 
	(Select FirstName, LastName, MidInitial from References)
	)
	natural join
	(
	select AptNumber, BuildingNumber, BasePrice + count(Feature) * 100 RentalPrice
	from Apartment natural join Features natural join Has natural join FloorPlan 
	group by AptNumber, BuildingNumber, BasePrice
	)
order by Area desc;