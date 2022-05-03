
/* 

List the permit number, number of floors, construction year, inspector name, and inspection date 
of  inspections  of  new  buildings  that  failed  the  inspection.    Sort  the  list  by  inspection  date  listing  
the oldest inspection first. 

*/


select PermitNumber, NumFloors, ConstructionYear, InspectorName, InspectionDate
from Building natural join New natural join Inspect natural join Inspection
where Result = 'Failed'
order by InspectionDate;

