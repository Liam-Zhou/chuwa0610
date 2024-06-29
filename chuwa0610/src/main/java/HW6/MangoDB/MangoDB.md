
- use test;

- db.createCollection("oms_company_address");

- db.oms_company_address.insertMany([
  {
  "address_name": "First",
  "send_status": 1,
  "receive_status": 1,
  "name": "Alice",
  "phone": "111-111-1111",
  "province": "A",
  "city": "a",
  "region": "aa",
  "detail_address": "Red Street"
  },
  {
  "address_name": "Second",
  "send_status": 1,
  "receive_status": 1,
  "name": "Bobby",
  "phone": "222-222-2222",
  "province": "B",
  "city": "b",
  "region": "bb",
  "detail_address": "Blue Street"
  },
  {
  "address_name": "Third",
  "send_status": 1,
  "receive_status": 1,
  "name": "Charlcy",
  "phone": "333-333-3333",
  "province": "C",
  "city": "c",
  "region": "cc",
  "detail_address": "Green Street"
  },
  {
  "address_name": "Fourth",
  "send_status": 1,
  "receive_status": 1,
  "name": "Doggy",
  "phone": "444-444-4444",
  "province": "D",
  "city": "d",
  "region": "dd",
  "detail_address": "Yellow Street"
  }
  ]);

- db.oms_company_address.findOne()

- db.oms_company_address.find().pretty()

- db.oms_company_address.updateOne( { name: "Doggy" }, { $set: { phone: "666-6666-8888" } } )

- db.oms_company_address.remove({ name: "Doggy" })