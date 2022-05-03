/* 

List the apartment number, number of tenants, type of wood, floor plan, and rental price of each 
apartment that has balcony as one of its special features.  Sort the list by floor plan then number 
of tenants in decreasing order. 

*/

--idk if this line will actually work when you run it but it made the formatting look better on my end
Column FloorPlan format A2;

Select AptNumber, NumTenants, WoodType, (NumBedrooms || Letter) FloorPlan, RentalPrice
from Apartment natural join Has natural join NumTenantTable
natural join (
	select AptNumber, BuildingNumber, Feature
	from Features
	where Feature = 'balcony'
	)
natural join (
	select AptNumber, BuildingNumber, BasePrice + count(Feature) * 100 RentalPrice
	from Apartment natural join Features natural join Has natural join FloorPlan 
	group by AptNumber, BuildingNumber, BasePrice
)
order by FloorPlan, NumTenants desc; 















