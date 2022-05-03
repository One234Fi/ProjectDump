/* 

List the full name, credit score, apartment number, and number of references of the tenant that 
has the most references. 

*/

select FirstName || ' ' || MidInitial || '. ' || Lastname FullName, CreditScore, AptNumber, maxRefs RefCount
from Tenant natural join Lease
natural join (
	select FirstName, LastName, MidInitial, count(*) NumRefs
	from References
	group by FirstName, LastName, MidInitial
	), 	(select MAX (numRefs) maxRefs
		from (select FirstName, LastName, MidInitial, Count(*) numRefs
		from References
		group by FirstName, LastName, MidInitial)
		)
where NumRefs in maxRefs;