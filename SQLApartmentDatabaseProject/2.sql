--Populate the tables with at least five values each

/* used for debugging
delete from Has;
delete from Lease;
delete from New;
delete from Renovated;
delete from Features;
delete from Apartment;
delete from FloorPlan;
delete from References;
delete from Tenant;
delete from Inspect;
delete from Inspection;
delete from Building;
*/

--Building
insert into Building
values (1, 'Cave', 3, 2000, 2000);

insert into Building
values (2, 'Mountain', 3, 2000, 2500);

insert into Building
values (3, 'Valley', 3, 2000, 1500);

insert into Building
values (4, 'Riverside', 2, 2002, 3000);

insert into Building
values (5, 'Forest', 2, 2002, 3500);

insert into Building
values (6, 'Cove', 4, 2005, 2000);

insert into Building
values (7, 'Beach', 4, 2005, 4000);

insert into Building
values (8, 'Snowdrift', 5, 2020, 5000);

insert into Building
values (9, 'Dune', 5, 2020, 4500);

insert into Building
values (10, 'Marsh', 5, 2020, 5000);

--New
insert into New
values (6, 1111);

insert into New
values (7, 2222);

insert into New
values (8, 3333);

insert into New
values (9, 4444);

insert into New
values (10, 5555);

--Renovated
insert into Renovated
values (1, 2020);

insert into Renovated
values (2, 2022);

insert into Renovated
values (3, 2018);

insert into Renovated
values (4, 2021);

insert into Renovated
values (5, 2019);

--Inspection
insert into Inspection
values (123, TO_DATE('03 06 2002', 'MM DD YYYY'), 'Passed', 'Leonard Brown', 'NA');

insert into Inspection
values (234, TO_DATE('06 23 2004', 'MM DD YYYY'), 'Passed', 'Yi Li', 'NA');

insert into Inspection
values (345, TO_DATE('07 02 2010', 'MM DD YYYY'), 'Passed', 'David Alger', 'NA');

insert into Inspection
values (456, TO_DATE('04 19 2012', 'MM DD YYYY'), 'Failed', 'Stephen Rainwater', 
'Water pipes are faulty');

insert into Inspection
values (567, TO_DATE('07 11 2020', 'MM DD YYYY'), 'Passed', 'David Hull', 'NA');

insert into Inspection
values (678, TO_DATE('03 14 2013', 'MM DD YYYY'), 'Passed', 'Arun Kulkarni', 'NA');

insert into Inspection
values (789, TO_DATE('04 20 2008', 'MM DD YYYY'), 'Passed', 'Tom Roberts', 'NA');

insert into Inspection
values (890, TO_DATE('01 22 2010', 'MM DD YYYY'), 'Failed', 'Robert Schumaker', 
'Hazardous exposed wires on ground floor and floor two');

insert into Inspection
values (1234, TO_DATE('12 01 2007', 'MM DD YYYY'), 'Passed', 'Narayanan Subramanian', 'NA');

insert into Inspection
values (2345, TO_DATE('01 15 2016', 'MM DD YYYY'), 'Passed', 'Lidong Wu', 'NA');

insert into Inspection
values (3456, TO_DATE('10 22 2009', 'MM DD YYYY'), 'Passed', 'Leonard Brown', 'NA');

insert into Inspection
values (4567, TO_DATE('02 28 2006', 'MM DD YYYY'), 'Passed', 'Stephen Rainwater', 'NA');

insert into Inspection
values (5678, TO_DATE('01 17 2011', 'MM DD YYYY'), 'Passed', 'Arun Kulkarni', 'NA');

insert into Inspection
values (6789, TO_DATE('05 04 2018', 'MM DD YYYY'), 'Passed', 'Yi Li', 'NA');

insert into Inspection
values (7890, TO_DATE('08 10 2019', 'MM DD YYYY'), 'Passed', 'David Alger', 'NA');

--Apartment
insert into Apartment
values (311, 1, 'N', 'White', 'Tan', 'Black', 'HorizontalLines', 'Oak');

insert into Apartment
values (312, 2, 'E', 'Grey', 'Red', 'White', 'VerticalLines', 'Redwood');

insert into Apartment
values (233, 3, 'S', 'Brown', 'Grey', 'Grey', 'ZigZagLines', 'Elm');

insert into Apartment
values (122, 4, 'W', 'Green', 'Blue', 'Red', 'Checkerboard', 'Sycamore');

insert into Apartment
values (221, 5, 'N', 'Brown', 'Red', 'Grey', 'VerticalLines', 'Oak');

insert into Apartment
values (111, 6, 'S', 'Grey', 'Blue', 'White', 'HorizontalLines', 'Elm');

insert into Apartment
values (413, 7, 'W', 'Green', 'Tan', 'Green', 'ZigZagLines', 'Sycamore');

insert into Apartment
values (413, 8, 'E', 'Purple', 'Blue', 'Green', 'PolkaDots', 'Birch');

insert into Apartment
values (534, 9, 'N', 'Yellow', 'Yellow', 'Black', 'Checkerboard', 'Oak');

insert into Apartment
values (522, 10, 'S', 'Red', 'Blue', 'White', 'ZigZagLines', 'Redwood');

insert into Apartment
values (213, 1, 'N', 'Brown', 'Bronze', 'Gold', 'Checkerboard', 'Oak');

insert into Apartment
values (331, 2, 'E', 'Tan', 'Blue', 'Silver', 'VerticalLines', 'Redwood');

insert into Apartment
values (134, 3, 'S', 'Grey', 'Green', 'Blue', 'ZigZagLines', 'Elm');

insert into Apartment
values (123, 4, 'W', 'Yellow', 'Tan', 'Black', 'Checkerboard', 'Sycamore');

insert into Apartment
values (200, 5, 'N', 'White', 'Black', 'Grey', 'Checkerboard', 'Oak');

insert into Apartment
values (413, 6, 'S', 'Grey', 'Blue', 'White', 'ZigZagLines', 'Elm');

insert into Apartment
values (410, 7, 'W', 'Green', 'Tan', 'Green', 'PolkaDots', 'Sycamore');

insert into Apartment
values (112, 8, 'E', 'Red', 'Yellow', 'Blue', 'VerticalLines', 'Birch');

insert into Apartment
values (504, 9, 'N', 'Grey', 'Tan', 'Yellow', 'Checkerboard', 'Oak');

insert into Apartment
values (413, 10, 'S', 'Blue', 'White', 'Red', 'ZigZagLines', 'Redwood');

--Features
insert into Features 
values (311, 1, 'covered parking spot');

insert into Features 
values (311, 1, 'balcony');

insert into Features 
values (312, 2, 'balcony');

insert into Features 
values (312, 2, 'fireplace');

insert into Features 
values (233, 3, 'balcony');

insert into Features 
values (233, 3, 'fireplace');

insert into Features 
values (122, 4, 'poolside');

insert into Features 
values (122, 4, 'fireplace');

insert into Features 
values (221, 5, 'balcony');

insert into Features 
values (221, 5, 'fireplace');

insert into Features 
values (111, 6, 'poolside');

insert into Features 
values (111, 6, 'covered parking spot');

insert into Features 
values (413, 7, 'balcony');

insert into Features 
values (413, 7, 'covered parking spot');

insert into Features 
values (413, 8, 'covered parking spot');

insert into Features 
values (413, 8, 'fireplace');

insert into Features 
values (534, 9, 'balcony');

insert into Features 
values (534, 9, 'covered parking spot');

insert into Features 
values (522, 10, 'balcony');

insert into Features 
values (522, 10, 'fireplace');

insert into Features 
values (213, 1, 'covered parking spot');

insert into Features 
values (213, 1, 'balcony');

insert into Features 
values (331, 2, 'fireplace');

insert into Features 
values (331, 2, 'balcony');

insert into Features 
values (134, 3, 'poolside');

insert into Features 
values (134, 3, 'covered parking spot');

insert into Features 
values (123, 4, 'poolside');

insert into Features 
values (123, 4, 'fireplace');

insert into Features 
values (200, 5, 'covered parking spot');

insert into Features 
values (200, 5, 'fireplace');

insert into Features 
values (413, 6, 'balcony');

insert into Features 
values (413, 6, 'fireplace');

insert into Features 
values (410, 7, 'balcony');

insert into Features 
values (410, 7, 'covered parking spot');

insert into Features 
values (112, 8, 'poolside');

insert into Features 
values (112, 8, 'covered parking spot');

insert into Features 
values (504, 9, 'balcony');

insert into Features 
values (504, 9, 'fireplace');

insert into Features 
values (413, 10, 'fireplace');

insert into Features 
values (413, 10, 'balcony');

--FloorPlan
insert into FloorPlan
values ('A', 4, 2, 500, 1500);

insert into FloorPlan
values ('B', 4, 4, 1000, 2000);

insert into FloorPlan
values ('C', 2, 2, 1000, 1250);

insert into FloorPlan
values ('D', 3, 3, 900, 1250);

insert into FloorPlan
values ('E', 1, 1, 2000, 500);

--Tenant
insert into Tenant
values ('John', 'Smith', 'D', 630, 3000);

insert into Tenant
values ('Stacy', 'James', 'S', 750, 3500);

insert into Tenant
values ('Mark', 'John', 'L', 800, 5000);

insert into Tenant
values ('Kennedy', 'Jones', 'R', 400, 2500);

insert into Tenant
values ('Richard', 'Benson', 'E', 690, 3250);

--References
insert into References
values ('John', 'Smith', 'D', 'Amazon');

insert into References
values ('John', 'Smith', 'D', 'Stacy S. James');

insert into References
values ('Stacy', 'James', 'S', 'Google');

insert into References
values ('Stacy', 'James', 'S', 'University of Texas at Tyler');

insert into References
values ('John', 'Smith', 'D', 'Dan Fornace');

insert into References
values ('John', 'Smith', 'D', 'CAT');

insert into References
values ('Kennedy', 'Jones', 'R', 'Mark L. John');

insert into References
values ('Kennedy', 'Jones', 'R', 'Richard E Jones');

insert into References
values ('Richard', 'Benson', 'E', 'Mark L. John');

insert into References
values ('Richard', 'Benson', 'E', 'Joseph McMillan');

--Inspect
insert into Inspect
values (1, 123);

insert into Inspect
values (2, 234);

insert into Inspect
values (3, 345);

insert into Inspect
values (4, 456);

insert into Inspect
values (5, 567);

insert into Inspect
values (6, 678);

insert into Inspect
values (7, 789);

insert into Inspect
values (8, 890);

insert into Inspect
values (9, 1234);

insert into Inspect
values (10, 2345);

insert into Inspect
values (1, 3456);

insert into Inspect
values (2, 4567);

insert into Inspect
values (3, 5678);

insert into Inspect
values (4, 6789);

insert into Inspect
values (5, 7890);

--Has
insert into Has
values (311, 1, 'A', 4);

insert into Has
values (312, 2, 'B', 4);

insert into Has
values (233, 3, 'C', 2);

insert into Has
values (122, 4, 'D', 3);

insert into Has
values (221, 5, 'E', 1);

insert into Has
values (111, 6, 'A', 4);

insert into Has
values (413, 7, 'B', 4);

insert into Has
values (413, 8, 'C', 2);

insert into Has
values (534, 9, 'D', 3);

insert into Has
values (522, 10, 'E', 1);

insert into Has
values (213, 1, 'A', 4);

insert into Has
values (331, 2, 'B', 4);

insert into Has
values (134, 3, 'C', 2);

insert into Has
values (123, 4, 'D', 3);

insert into Has
values (200, 5, 'E', 1);

insert into Has
values (413, 6, 'E', 1);

insert into Has
values (410, 7, 'B', 4);

insert into Has
values (112, 8, 'C', 2);

insert into Has
values (504, 9, 'D', 3);

insert into Has
values (413, 10, 'E', 1);

--Lease
insert into Lease
values (410, 7, 'John', 'Smith', 'D');

insert into Lease
values (413, 10, 'Stacy', 'James', 'S');

insert into Lease
values (213, 1, 'Mark', 'John', 'L');

insert into Lease
values (112, 8, 'Kennedy', 'Jones', 'R');

insert into Lease
values (233, 3, 'Richard', 'Benson', 'E');








