 ### Spring framework job interview program  

I have been given the following tasks:
 - synchronize data from a REST API (mockaroo.com)
 - validate and store it in to the local database (MySQL  or Postgres)\
    validate Listing objects only, if validation fails, write into importLog.csv
 - create a report according to the following conditions
   - Total listing count 
   - Total eBay listing count 
   - Total eBay listing price 
   - Average eBay listing price 
   - Total Amazon listing count 
   - Total Amazon listing price 
   - Average Amazon listing price 
   - Best lister email address 
   - Monthly reports: 
     - Total eBay listing count per month 
     - Total eBay listing price per month 
     - Average eBay listing price per month 
     - Average Amazon listing price per month 
     - Total Amazon listing count per month 
     - Total Amazon listing price per month 
     - Best lister email address of the month 
 - upload report as JSON file (report.json) to FTP

The way I have sent this in is marked with the 'sentthisversion' tag.\
Please note that even though I was given a month for this, as I have been doing 10-12 hour shifts, I was only able to spend around 5 days programming.\
I haven't seen Java in years and this was the first time I had a serious look at the Spring Framework.\
Although the program is incomplete, it is generating the report.json and uploads it to FTP.\
I think I did pretty good given the circumstances, but the company didn't agree...

TODO:
 - finish validation
 - setup database with .sql file
 - use .properties files for project setup
 - error hangling / logging
 - tests

Please note that right now a Postgres database has to be set up manually (only the database, tables are handled by Hibernate) and report.sql has to be run against it (to create the report generator function).
