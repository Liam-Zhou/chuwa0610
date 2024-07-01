## fetch all 
```bash
mysql> select * from oms_company_address;
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
| address_id | company_name    | address       | city  | state  | postal_code | country  | phone         |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
|          1 | ABC Company     | 123 Main St   | CityA | StateA | 12345       | CountryA | 111-1111-1111 |
|          2 | XYZ Corp        | 456 Oak Ave   | CityB | StateB | 67890       | CountryB | 222-2222-2222 |
|          3 | LMN Enterprises | 789 Pine Rd   | CityC | StateC | 54321       | CountryC | 333-3333-3333 |
|          4 | EFG Ltd         | 101 Elm St    | CityD | StateD | 98765       | CountryD | 444-4444-4444 |
|          5 | RST Corporation | 202 Maple Ave | CityE | StateE | 13579       | CountryE | 555-5555-5555 |
|          6 | UVW Solutions   | 303 Oak Blvd  | CityF | StateF | 24680       | CountryF | 666-6666-6666 |
|          7 | HIJ Group       | 404 Cedar Ln  | CityG | StateG | 97531       | CountryG | 777-7777-7777 |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
7 rows in set (0.00 sec)

```

## top 3 
```bash
mysql> SELECT * FROM oms_company_address LIMIT 3;
+------------+-----------------+-------------+-------+--------+-------------+----------+---------------+
| address_id | company_name    | address     | city  | state  | postal_code | country  | phone         |
+------------+-----------------+-------------+-------+--------+-------------+----------+---------------+
|          1 | ABC Company     | 123 Main St | CityA | StateA | 12345       | CountryA | 111-1111-1111 |
|          2 | XYZ Corp        | 456 Oak Ave | CityB | StateB | 67890       | CountryB | 222-2222-2222 |
|          3 | LMN Enterprises | 789 Pine Rd | CityC | StateC | 54321       | CountryC | 333-3333-3333 |
+------------+-----------------+-------------+-------+--------+-------------+----------+---------------+
3 rows in set (0.00 sec)

```

## Change all phone number
```bash
mysql> UPDATE oms_company_address SET phone = '666-6666-8888';
Query OK, 7 rows affected (0.01 sec)
Rows matched: 7  Changed: 7  Warnings: 0

mysql> select * from oms_company_address;
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
| address_id | company_name    | address       | city  | state  | postal_code | country  | phone         |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
|          1 | ABC Company     | 123 Main St   | CityA | StateA | 12345       | CountryA | 666-6666-8888 |
|          2 | XYZ Corp        | 456 Oak Ave   | CityB | StateB | 67890       | CountryB | 666-6666-8888 |
|          3 | LMN Enterprises | 789 Pine Rd   | CityC | StateC | 54321       | CountryC | 666-6666-8888 |
|          4 | EFG Ltd         | 101 Elm St    | CityD | StateD | 98765       | CountryD | 666-6666-8888 |
|          5 | RST Corporation | 202 Maple Ave | CityE | StateE | 13579       | CountryE | 666-6666-8888 |
|          6 | UVW Solutions   | 303 Oak Blvd  | CityF | StateF | 24680       | CountryF | 666-6666-8888 |
|          7 | HIJ Group       | 404 Cedar Ln  | CityG | StateG | 97531       | CountryG | 666-6666-8888 |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
7 rows in set (0.00 sec)

```
## Delete one
```bash
mysql> delete from oms_company_address where address_id=1
    -> ;
Query OK, 1 row affected (0.01 sec)

mysql> select * from oms_company_address;
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
| address_id | company_name    | address       | city  | state  | postal_code | country  | phone         |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
|          2 | XYZ Corp        | 456 Oak Ave   | CityB | StateB | 67890       | CountryB | 666-6666-8888 |
|          3 | LMN Enterprises | 789 Pine Rd   | CityC | StateC | 54321       | CountryC | 666-6666-8888 |
|          4 | EFG Ltd         | 101 Elm St    | CityD | StateD | 98765       | CountryD | 666-6666-8888 |
|          5 | RST Corporation | 202 Maple Ave | CityE | StateE | 13579       | CountryE | 666-6666-8888 |
|          6 | UVW Solutions   | 303 Oak Blvd  | CityF | StateF | 24680       | CountryF | 666-6666-8888 |
|          7 | HIJ Group       | 404 Cedar Ln  | CityG | StateG | 97531       | CountryG | 666-6666-8888 |
+------------+-----------------+---------------+-------+--------+-------------+----------+---------------+
6 rows in set (0.00 sec)

```


