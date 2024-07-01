## Collection
```bash
testdb> db.oms_company_address.find().pretty()
[
  {
    _id: ObjectId('667f4953a4fb5f569aa26a13'),
    address_id: 1,
    company_name: 'ABC Company',
    address: '123 Main St',
    city: 'CityA',
    state: 'StateA',
    postal_code: '12345',
    country: 'CountryA',
    phone: '111-1111-1111'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a14'),
    address_id: 2,
    company_name: 'XYZ Corp',
    address: '456 Oak Ave',
    city: 'CityB',
    state: 'StateB',
    postal_code: '67890',
    country: 'CountryB',
    phone: '222-2222-2222'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a15'),
    address_id: 3,
    company_name: 'LMN Enterprises',
    address: '789 Pine Rd',
    city: 'CityC',
    state: 'StateC',
    postal_code: '54321',
    country: 'CountryC',
    phone: '333-3333-3333'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a16'),
    address_id: 4,
    company_name: 'EFG Ltd',
    address: '101 Elm St',
    city: 'CityD',
    state: 'StateD',
    postal_code: '98765',
    country: 'CountryD',
    phone: '444-4444-4444'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a17'),
    address_id: 5,
    company_name: 'RST Corporation',
    address: '202 Maple Ave',
    city: 'CityE',
    state: 'StateE',
    postal_code: '13579',
    country: 'CountryE',
    phone: '555-5555-5555'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a18'),
    address_id: 6,
    company_name: 'UVW Solutions',
    address: '303 Oak Blvd',
    city: 'CityF',
    state: 'StateF',
    postal_code: '24680',
    country: 'CountryF',
    phone: '666-6666-6666'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a19'),
    address_id: 7,
    company_name: 'HIJ Group',
    address: '404 Cedar Ln',
    city: 'CityG',
    state: 'StateG',
    postal_code: '97531',
    country: 'CountryG',
    phone: '777-7777-7777'
  }
]

```

## Insert
```bash
testdb> db.oms_company_address.insert([
s_id: 9,
    company_name: 'MNO Corporation',
    address: '606 Elm Ave',
    city: 'CityI',
    state: 'StateI',
    postal_code: '97531',
    country: 'CountryI',
    phone: '999-9999-9999'
  }
]);
...   {
...     address_id: 8,
...     company_name: 'PQR Industries',
...     address: '505 Pine St',
...     city: 'CityH',
...     state: 'StateH',
...     postal_code: '24680',
...     country: 'CountryH',
...     phone: '888-8888-8888'
...   },
...   {
...     address_id: 9,
...     company_name: 'MNO Corporation',
...     address: '606 Elm Ave',
...     city: 'CityI',
...     state: 'StateI',
...     postal_code: '97531',
...     country: 'CountryI',
...     phone: '999-9999-9999'
...   }
... ]);

```

## find one 
```bash
testdb> db.oms_company_address.findOne({ address_id: 1 });
{
  _id: ObjectId('667f4953a4fb5f569aa26a13'),
  address_id: 1,
  company_name: 'ABC Company',
  address: '123 Main St',
  city: 'CityA',
  state: 'StateA',
  postal_code: '12345',
  country: 'CountryA',
  phone: '111-1111-1111'
}

```


## find all 
```bash
testdb> db.oms_company_address.find().pretty();
[
  {
    _id: ObjectId('667f4953a4fb5f569aa26a13'),
    address_id: 1,
    company_name: 'ABC Company',
    address: '123 Main St',
    city: 'CityA',
    state: 'StateA',
    postal_code: '12345',
    country: 'CountryA',
    phone: '111-1111-1111'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a14'),
    address_id: 2,
    company_name: 'XYZ Corp',
    address: '456 Oak Ave',
    city: 'CityB',
    state: 'StateB',
    postal_code: '67890',
    country: 'CountryB',
    phone: '222-2222-2222'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a15'),
    address_id: 3,
    company_name: 'LMN Enterprises',
    address: '789 Pine Rd',
    city: 'CityC',
    state: 'StateC',
    postal_code: '54321',
    country: 'CountryC',
    phone: '333-3333-3333'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a16'),
    address_id: 4,
    company_name: 'EFG Ltd',
    address: '101 Elm St',
    city: 'CityD',
    state: 'StateD',
    postal_code: '98765',
    country: 'CountryD',
    phone: '444-4444-4444'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a17'),
    address_id: 5,
    company_name: 'RST Corporation',
    address: '202 Maple Ave',
    city: 'CityE',
    state: 'StateE',
    postal_code: '13579',
    country: 'CountryE',
    phone: '555-5555-5555'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a18'),
    address_id: 6,
    company_name: 'UVW Solutions',
    address: '303 Oak Blvd',
    city: 'CityF',
    state: 'StateF',
    postal_code: '24680',
    country: 'CountryF',
    phone: '666-6666-6666'
  },
  {
    _id: ObjectId('667f4953a4fb5f569aa26a19'),
    address_id: 7,
    company_name: 'HIJ Group',
    address: '404 Cedar Ln',
    city: 'CityG',
    state: 'StateG',
    postal_code: '97531',
    country: 'CountryG',
    phone: '777-7777-7777'
  },
  {
    _id: ObjectId('667f49a3a4fb5f569aa26a1a'),
    address_id: 8,
    company_name: 'PQR Industries',
    address: '505 Pine St',
    city: 'CityH',
    state: 'StateH',
    postal_code: '24680',
    country: 'CountryH',
    phone: '888-8888-8888'
  },
  {
    _id: ObjectId('667f49a3a4fb5f569aa26a1b'),
    address_id: 9,
    company_name: 'MNO Corporation',
    address: '606 Elm Ave',
    city: 'CityI',
    state: 'StateI',
    postal_code: '97531',
    country: 'CountryI',
    phone: '999-9999-9999'
  }
]

```

## Change one 
```bash
testdb> db.oms_company_address.updateOne(
...   { address_id: 1 },
...   {
...     $set: {
...       phone: '123-4567-8901'
...     }
...   }
... );
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
testdb> db.oms_company_address.find().pretty();
[
  {
    _id: ObjectId('667f4953a4fb5f569aa26a13'),
    address_id: 1,
    company_name: 'ABC Company',
    address: '123 Main St',
    city: 'CityA',
    state: 'StateA',
    postal_code: '12345',
    country: 'CountryA',
    phone: '123-4567-8901'
  },

```
## delete one 
```bash

testdb> db.oms_company_address.deleteOne({ address_id: 8 });
{ acknowledged: true, deletedCount: 1 }
testdb> db.oms_company_address.find().pretty();

  {
    _id: ObjectId('667f4953a4fb5f569aa26a19'),
    address_id: 7,
    company_name: 'HIJ Group',
    address: '404 Cedar Ln',
    city: 'CityG',
    state: 'StateG',
    postal_code: '97531',
    country: 'CountryG',
    phone: '777-7777-7777'
  },
  {
    _id: ObjectId('667f49a3a4fb5f569aa26a1b'),
    address_id: 9,
    company_name: 'MNO Corporation',
    address: '606 Elm Ave',
    city: 'CityI',
    state: 'StateI',
    postal_code: '97531',
    country: 'CountryI',
    phone: '999-9999-9999'
  }
]

```
