--Create the tables, uncomment the block below to clear the tables before making them

/* used for debugging
drop table Has;
drop table Lease;
drop table New;
drop table Renovated;
drop table Features;
drop table Apartment;
drop table FloorPlan;
drop table References;
drop table Tenant;
drop table Inspect;
drop table Inspection;
drop table Building;
drop view NumTenantTable;
*/

--Building
create table Building (
	BuildingNumber number NOT NULL,
	Nickname varchar2(10) NOT NULL,
	NumFloors number NOT NULL,
	ConstructionYear number(4) NOT NULL,
	Value number NOT NULL,
	primary key (BuildingNumber)
);

--New
create table New (
	BuildingNumber number NOT NULL,
	PermitNumber number NOT NULL,
	primary key (BuildingNumber),
	foreign key (BuildingNumber) 
		references Building
		on delete cascade
);

--Renovated
create table Renovated (
	BuildingNumber number NOT NULL,
	RenovatedYear number(4) NOT NULL,
	primary key (BuildingNumber),
	foreign key (BuildingNumber)
		references Building
		on delete cascade
);

--Inspection
create table Inspection (
	IDNumber number NOT NULL,
	InspectionDate date NOT NULL,
	Result varchar2(6) NOT NULL,
	InspectorName varchar2(22) NOT NULL,
	InspectorComment varchar2(255) NOT NULL,
	primary key (IDNumber),
	check (Result = 'Passed' or Result = 'Failed')
);

--Apartment
create table Apartment (
	AptNumber number(3) NOT NULL,
	BuildingNumber number NOT NULL,
	Direction char(1) NOT NULL,
	BathroomColor varchar2(10) NOT NULL,
	CarpetColor varchar2(10) NOT NULL,
	KitchenAppColor varchar2(10) NOT NULL,
	WallpaperStyle varchar2(20) NOT NULL,
	WoodType varchar2(10) NOT NULL,
	primary key (AptNumber, BuildingNumber),
	foreign key (BuildingNumber) 
		references Building
		on delete cascade,
	check ( Direction = 'N' or
		Direction = 'S' or
		Direction = 'E' or
		Direction = 'W')
);

--Features
create table Features (
	AptNumber number NOT NULL,
	BuildingNumber number NOT NULL,
	Feature varchar2(20) NOT NULL,
	primary key (AptNumber, BuildingNumber, Feature),
	foreign key (AptNumber, BuildingNumber)
		references Apartment
		on delete cascade
);

--FloorPlan
create table FloorPlan (
	Letter char(1) NOT NULL,
	NumBedrooms number NOT NULL,
	NumBathrooms number NOT NULL,
	BasePrice number NOT NULL,
	Area number NOT NULL,
	primary key (Letter, NumBedrooms)
);

--Tenant
create table Tenant (
	FirstName varchar2(20) NOT NULL,
	LastName varchar2(20) NOT NULL,
	MidInitial char(1) NOT NULL,
	CreditScore number NOT NULL,
	Income number NOT NULL,
	primary key (FirstName, LastName, MidInitial)
);

--References
create table References (
	FirstName varchar2(20) NOT NULL,
	LastName varchar2(20) NOT NULL,
	MidInitial char(1) NOT NULL,
	References varchar2(50) NOT NULL,
	primary key (FirstName, LastName, MidInitial, References),
	foreign key (FirstName, LastName, MidInitial)
		references Tenant
		on delete cascade
);

--Inspect
create table Inspect (
	BuildingNumber number NOT NULL,
	IDNumber number NOT NULL,
	primary key (IDNumber),
	foreign key (BuildingNumber)
		references Building
		on delete cascade,
	foreign key (IDNumber)
		references Inspection
		on delete cascade
);

--Has
create table Has (
	AptNumber number NOT NULL,
	BuildingNumber number NOT NULL,
	Letter char(1) NOT NULL,
	NumBedrooms number NOT NULL,
	primary key (AptNumber, BuildingNumber),
	foreign key (AptNumber, BuildingNumber)
		references Apartment
		on delete cascade,
	foreign key (Letter, NumBedrooms) 
		references FloorPlan
		on delete cascade
);

--Lease
create table Lease (
	AptNumber number,
	BuildingNumber number,
	FirstName varchar2(20),
	LastName varchar2(20),
	MidInitial char(1),
	primary key (AptNumber, BuildingNumber),
	foreign key (AptNumber, BuildingNumber)
		references Apartment
		on delete cascade,
	foreign key (FirstName, LastName, MidInitial)
		references Tenant
		on delete cascade
);


--Number of tenants in each apartment
Create view NumTenantTable as
(select AptNumber, BuildingNumber, count(*) NumTenants
		from Tenant natural join Lease
		group by AptNumber, BuildingNumber)
		union
		(select AptNumber, BuildingNumber, 0 NumTenants
		from Apartment
		where (AptNumber, BuildingNumber) NOT in 
		(select AptNumber, BuildingNumber from Lease));


