-- Scratchpad for report generation


-- total_listing_count
--select count(*) from listing;

--select owner_email_address, count(*) listings from listing
--group by owner_email_address
--order by listings desc

-- best_lister_email_address
--select owner_email_address from
--	(select owner_email_address, count(*) listings from listing
--	group by owner_email_address
--	order by listings desc
--	limit 1) as best_lister_email_address;

--select id, to_char(upload_time,'YYYY-MM') as year_month from listing
--limit 100;

-- total_listing_count, total_listing_price, average_listing_price for EBAY /mp
--select to_char(upload_time,'YYYY-MM') as year_month,
--	count(*) as total_ebay_listing_count,
--	sum(listing_price) as total_ebay_listing_price,
--	avg(listing_price) as average_ebay_listing_price
--	from listing
--	inner join marketplace on listing.marketplace = marketplace.id
--	where marketplace.marketplace_name like 'EBAY'
--	group by year_month

-- total_listing_count, total_listing_price, average_listing_price for AMAZON /mp
--select to_char(upload_time,'YYYY-MM') as year_month,
--	count(*) as total_amazon_listing_count,
--	sum(listing_price) as total_amazon_listing_price,
--	avg(listing_price) as average_amazon_listing_price
--	from listing
--	inner join marketplace on listing.marketplace = marketplace.id
--	where marketplace.marketplace_name like 'AMAZON'
--	group by year_month

-- total_listing_count, total_listing_price, average_listing_price /m /mp
--select distinct on (year_month, marketplace) to_char(upload_time,'YYYY-MM') as year_month,
--	marketplace,
--	count(*) over w as total_listing_count,
--	sum(listing_price) over w as total_listing_price,
--	avg(listing_price) over w as average_listing_price
--	from listing
--	window w as (partition by to_char(upload_time,'YYYY-MM'), marketplace);

-- total_listing_count, total_listing_price, average_listing_price /m
-- (two rows per month)
--select year_month,
--	case marketplace_name
--		when 'EBAY' then total_listing_count
--		else 0
--	end as total_ebay_listing_count,
--	case marketplace_name
--		when 'EBAY' then total_listing_price
--		else 0
--	end as total_ebay_listing_price,
--	case marketplace_name
--		when 'EBAY' then average_listing_price
--		else 0
--	end as average_ebay_listing_price,

--	case marketplace_name
--		when 'AMAZON' then total_listing_count
--		else 0
--	end as total_amazon_listing_count,
--	case marketplace_name
--		when 'AMAZON' then total_listing_price
--		else 0
--	end as total_amazon_listing_price,
--	case marketplace_name
--		when 'AMAZON' then average_listing_price
--		else 0
--	end as average_amazon_listing_price

--	from
--	(select distinct on (year_month, marketplace) to_char(upload_time,'YYYY-MM') as year_month,
--		marketplace_name,
--		count(*) over w as total_listing_count,
--		sum(listing_price) over w as total_listing_price,
--		avg(listing_price) over w as average_listing_price
--		from listing
--	 	inner join marketplace on listing.marketplace = marketplace.id
--		window w as (partition by to_char(upload_time,'YYYY-MM'), marketplace)
--	 ) as sq
--;

-- best_lister_email_address /m
--select distinct on (year_month) year_month, owner_email_address from
--	(select to_char(upload_time,'YYYY-MM') as year_month, owner_email_address, count(*) as listings
--		from listing
--		group by year_month, owner_email_address
--		order by year_month asc, listings desc
--	 ) as sq;

--create temporary table temp_reports (
--	year_month varchar(10) primary key,
--	total_listing_count integer,

--	total_ebay_listing_count integer,
--	total_ebay_listing_price numeric(19,2),
--	average_ebay_listing_price numeric(19,2),

--	total_amazon_listing_count integer,
--	total_amazon_listing_price numeric(19,2),
--	average_amazon_listing_price numeric(19,2),

--	best_lister_email_address varchar(255)
--);

--drop table temp_reports;

-- monthly reports
--insert into temp_reports (year_month,
--							 	total_ebay_listing_count,
--							   	total_ebay_listing_price,
--							   	average_ebay_listing_price,
--							   	total_amazon_listing_count,
--							   	total_amazon_listing_price,
--							   	average_amazon_listing_price,
--							   	best_lister_email_address)
--select * from
--		(select to_char(upload_time,'YYYY-MM') as year_month,
--			count(*) as total_ebay_listing_count,
--			sum(listing_price) as total_ebay_listing_price,
--			avg(listing_price) as average_ebay_listing_price
--			from listing
--			inner join marketplace on listing.marketplace = marketplace.id
--			where marketplace.marketplace_name like 'EBAY'
--			group by year_month) as ebay_q
--	inner join
--		(select to_char(upload_time,'YYYY-MM') as year_month,
--			count(*) as total_amazon_listing_count,
--			sum(listing_price) as total_amazon_listing_price,
--			avg(listing_price) as average_amazon_listing_price
--			from listing
--			inner join marketplace on listing.marketplace = marketplace.id
--			where marketplace.marketplace_name like 'AMAZON'
--			group by year_month) as amazon_q
--	using (year_month)
--	inner join
--		(select distinct on (year_month) year_month, owner_email_address from
--			(select to_char(upload_time,'YYYY-MM') as year_month, owner_email_address, count(*) as listings
--			from listing
--			group by year_month, owner_email_address
--			order by year_month asc, listings desc) as sq
--		 ) as email_q
--	using (year_month);

--select * from temp_reports;

-- insert total report
--with tlc as (
--		select 'total', count(*) from listing
--	), ebay as (
--		select count(*) as total_ebay_listing_count,
--		sum(listing_price) as total_ebay_listing_price,
--		avg(listing_price) as average_ebay_listing_price
--		from listing
--		inner join marketplace on listing.marketplace = marketplace.id
--		where marketplace.marketplace_name like 'EBAY'
--	), amazon as (
--		select count(*) as total_amazon_listing_count,
--		sum(listing_price) as total_amazon_listing_price,
--		avg(listing_price) as average_amazon_listing_price
--		from listing
--		inner join marketplace on listing.marketplace = marketplace.id
--		where marketplace.marketplace_name like 'AMAZON'
--	), email as (
--		select owner_email_address from
--			(select owner_email_address, count(*) listings from listing
--			group by owner_email_address
--			order by listings desc
--			limit 1) as best_lister_email_address
--	)
--insert into temp_reports
--select * from tlc, ebay, amazon, email;


-- report generation function
create or replace function generate_report()
	returns table (
		year_month varchar(10),
		total_listing_count integer,

		total_ebay_listing_count integer,
		total_ebay_listing_price numeric(19,2),
		average_ebay_listing_price numeric(19,2),

		total_amazon_listing_count integer,
		total_amazon_listing_price numeric(19,2),
		average_amazon_listing_price numeric(19,2),

		best_lister_email_address varchar(255)
	)
as $$
begin
	create temporary table temp_reports (
		year_month varchar(10) primary key,
		total_listing_count integer,

		total_ebay_listing_count integer,
		total_ebay_listing_price numeric(19,2),
		average_ebay_listing_price numeric(19,2),

		total_amazon_listing_count integer,
		total_amazon_listing_price numeric(19,2),
		average_amazon_listing_price numeric(19,2),

		best_lister_email_address varchar(255)
	);

insert into temp_reports (year_month,
							 	total_ebay_listing_count,
							   	total_ebay_listing_price,
							   	average_ebay_listing_price,
							   	total_amazon_listing_count,
							   	total_amazon_listing_price,
							   	average_amazon_listing_price,
							   	best_lister_email_address)
select * from
		(select to_char(upload_time,'YYYY-MM') as year_month,
			count(*) as total_ebay_listing_count,
			sum(listing_price) as total_ebay_listing_price,
			avg(listing_price) as average_ebay_listing_price
			from listing
			inner join marketplace on listing.marketplace = marketplace.id
			where marketplace.marketplace_name like 'EBAY'
			group by year_month) as ebay_q
	inner join
		(select to_char(upload_time,'YYYY-MM') as year_month,
			count(*) as total_amazon_listing_count,
			sum(listing_price) as total_amazon_listing_price,
			avg(listing_price) as average_amazon_listing_price
			from listing
			inner join marketplace on listing.marketplace = marketplace.id
			where marketplace.marketplace_name like 'AMAZON'
			group by year_month) as amazon_q
	using (year_month)
	inner join
		(select distinct on (year_month) sq.year_month, owner_email_address from
			(select to_char(upload_time,'YYYY-MM') as year_month, owner_email_address, count(*) as listings
			from listing
			group by year_month, owner_email_address
			order by year_month asc, listings desc) as sq
		 ) as email_q
	using (year_month);

	with tlc as (
			select 'total', count(*) from listing
		), ebay as (
			select count(*) as total_ebay_listing_count,
			sum(listing_price) as total_ebay_listing_price,
			avg(listing_price) as average_ebay_listing_price
			from listing
			inner join marketplace on listing.marketplace = marketplace.id
			where marketplace.marketplace_name like 'EBAY'
		), amazon as (
			select count(*) as total_amazon_listing_count,
			sum(listing_price) as total_amazon_listing_price,
			avg(listing_price) as average_amazon_listing_price
			from listing
			inner join marketplace on listing.marketplace = marketplace.id
			where marketplace.marketplace_name like 'AMAZON'
		), email as (
			select owner_email_address from
				(select owner_email_address, count(*) listings from listing
				group by owner_email_address
				order by listings desc
				limit 1) as best_lister_email_address
		)
	insert into temp_reports
	select * from tlc, ebay, amazon, email;

	return query select * from temp_reports;
	drop table temp_reports;

end; $$
LANGUAGE 'plpgsql';


