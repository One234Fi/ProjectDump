/* 

Change (update) the color scheme for all open apartments (no tenants) in old buildings that have 
been renovated more than two years ago. 

*/

--Puce is a color I swear
update Apartment
set 	BathroomColor = 'Yellow',
	CarpetColor = 'Puce',
	KitchenAppColor = 'Lime'
where 
	(AptNumber, BuildingNumber) NOT in (Select AptNumber, BuildingNumber from Lease)
	AND
	(AptNumber, BuildingNumber) in (Select AptNumber, BuildingNumber
					from Renovated
					where RenovatedYear < 2020);